package com.example.corpit.testnfc.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.corpit.testnfc.Adapter.CheckInAdapter;
import com.example.corpit.testnfc.Data.MyDatabaseHelper;
import com.example.corpit.testnfc.DataManager.CheckInDataManager;
import com.example.corpit.testnfc.Model.CheckIn;
import com.example.corpit.testnfc.R;
import com.example.corpit.testnfc.Util.CommonFunction;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

public class CheckInListActivity extends AutoLayoutActivity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private ListView lv_check_in_list;
    private CheckInAdapter adapter;

    private List<CheckIn> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_list);

        //Database
        dbHelper = CommonFunction.createOrUseDataBase(this);
        db = dbHelper.getWritableDatabase();

        initView();
        initData();
        initEvent();
    }

    private void initView(){
        lv_check_in_list = (ListView) findViewById(R.id.lv_check_in_list);
    }

    private void initData(){
        data = CheckInDataManager.getCheckIn(db);
        adapter = new CheckInAdapter(getApplicationContext(),data,db);
        lv_check_in_list.setAdapter(adapter);
    }

    private void initEvent(){

    }
}
