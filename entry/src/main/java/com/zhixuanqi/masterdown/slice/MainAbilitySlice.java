package com.zhixuanqi.masterdown.slice;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.UserFile;
import com.zhixuanqi.masterdown.provider.UserFileProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initUserFileProvider();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private List<UserFile> getUserFileData(){
        List<UserFile> ls = new ArrayList<>();
        // generate mock data
        for(int i = 0; i < 10; i++){
            ls.add(new UserFile(true, "Test-File"+(i)+".md"));
        }

        return ls;
    }

    private void initUserFileProvider(){
        // get the list container compnent from xml
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_file_list_container);
        // instantiate the user file list
        List<UserFile> ls = getUserFileData();
        // instantiate the data provider
        UserFileProvider provider = new UserFileProvider(ls, this);
        // feed the data to the provider
        listContainer.setItemProvider(provider);
    }

}
