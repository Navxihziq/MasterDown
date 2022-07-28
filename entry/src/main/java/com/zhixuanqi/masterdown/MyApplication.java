package com.zhixuanqi.masterdown;

import com.zhixuanqi.masterdown.util.DatabaseUtil;
import ohos.aafwk.ability.AbilityPackage;
import ohos.app.Environment;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        // reset the current working firctory to the file's external storage root
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        Preferences preferences = databaseHelper.getPreferences("preferences");
        // put the root path to the preference
        preferences.putString("cwd", getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+"/MasterDown");
        // initialize the database
        DatabaseUtil.onInitialize(this);
    }
}
