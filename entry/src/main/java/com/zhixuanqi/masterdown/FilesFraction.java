package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.slice.FileAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;

public class FileAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(FileAbilitySlice.class.getName());
    }

    public void showBottomNavIcons(){
        TabList tabList = (TabList) findComponentById(ResourceTable.Id_bottom_nav_bar);
        TabList.Tab tab = tabList.new Tab(getContext());
        tab.setText("Test");
    }
}
