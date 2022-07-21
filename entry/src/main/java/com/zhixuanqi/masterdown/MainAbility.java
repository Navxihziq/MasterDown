package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.fraction.FilesFraction;
import com.zhixuanqi.masterdown.fraction.SettingsFraction;
import com.zhixuanqi.masterdown.fraction.TodosFraction;
import com.zhixuanqi.masterdown.slice.MainAbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;


public class MainAbility extends FractionAbility {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        setUIContent(ResourceTable.Layout_ability_main);
        requestPermissionsFromUser(new String[]{"ohos.permission.READ_USER_STORAGE"}, 0);
    }


}

