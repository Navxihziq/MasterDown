package com.zhixuanqi.masterdown.db;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Column;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "todo")
public class Todo extends OrmObject implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @Column(name="id")
    private Integer id;
    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "checked")
    private Boolean checked;
    @Column(name = "created_time")
    private Date created_time;

    public Todo() {
        super();
    }

    public Todo(Integer id, String title, Boolean checked, Date created_time) {
        this.id = id;
        this.title = title;
        this.checked = checked;
        this.created_time = created_time;
    }

    public Todo(String title) {
        this.title = title;
        this.checked=false;
        this.created_time = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", checked=" + checked +
                ", created_time=" + created_time +
                '}';
    }
}
