package com.example.corpit.testnfc.DataManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.DummyData;
import com.example.corpit.testnfc.Model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 3/7/2017.
 */

public class CustomerDataManager {
    public static List<Customer> getCustomer(SQLiteDatabase db){
        List<Customer> customerList = new ArrayList<Customer>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CUSTOMER_NAME);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    Customer n = new Customer();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.name = (cursor.getString(cursor.getColumnIndex("name")));
                    n.NFCNumber = (cursor.getString(cursor.getColumnIndex("NFCNumber")));
                    customerList.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return customerList;
    }

    public static List<Customer> getCustomer(SQLiteDatabase db,String NFCNumber){
        List<Customer> customerList = new ArrayList<Customer>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from "+ DatabaseConstants.TABLE_CUSTOMER_NAME);
        sql.append(" where NFCNumber = '");
        sql.append(NFCNumber);
        sql.append("'");
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql.toString(), null);
            if (cursor.moveToFirst()) {
                int cursorCount = cursor.getCount();
                for (int i = 0; i < cursorCount; i++) {
                    Customer n = new Customer();
                    n.id = (cursor.getString(cursor.getColumnIndex("id")));
                    n.name = (cursor.getString(cursor.getColumnIndex("name")));
                    n.NFCNumber = (cursor.getString(cursor.getColumnIndex("NFCNumber")));
                    customerList.add(n);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return customerList;
    }

    public synchronized static void downloadCustomerData(SQLiteDatabase db){
        List<Customer> customerList = DummyData.getCustomer();
        ContentValues cv = new ContentValues();
        for (Customer c :
                customerList) {
            deleteCustomer(db,c.id);
            cv.put("id",c.id);
            cv.put("name",c.name);
            cv.put("NFCNumber",c.NFCNumber);
            insertCustomer(cv,db);
        }
    }

    //    插入数据
    public synchronized static void insertCustomer(ContentValues values, SQLiteDatabase db) {
        db.insert(DatabaseConstants.TABLE_CUSTOMER_NAME, null, values);
    }
    //    更新数据
    public synchronized static void updateCustomer(SQLiteDatabase db, ContentValues values, String ID) {
        db.update(DatabaseConstants.TABLE_CUSTOMER_NAME, values, "ID = '" + ID + "'", null);
    }

    public synchronized static void deleteCustomer(SQLiteDatabase db){
        db.delete(DatabaseConstants.TABLE_CUSTOMER_NAME, null, null);
    }

    public synchronized static void deleteCustomer(SQLiteDatabase db,String id ){
        db.delete(DatabaseConstants.TABLE_CUSTOMER_NAME, "id=?", new String[]{id});
    }
}
