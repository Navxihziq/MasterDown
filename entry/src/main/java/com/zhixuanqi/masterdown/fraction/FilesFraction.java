package com.zhixuanqi.masterdown.fraction;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.UserFile;
import com.zhixuanqi.masterdown.listener.NewFileButtonListener;
import com.zhixuanqi.masterdown.provider.UserFileProvider;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.app.Environment;

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
    }

    private List<UserFile> getUserFileData() throws IOException {
        List<UserFile> ls = new ArrayList<>();
        File pathToDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "MasterDown");

        // make the directory for the app if not exists
        if (!pathToDir.exists()){
            if (pathToDir.mkdir()){
                System.out.println("Dir Created");
            }
        }

        // generate mock data
        for(int i = 0; i < 10; i++){
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
