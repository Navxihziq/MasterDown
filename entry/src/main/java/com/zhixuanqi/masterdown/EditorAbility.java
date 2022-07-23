package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.slice.EditorAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class EditorAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(EditorAbilitySlice.class.getName());
    }
}
