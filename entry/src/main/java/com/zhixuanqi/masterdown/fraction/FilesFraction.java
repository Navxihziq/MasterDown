package com.zhixuanqi.masterdown.fraction;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.UserFile;
import com.zhixuanqi.masterdown.listener.NewFileButtonListener;
import com.zhixuanqi.masterdown.provider.UserFileProvider;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.app.Environment;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FilesFraction extends Fraction {
    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        return scatter.parse(ResourceTable.Layout_fraction_files, container, false);
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);

        // initiate the user file data provider
        try {
            initUserFileProvider();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // bind click event listener to the new file image
        Image newFileBtn = (Image) getFractionAbility().findComponentById(ResourceTable.Id_new_file_button);
        newFileBtn.setClickedListener(new NewFileButtonListener());

        ((ListContainer)getFractionAbility().findComponentById(ResourceTable.Id_file_list_container)).setItemClickedListener(new ListContainer.ItemClickedListener() {
            @Override
            public void onItemClicked(ListContainer listContainer, Component component, int i, long l) {
                // jump to the editor page
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.zhixuanqi.masterdown")
                        .withAbilityName("EditorAbility")
                        .build();
                intent.setOperation(operation);
                intent.setParam("filename", ((UserFile)(listContainer.getItemProvider().getItem(i))).getName());
                getFractionAbility().startAbility(intent);   // start the ability
            }
        });
    }

    private List<UserFile> getUserFileData() throws IOException {
        List<UserFile> ls = new ArrayList<>();
        // get current working directory
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        Preferences preferences = databaseHelper.getPreferences("preferences");
        String cwd = preferences.getString("cwd", getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
        File pathToDir = new File(cwd);

        // make the directory for the app if not exists
        if (!pathToDir.exists()){
            if (pathToDir.mkdir()){
                System.out.println("Dir Created");
            }
        }

        // todo: remove in release: generate mock data
        for(int i = 0; i < 5; i++){
            // ls.add(new UserFile(true, "Test-File"+(i)+".md"));

            // create actual file as mocking data
            File mockFile = new File(pathToDir, "Test-File"+(i)+".md");
            if (mockFile.createNewFile()){
                System.out.println("created: "+mockFile.getName());
            }
        }

        // read file info
        File[] files = pathToDir.listFiles();
        for (File file : files){
            ls.add(new UserFile(file.isFile(), file.getName()));
        }

        return ls;
    }

    public void initUserFileProvider() throws IOException {
        // get the list container component from xml
        ListContainer listContainer = (ListContainer)getFractionAbility().findComponentById(ResourceTable.Id_file_list_container);
        // instantiate the user file list
        List<UserFile> ls = getUserFileData();
        // instantiate the data provider
        UserFileProvider provider = new UserFileProvider(ls, getFractionAbility());
        // feed the data to the provider
        listContainer.setItemProvider(provider);
    }

}
