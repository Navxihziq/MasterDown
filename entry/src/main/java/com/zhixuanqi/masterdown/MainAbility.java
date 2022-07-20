package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.fraction.FilesFraction;
import com.zhixuanqi.masterdown.slice.MainAbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;


public class MainAbility extends FractionAbility {
    private final String [] menuItems = {"File", "Todo", "Settings"};
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        setUIContent(ResourceTable.Layout_ability_main);
        initView();
    }

    public void initView(){
        // get the tab list by id
        TabList tablist = (TabList) findComponentById(ResourceTable.Id_bottom_nav_bar);

        // push everything in the list to the bar
        pushMenuItems(tablist);
        tablist.setFixedMode(true);

        // select the first tab as default
        tablist.getTabAt(0).select();
        switchPage(0);
    }

    public void pushMenuItems(TabList tabList){
        for (String menuItem : menuItems) {
            TabList.Tab tab = tabList.new Tab(getContext());
            tab.setText(menuItem);
            tabList.addTab(tab);
        }
    }

    public void switchPage(int pageNum){
        switch(pageNum){
            case 0:
                // switch to the file panel
                getFractionManager()
                        .startFractionScheduler()
                        .replace(ResourceTable.Id_stack_layout, new FilesFraction())
                        .submit();
                break;
        }
    }
}

