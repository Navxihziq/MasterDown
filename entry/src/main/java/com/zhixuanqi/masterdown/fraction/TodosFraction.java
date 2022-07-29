package com.zhixuanqi.masterdown.fraction;

import com.zhixuanqi.masterdown.ResourceTable;
import com.zhixuanqi.masterdown.db.Todo;
import com.zhixuanqi.masterdown.provider.TodoProvider;
import com.zhixuanqi.masterdown.util.DatabaseUtil;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.window.dialog.CommonDialog;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;
import org.jsoup.helper.DataUtil;

import java.io.IOException;
import java.util.List;

public class TodosFraction extends Fraction {
    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        return scatter.parse(ResourceTable.Layout_fraction_todos, container, false);
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);

        // bind the new file button to add new to-do entry
        getFractionAbility().findComponentById(ResourceTable.Id_new_file_button).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                // todo: pop up the dialog ask for todo title
                // get resource manager to get the title string
                ResourceManager rscManager = component.getResourceManager();
                // create the dialog for new file
                CommonDialog newTodoDlg = new CommonDialog(component.getContext());
                try {
                    newTodoDlg.setTitleText(rscManager.getElement(ResourceTable.String_newfiledialog_title).getString());
                } catch (IOException | NotExistException | WrongTypeException e) {
                    e.printStackTrace();
                }
                DirectionalLayout ctn_title = (DirectionalLayout) LayoutScatter.getInstance(component.getContext()).parse(ResourceTable.Layout_common_dialog_title, null, false);
                newTodoDlg.setTitleCustomComponent(ctn_title);

                // parse and use custom style
                ComponentContainer ctn_body = (ComponentContainer) LayoutScatter.getInstance(component.getContext()).parse(ResourceTable.Layout_common_dialog_new_file, null, false);
                newTodoDlg.setContentCustomComponent(ctn_body);
                newTodoDlg.setSize(ComponentContainer.LayoutConfig.MATCH_CONTENT, ComponentContainer.LayoutConfig.MATCH_CONTENT);

                // add listeners to both buttons
                Button newTodoBtn = (Button) ctn_body.findComponentById(ResourceTable.Id_create_file_button);
                Button cancel = (Button) ctn_body.findComponentById(ResourceTable.Id_dialog_cancel_button);

                newTodoBtn.setClickedListener(new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        String title = ((TextField)ctn_body.findComponentById(ResourceTable.Id_filename_input)).getText();
                        Todo todo = new Todo(title);
                        DatabaseUtil.addTodo(todo);

                        newTodoDlg.destroy();
                    }
                });

                // close dialog on cancel click
                cancel.setClickedListener(component2->{
                    newTodoDlg.destroy();
                });

                newTodoDlg.show();
            }
        });

        initTodoProvider();

        ((ListContainer)getFractionAbility().findComponentById(ResourceTable.Id_todo_list_container)).setItemClickedListener(new ListContainer.ItemClickedListener() {
            @Override
            public void onItemClicked(ListContainer listContainer, Component component, int i, long l) {
                Todo todo = DatabaseUtil.queryTitle(component.getName());
                if (todo != null){
                    DatabaseUtil.checkTodo(todo);
                }
            }
        });
    }

    public void initTodoProvider(){
        // get the list container component from xml
        ListContainer listContainer = (ListContainer)getFractionAbility().findComponentById(ResourceTable.Id_todo_list_container);
        // instantiate the user file list
        List<Todo> ls = DatabaseUtil.getNotChecked();
        // instantiate the data provider
        TodoProvider provider = new TodoProvider(ls, getFractionAbility());
        // feed the data to the provider
        listContainer.setItemProvider(provider);
    }
}
