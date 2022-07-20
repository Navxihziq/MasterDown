package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.fraction.FilesFraction;
import com.zhixuanqi.masterdown.slice.MainAbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;


public class MainAbility extends FractionAbility {
    private final String [] menuItems = new String[3];

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        setUIContent(ResourceTable.Layout_ability_main);
        requestPermissionsFromUser(new String[]{"ohos.permission.READ_USER_STORAGE"}, 0);
        try {
            initView();
        } catch (NotExistException | WrongTypeException | IOException e) {
            e.printStackTrace();
        }
    }

    public void initView() throws NotExistException, WrongTypeException, IOException {
        // get the tab list by id
        TabList tablist = (TabList) findComponentById(ResourceTable.Id_bottom_nav_bar);
        getMenuItems();
        // push everything in the list to the bar
        pushMenuItems(tablist);
        tablist.setFixedMode(true);

        // select the first tab as default
        tablist.getTabAt(0).select();
        switchPage(0);
    }

    public void getMenuItems() throws NotExistException, WrongTypeException, IOException {
        ResourceManager rscManager = this.getResourceManager();
        // get the strings (multi-lingual support)
        this.menuItems[0] = rscManager.getElement(ResourceTable.String_mainability_FileTab).getString();
        this.menuItems[1] = rscManager.getElement(ResourceTable.String_mainability_TodoTab).getString();
        this.menuItems[2] = rscManager.getElement(ResourceTable.String_mainability_SettingsTab).getString();
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

