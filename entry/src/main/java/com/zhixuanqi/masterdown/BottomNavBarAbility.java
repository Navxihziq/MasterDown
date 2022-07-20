package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.slice.BottomNavBarFractionSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;

public class BottomNavBarFraction extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(BottomNavBarFractionSlice.class.getName());
    }
}
