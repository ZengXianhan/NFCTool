package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.CheckInPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 4/7/2017.
 */

public class CheckInPointDataManager {
    public static List<CheckInPoint> getCheckInPoint(SQLiteDatabase db){
        List<CheckInPoint> list = new ArrayList<CheckInPoint>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_POINT_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckInPoint n = new CheckInPoint();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.pointName = (cursor.getString(cursor.getColumnIndex("pointName")));
                    n.checkInTypeId = (cursor.getString(cursor.getColumnIndex("checkInTypeId")));
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

    public static List<CheckInPoint> getCheckInPoint(SQLiteDatabase db,String checkInTypeId){
        List<CheckInPoint> list = new ArrayList<CheckInPoint>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_POINT_NAME);
        sql.append(" where checkInTypeId = '");
        sql.append(checkInTypeId);
        sql.append("'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckInPoint n = new CheckInPoint();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.pointName = (cursor.getString(cursor.getColumnIndex("pointName")));
                    n.checkInTypeId = (cursor.getString(cursor.getColumnIndex("checkInTypeId")));
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

    public synchronized static void downloadCheckInPointData(SQLiteDatabase db){
        List<CheckInPoint> list = DummyData.getCheckInPointList();
        ContentValues cv = new ContentValues();
        for (CheckInPoint c :
                list) {
            cv.put("id",c.id);
            cv.put("pointName",c.pointName);
            cv.put("checkInTypeId",c.checkInTypeId);
            insertCheckInPoint(cv,db);
        }
    }

    //    插入数据
    public synchronized static void insertCheckInPoint(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_CHECK_IN_POINT_NAME, null, values);
    }
    //    更新数据
    public synchronized static void updateCheckInPoint(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_CHECK_IN_POINT_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deleteCheckInPoint(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_CHECK_IN_POINT_NAME, null, null);
    }

    public synchronized static void deleteCheckInPoint(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_CHECK_IN_POINT_NAME, "id=?", new String[]{id});
    }
}
