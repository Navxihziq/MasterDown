package com.zhixuanqi.masterdown.util;

import com.zhixuanqi.masterdown.db.Todo;
import com.zhixuanqi.masterdown.db.TodoDB;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;

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
}