package com.zhixuanqi.masterdown.slice;

import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.zhixuanqi.masterdown.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.multimodalinput.event.KeyEvent;

public class EditorAbilitySlice extends AbilitySlice {
    private String filename;
    @Override
    public void onStart(Intent intent) {
        filename = intent.getStringParam("filename");
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_editor);

        // set the title of the page by the name of the file
        ((Text) findComponentById(ResourceTable.Id_editor_title_text)).setText(filename);

        // todo: bind the cursor change listener
        //
    }

    @Override
    public void onActive() {
        super.onActive();

    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private String reformatText(String text){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(text);
        Formatter formatter = Formatter.builder().build();

        return formatter.render(document);
    }
}
