package com.zhixuanqi.masterdown.slice;

import com.zhixuanqi.masterdown.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;

public class EditorAbilitySlice extends AbilitySlice {
    private String filename;
    @Override
    public void onStart(Intent intent) {
        filename = intent.getStringParam("filename");
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_editor);

        // todo: delete this debug session afterwards
        ((Text) findComponentById(ResourceTable.Id_editor_title_text)).setText(filename);
    }

    @Override
    public void onActive() {
        super.onActive();

    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
