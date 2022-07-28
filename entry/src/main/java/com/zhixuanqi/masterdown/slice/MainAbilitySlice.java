package com.zhixuanqi.masterdown.slice;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.UserFile;
import com.zhixuanqi.masterdown.fraction.FilesFraction;
import com.zhixuanqi.masterdown.fraction.SettingsFraction;
import com.zhixuanqi.masterdown.fraction.TodosFraction;
import com.zhixuanqi.masterdown.provider.UserFileProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    private final String [] menuItems = new String[3];

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // initiate the navigation bar
        try {
            initView();
        } catch (NotExistException | WrongTypeException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    public void initView() throws NotExistException, WrongTypeException, IOException {
        // get the tab list by id
        TabList tablist = (TabList) findComponentById(ResourceTable.Id_bottom_nav_bar);
        // read the items based on configs
        getMenuItems();
        // push everything in the list to the bar
        pushMenuItems(tablist);
        tablist.setFixedMode(true);

        // add event listener to the tab list
        tablist.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                switchPage(tab.getPosition());
            }

            @Override
            public void onUnselected(TabList.Tab tab) {

            }

            @Override
            public void onReselected(TabList.Tab tab) {

            }
        });

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
        // change the title in the header to file
        ((Text)findComponentById(ResourceTable.Id_header_title_text)).setText(menuItems[pageNum]);

        switch(pageNum){
            case 0:
                // switch to the file panel
                ((FractionAbility)getAbility()).getFractionManager()
                        .startFractionScheduler()
                        .replace(ResourceTable.Id_stack_layout, new FilesFraction())
                        .submit();

                // add new file button to the button area in header
                getAbility().findComponentById(ResourceTable.Id_new_file_button).setVisibility(Component.VISIBLE);

                break;

            case 1:
                // switch to to-do panel
                ((FractionAbility)getAbility()).getFractionManager()
                        .startFractionScheduler()
                        .replace(ResourceTable.Id_stack_layout, new TodosFraction())
                        .submit();

                // make the add to-do entry (file) button
                getAbility().findComponentById(ResourceTable.Id_new_file_button).setVisibility(Component.VISIBLE);

                break;

            case 2:
                // switch to settings panel
                ((FractionAbility)getAbility()).getFractionManager()
                        .startFractionScheduler()
                        .replace(ResourceTable.Id_stack_layout, new SettingsFraction())
                        .submit();

                // remove the add file button
                getAbility().findComponentById(ResourceTable.Id_new_file_button).setVisibility(Component.INVISIBLE);
                break;
        }
    }
}
