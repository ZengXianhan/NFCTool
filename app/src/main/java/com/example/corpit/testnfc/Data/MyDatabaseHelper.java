package com.example.corpit.testnfc.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.corpit.testnfc.Util.Logger;

/**
 * Created by corpit on 29/6/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private final static String TAG = "MyDatabaseHelper";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
//        Logger.info(TAG, "my database helper constructor");
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.info(TAG, "Database Create");
        createTable(db);
    }

    // 升級
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.info(TAG, "oldVersion: " + oldVersion + ", newVersion: " + newVersion);
        AppPreference.clearUserPreference(mContext);
//        如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_CUSTOMER_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_CHECK_IN_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_CHECK_IN_POINT_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_PHOTO_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_PHOTOGRAPHER_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseConstants.TABLE_DEVICE_NAME);
        //再次创建表
        onCreate(db);
    }
    private void createTable(SQLiteDatabase db){
        db.execSQL(DatabaseConstants.CREATE_TABLE_CUSTOMER);
        Logger.info(TAG, DatabaseConstants.TABLE_CUSTOMER_NAME +" TABLE CREATED");

        db.execSQL(DatabaseConstants.CREATE_TABLE_CUSTOMER_CHECK_IN_INFO);
        Logger.info(TAG, DatabaseConstants.TABLE_CHECK_IN_NAME +" TABLE CREATED");

        db.execSQL(DatabaseConstants.CREATE_TABLE_CHECK_IN_POINT);
        Logger.info(TAG, DatabaseConstants.TABLE_CHECK_IN_POINT_NAME +" TABLE CREATED");

        db.execSQL(DatabaseConstants.CREATE_TABLE_CHECK_IN_TYPE);
        Logger.info(TAG, DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME +" TABLE CREATED");

        db.execSQL(DatabaseConstants.CREATE_PHOTO);
        Logger.info(TAG, DatabaseConstants.TABLE_PHOTO_NAME +" TABLE CREATED");

        db.execSQL(DatabaseConstants.CREATE_PHOTOGRAPHER);
        Logger.info(TAG, DatabaseConstants.TABLE_PHOTOGRAPHER_NAME +" TABLE CREATED");

        db.execSQL(DatabaseConstants.CREATE_TABLE_DEVICE);
        Logger.info(TAG, DatabaseConstants.TABLE_DEVICE_NAME +" TABLE CREATED");
    }
}

