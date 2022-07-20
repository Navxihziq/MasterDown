package com.zhixuanqi.masterdown;

public class UserFile {
    private boolean file;   // 0: dir, 1: file;
    private String name;

    public UserFile(boolean file, String name) {
        this.file = file;
        this.name = name;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
