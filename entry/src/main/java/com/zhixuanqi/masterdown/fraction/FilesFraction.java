package com.zhixuanqi.masterdown.fraction;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.UserFile;
import com.zhixuanqi.masterdown.provider.UserFileProvider;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.ListContainer;
import ohos.app.Environment;

import java.io.File;
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
        initUserFileProvider();
    }

    private List<UserFile> getUserFileData(){
        List<UserFile> ls = new ArrayList<>();
        // generate mock data
        for(int i = 0; i < 10; i++){
            ls.add(new UserFile(true, "Test-File"+(i)+".md"));
        }

        File pathToFiles = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "MasterDown");

        // make the directory for the app if not exists
        if (!pathToFiles.exists()){
            if (pathToFiles.mkdir()){
                System.out.println("Dir Created");
            }
        }

        // read file info


        return ls;
    }

    public void initUserFileProvider(){
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
