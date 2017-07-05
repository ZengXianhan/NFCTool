package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.Photographer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 4/7/2017.
 */

public class PhotographerDataManager {
    public static List<Photographer> getPhotographer(SQLiteDatabase db){
        List<Photographer> list = new ArrayList<Photographer>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_PHOTOGRAPHER_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    Photographer n = new Photographer();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.name = (cursor.getString(cursor.getColumnIndex("name")));
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

    public synchronized static void downloadPhotographerData(SQLiteDatabase db){
        List<Photographer> list = DummyData.getPhotographerList();
        ContentValues cv = new ContentValues();
        for (Photographer c :
                list) {
            cv.put("id",c.id);
            cv.put("name",c.name);
            insertPhotographer(cv,db);
        }
    }

    //    插入数据
    public synchronized static void insertPhotographer(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_PHOTOGRAPHER_NAME, null, values);
    }
    //    更新数据
    public synchronized static void updatePhotographer(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_PHOTOGRAPHER_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deletePhotographer(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_PHOTOGRAPHER_NAME, null, null);
    }

    public synchronized static void deletePhotographer(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_PHOTOGRAPHER_NAME, "id=?", new String[]{id});
    }
}
