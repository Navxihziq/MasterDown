package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;

public class MainAbility extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        showBottomNavIcons();
    }

    public void showBottomNavIcons(){
        TabList tabList = (TabList) findComponentById(ResourceTable.Id_bottom_nav);
        TabList.Tab tab = tabList.new Tab(getContext());
        tab.setText("Test");
    }
}
