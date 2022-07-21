package com.zhixuanqi.masterdown.listener;

import com.zhixuanqi.masterdown.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.window.dialog.CommonDialog;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;

public class NewFileButtonListener implements Component.ClickedListener {
    @Override
    public void onClick(Component component) {
        ResourceManager rscManager = component.getResourceManager();

        CommonDialog newFileDlg = new CommonDialog(component.getContext());
        try {
            newFileDlg.setTitleText(rscManager.getElement(ResourceTable.String_newfiledialog_title).getString());
        } catch (IOException | NotExistException | WrongTypeException e) {
            e.printStackTrace();
        }

        newFileDlg.show();
    }
}
