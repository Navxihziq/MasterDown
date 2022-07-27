package com.zhixuanqi.masterdown.slice;

import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.zhixuanqi.masterdown.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.app.Environment;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;
import ohos.global.resource.Resource;
import ohos.multimodalinput.event.KeyEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class EditorAbilitySlice extends AbilitySlice {
    private String filename;
    private String cwd;
    @Override
    public void onStart(Intent intent) {
        filename = intent.getStringParam("filename");
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        Preferences preferences = databaseHelper.getPreferences("preferences");
        cwd = preferences.getString("cwd", getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());

        // initiate the ability
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_editor);

        // set the title of the page by the name of the file
        ((Text) findComponentById(ResourceTable.Id_editor_title_text)).setText(filename);
        
        // write the content of the file to the text field
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(cwd+"/"+filename), StandardCharsets.UTF_8)){
            StringBuilder text = null;
            String str = null;
            while((str=bufferedReader.readLine())!=null){
                System.out.println(str);
                text.append(str).append("\n");
            }
            ((TextField) findComponentById(ResourceTable.Id_editor_textfield)).setText(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // todo: bind the save button to save the editing file
        findComponentById(ResourceTable.Id_editor_save_button).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                // get the text from the text field
                String text = ((TextField)findComponentById(ResourceTable.Id_editor_textfield)).getText();
                // get the file descriptor
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get( cwd+"/"+filename), StandardCharsets.UTF_8, StandardOpenOption.WRITE)){
                    bufferedWriter.write(text);
                    System.out.println("File Successfully Written");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // todo: bind the cursor change listener
        // try to bind the functionality to the format button
        findComponentById(ResourceTable.Id_editor_reformat_button).setClickedListener(component -> {
            TextField textField = (TextField) findComponentById(ResourceTable.Id_editor_textfield);
            String input = textField.getText();
            System.out.println(reformatText(input));    // todo: debugging session
            textField.setText(reformatText(input));
        });
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
        HtmlRenderer renderer = HtmlRenderer.builder().build(); // todo: debugging session

        return renderer.render(document);
    }
}
