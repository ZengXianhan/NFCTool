package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.CheckIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 3/7/2017.
 */

public class CheckInDataManager {
    public static List<CheckIn> getCheckIn(SQLiteDatabase db){
        List<CheckIn> checkInList = new ArrayList<CheckIn>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckIn n = new CheckIn();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.checkInTime = (cursor.getString(cursor.getColumnIndex("checkInTime")));
                    n.NFCNumber = (cursor.getString(cursor.getColumnIndex("NFCNumber")));
                    n.checkInPointId = (cursor.getString(cursor.getColumnIndex("checkInPointId")));
                    n.checkInTypeId = (cursor.getString(cursor.getColumnIndex("checkInTypeId")));
                    n.checkInDeviceId = (cursor.getString(cursor.getColumnIndex("checkInDeviceId")));
                    checkInList.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return checkInList;
    }

    public static List<CheckIn> getCheckIn(SQLiteDatabase db, String typeId){
        List<CheckIn> checkInList = new ArrayList<CheckIn>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_NAME);
        sql.append(" where checkInTypeId = '");
        sql.append(typeId);
        sql.append("'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckIn n = new CheckIn();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.checkInTime = (cursor.getString(cursor.getColumnIndex("checkInTime")));
                    n.NFCNumber = (cursor.getString(cursor.getColumnIndex("NFCNumber")));
                    n.checkInPointId = (cursor.getString(cursor.getColumnIndex("checkInPointId")));
                    n.checkInTypeId = (cursor.getString(cursor.getColumnIndex("checkInTypeId")));
                    n.checkInDeviceId = (cursor.getString(cursor.getColumnIndex("checkInDeviceId")));
                    checkInList.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return checkInList;
    }

    public static List<CheckIn> getCheckInByNFCNumber(SQLiteDatabase db, String NFCNumber ,String type){
        List<CheckIn> checkInList = new ArrayList<CheckIn>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CHECK_IN_NAME);
        sql.append(" where NFCNumber = '");
        sql.append(NFCNumber);
        sql.append("' and checkInTypeId = '");
        sql.append(type);
        sql.append("'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    CheckIn n = new CheckIn();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.checkInTime = (cursor.getString(cursor.getColumnIndex("checkInTime")));
                    n.NFCNumber = (cursor.getString(cursor.getColumnIndex("NFCNumber")));
                    n.checkInPointId = (cursor.getString(cursor.getColumnIndex("checkInPointId")));
                    n.checkInTypeId = (cursor.getString(cursor.getColumnIndex("checkInTypeId")));
                    n.checkInDeviceId = (cursor.getString(cursor.getColumnIndex("checkInDeviceId")));
                    checkInList.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return checkInList;
    }

    public synchronized static void downloadCheckInData(SQLiteDatabase db){
        List<CheckIn> checkInList = DummyData.getCheckInInfoList();
        ContentValues cv = new ContentValues();
        for (CheckIn c :
                checkInList) {
            cv.put("id",c.id);
            cv.put("checkInTime",c.checkInTime);
            cv.put("NFCNumber",c.NFCNumber);
            cv.put("checkInPointId",c.checkInPointId);
            cv.put("checkInTypeId",c.checkInTypeId);
            cv.put("checkInDeviceId",c.checkInDeviceId);
            insertCheckIn(cv,db);
        }
    }

//    插入数据
    public synchronized static void insertCheckIn(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_CHECK_IN_NAME, null, values);
    }
//    更新数据
    public synchronized static void updateCheckIn(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_CHECK_IN_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deleteCheckIn(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_CHECK_IN_NAME, null, null);
    }

    public synchronized static void deleteCheckIn(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_CHECK_IN_NAME, "id=?", new String[]{id});
    }

}
