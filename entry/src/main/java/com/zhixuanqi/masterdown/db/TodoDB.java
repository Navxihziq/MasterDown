package com.zhixuanqi.masterdown.db;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDB extends OrmDatabase {

}
