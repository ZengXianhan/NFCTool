package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 4/7/2017.
 */

public class PhotoDataManager {
    public static List<Photo> getPhoto(SQLiteDatabase db){
        List<Photo> list = new ArrayList<Photo>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_PHOTO_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    Photo n = new Photo();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.photoName = (cursor.getString(cursor.getColumnIndex("photoName")));
                    n.deviceId = (cursor.getString(cursor.getColumnIndex("deviceId")));
                    n.photographerId = (cursor.getString(cursor.getColumnIndex("photographerId")));
                    n.contentCustomer = (cursor.getString(cursor.getColumnIndex("contentCustomer")));
                    list.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    public synchronized static void downloadPhotoData(SQLiteDatabase db){
        List<Photo> list = DummyData.getPhotoList();
        ContentValues cv = new ContentValues();
        for (Photo c :
                list) {
            cv.put("id",c.id);
            cv.put("photoName",c.photoName);
            cv.put("deviceId",c.deviceId);
            cv.put("photographerId",c.photographerId);
            cv.put("contentCustomer",c.contentCustomer);
            insertPhoto(cv,db);
        }
    }

    //    插入数据
    public synchronized static void insertPhoto(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_PHOTO_NAME, null, values);
    }
    //    更新数据
    public synchronized static void updatePhoto(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_PHOTO_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deletePhoto(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_PHOTO_NAME, null, null);
    }

    public synchronized static void deletePhoto(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_PHOTO_NAME, "id=?", new String[]{id});
    }
}
