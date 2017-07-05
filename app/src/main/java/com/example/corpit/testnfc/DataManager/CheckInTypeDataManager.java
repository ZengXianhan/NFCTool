package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.CheckInType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 4/7/2017.
 */

public class CheckInTypeDataManager {
    public static List<CheckInType> getCheckInType(SQLiteDatabase db){
        List<CheckInType> list = new ArrayList<CheckInType>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckInType n = new CheckInType();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.typeName = (cursor.getString(cursor.getColumnIndex("typeName")));
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

    public static String getCheckInTypeId(SQLiteDatabase db,String typeName){
        List<CheckInType> list = new ArrayList<CheckInType>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME);
        sql.append(" where typeName = '");
        sql.append(typeName);
        sql.append("'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckInType n = new CheckInType();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.typeName = (cursor.getString(cursor.getColumnIndex("typeName")));
                    list.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        String checkInTypeId = "";
        if (list.size()>0){
            checkInTypeId = list.get(0).id;
        }
        return checkInTypeId;
    }

    public static String getCheckInTypeName(SQLiteDatabase db,String id){
        List<CheckInType> list = new ArrayList<CheckInType>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME);
        sql.append(" where id = '");
        sql.append(id);
        sql.append("'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckInType n = new CheckInType();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.typeName = (cursor.getString(cursor.getColumnIndex("typeName")));
                    list.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        String typeName = "";
        if (list.size()>0){
            typeName = list.get(0).typeName;
        }
        return typeName;
    }

    public synchronized static void downloadCheckInTypeData(SQLiteDatabase db){
        List<CheckInType> list = DummyData.getCheckInTypeList();
        ContentValues cv = new ContentValues();
        for (CheckInType c :
                list) {
            cv.put("id",c.id);
            cv.put("typeName",c.typeName);
            insertCheckInType(cv,db);
        }
    }

    //    插入数据
    public synchronized static void insertCheckInType(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME, null, values);
    }
    //    更新数据
    public synchronized static void updateCheckInType(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deleteCheckInType(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME, null, null);
    }

    public synchronized static void deleteCheckInType(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_CHECK_IN_TYPE_NAME, "id=?", new String[]{id});
    }
}
