package com.zhixuanqi.masterdown.provider;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.UserFile;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;
import java.util.List;

public class UserFileProvider extends BaseItemProvider {
    private final List<UserFile> userFileList;
    private final FractionAbility slice;

    public UserFileProvider(List<UserFile> userFileList, FractionAbility slice) {
        this.userFileList = userFileList;
        this.slice = slice;
    }
    @Override
    public int getCount() {
        // returns the length of the data list
        return userFileList==null ? 0 : userFileList.size();
    }

    @Override
    public Object getItem(int position) {
        if (userFileList != null && position >= 0 && position < userFileList.size()){
            return userFileList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component component, ComponentContainer componentContainer) {
        final Component cpt;
        // 如果还没有convertComponent对象，那么将xml布局文件转为一个Component对象。
        if(component == null){
            //从当前的AbilitySlice对应的xml布局中，
            cpt = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_list_container_item_files,null,false);
        }else{
            cpt = component;
        }
        UserFile file = userFileList.get(position);

        //todo: set the icon based on the value of file.isFile()

        // set the title of the file in the text section
        Text text = (Text) cpt.findComponentById(ResourceTable.Id_file_name_text);
        text.setText(file.getName());

        return cpt;
    }

    @Override
    public void notifyDataChanged() {
        super.notifyDataChanged();
    }
}
