package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 3/7/2017.
 */

public class DeviceDataManager {
    public static List<Device> getDevice(SQLiteDatabase db){
        List<Device> deviceList = new ArrayList<Device>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_DEVICE_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    Device n = new Device();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.deviceName = (cursor.getString(cursor.getColumnIndex("deviceName")));
                    deviceList.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return deviceList;
    }

    public synchronized static void downloadDeviceData(SQLiteDatabase db){
        List<Device> deviceList = DummyData.getDeviceList();
        ContentValues cv = new ContentValues();
        for (Device c :
                deviceList) {
            cv.put("id",c.id);
            cv.put("deviceName",c.deviceName);
            insertDevice(cv,db);
        }
    }

    //    插入数据
    public synchronized static void insertDevice(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_DEVICE_NAME, null, values);
    }
    //    更新数据
    public synchronized static void updateDevice(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_DEVICE_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deleteDevice(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_DEVICE_NAME, null, null);
    }

    public synchronized static void deleteDevice(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_DEVICE_NAME, "id=?", new String[]{id});
    }
}
