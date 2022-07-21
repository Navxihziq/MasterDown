package com.zhixuanqi.masterdown.listener;

import com.zhixuanqi.masterdown.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.window.dialog.CommonDialog;
import ohos.app.Environment;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

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

        // todo: add listeners to both buttons

        newFileDlg.show();
    }
}
