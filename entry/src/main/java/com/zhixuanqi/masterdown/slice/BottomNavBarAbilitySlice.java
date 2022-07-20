package com.zhixuanqi.masterdown.slice;

import com.zhixuanqi.masterdown.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;

public class BottomNavBarFractionSlice extends AbilitySlice {
    private String [] menuItems = {"Files", "Todos", "Settings"};

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_bottom_nav_bar_fraction);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    public void pushMenuItems(){
        TabList tabList = (TabList) findComponentById(ResourceTable.Id_bottom_nav_bar);
        for (int i=0; i<menuItems.length; i++){
            TabList.Tab tab = tabList.new Tab(getContext());
            tab.setText(menuItems[i]);
            tabList.addTab(tab);
        }
    }
}
