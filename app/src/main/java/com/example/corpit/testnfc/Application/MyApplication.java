package com.example.corpit.testnfc.Application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.corpit.testnfc.Data.MyDatabaseHelper;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by corpit on 29/6/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
