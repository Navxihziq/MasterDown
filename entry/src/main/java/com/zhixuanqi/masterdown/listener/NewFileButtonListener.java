package com.zhixuanqi.masterdown.listener;

import com.zhixuanqi.masterdown.ResourceTable;
import ohos.agp.components.*;
import ohos.agp.window.dialog.CommonDialog;
import ohos.app.Environment;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

import java.io.File;
import java.io.IOException;

public class NewFileButtonListener implements Component.ClickedListener {
    @Override
    public void onClick(Component component) {
        // get resource manager to get the title string
        ResourceManager rscManager = component.getResourceManager();
        // create the dialog for new file
        CommonDialog newFileDlg = new CommonDialog(component.getContext());
        try {
            newFileDlg.setTitleText(rscManager.getElement(ResourceTable.String_newfiledialog_title).getString());
        } catch (IOException | NotExistException | WrongTypeException e) {
            e.printStackTrace();
        }
        DirectionalLayout ctn_title = (DirectionalLayout) LayoutScatter.getInstance(component.getContext()).parse(ResourceTable.Layout_common_dialog_title, null, false);
        newFileDlg.setTitleCustomComponent(ctn_title);

        // parse and use custom style
        ComponentContainer ctn_body = (ComponentContainer) LayoutScatter.getInstance(component.getContext()).parse(ResourceTable.Layout_common_dialog_new_file, null, false);
        newFileDlg.setContentCustomComponent(ctn_body);
        newFileDlg.setSize(ComponentContainer.LayoutConfig.MATCH_CONTENT, ComponentContainer.LayoutConfig.MATCH_CONTENT);

        // add listeners to both buttons
        Button newFileBtn = (Button) ctn_body.findComponentById(ResourceTable.Id_create_file_button);
        Button cancel = (Button) ctn_body.findComponentById(ResourceTable.Id_dialog_cancel_button);

        // create new file on click
        newFileBtn.setClickedListener(component1 -> {
            // get the name for the new file
            String newFilename = ((TextField)ctn_body.findComponentById(ResourceTable.Id_filename_input)).getText();
            // create file
            DatabaseHelper databaseHelper = new DatabaseHelper(ctn_body.getContext());
            Preferences preferences = databaseHelper.getPreferences("preferences");
            String cwd = preferences.getString("cwd", ctn_body.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
            File newFile = new File(cwd, newFilename);
            try {
                if (!newFile.createNewFile()){
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // destroy the dialog
            newFileDlg.destroy();
        });

        // close dialog on cancel click
        cancel.setClickedListener(component2->{
            newFileDlg.destroy();
        });

        newFileDlg.show();
    }
}
