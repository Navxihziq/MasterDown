package com.zhixuanqi.masterdown.util;

import com.zhixuanqi.masterdown.db.Todo;
import com.zhixuanqi.masterdown.db.TodoDB;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;

import java.util.List;

public class DatabaseUtil {
    private static OrmContext ormContext;

    public static void onInitialize(Context ct){

        DatabaseHelper databaseHelper = new DatabaseHelper(ct);
        ormContext = databaseHelper.getOrmContext("TodoDB", "TodoDB.db", TodoDB.class);
    }

    public static boolean addTodo(Todo todo){
        boolean flag = ormContext.insert(todo);
        if(flag){
            flag = ormContext.flush();
        }

        return flag;
    }

    public static Todo queryTitle(String title){
        OrmPredicates ormPredicates = ormContext.where(Todo.class).equalTo("title", title);
        return (Todo) ormContext.query(ormPredicates).get(0);
    }

    public static List<Todo> getNotChecked(){
        OrmPredicates ormPredicates = ormContext.where(Todo.class).equalTo("checked", false);
        return ormContext.query(ormPredicates);
    }

    public static List<Todo> getChecked(){
        OrmPredicates ormPredicates = ormContext.where(Todo.class).equalTo("checked", true);
        return ormContext.query(ormPredicates);
    }

    public static List<Todo> getEveryThing(){
        OrmPredicates ormPredicates = ormContext.where(Todo.class);
        return ormContext.query(ormPredicates);
    }

    public static void checkTodo(Todo todo){
        // reverse the checked field
        todo.setChecked(!todo.getChecked());
        ormContext.update(todo);
        ormContext.flush();
    }
}