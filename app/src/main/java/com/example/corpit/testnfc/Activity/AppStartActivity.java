package com.example.corpit.testnfc.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.corpit.testnfc.Data.AppPreference;
import com.example.corpit.testnfc.Data.DatabaseConstants;
import com.example.corpit.testnfc.Data.MyDatabaseHelper;
import com.example.corpit.testnfc.DataManager.CheckInPointDataManager;
import com.example.corpit.testnfc.DataManager.CheckInTypeDataManager;
import com.example.corpit.testnfc.DataManager.CustomerDataManager;
import com.example.corpit.testnfc.DataManager.DeviceDataManager;
import com.example.corpit.testnfc.DataManager.PhotographerDataManager;
import com.example.corpit.testnfc.Model.Photographer;
import com.example.corpit.testnfc.R;
import com.example.corpit.testnfc.Util.CommonFunction;

/**
 * Created by corpit on 29/6/2017.
 */

public class AppStartActivity extends Activity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
        //Database
        dbHelper = CommonFunction.createOrUseDataBase(this);
        db = dbHelper.getWritableDatabase();
        downloadData(db);
        dispatchIntent();
        this.finish();
    }

    private void dispatchIntent(){
        String feature = AppPreference.getPref(this,AppPreference.FEATURE);
        Intent intent = new Intent(this, DeviceLoginActivity.class);
        if (feature !=null){
            switch (feature){
                case "Check In":
                    intent = new Intent(getApplicationContext(),CheckInActivity.class);
                    break;
                case "Taking Photo":
                    intent = new Intent(getApplicationContext(),CheckInActivity.class);
                    break;
            }
        }else {
            intent = new Intent(getApplicationContext(),DeviceLoginActivity.class);
        }
        startActivity(intent);
    }

    //download data from server
    private void downloadData(SQLiteDatabase db){
        DeviceDataManager.downloadDeviceData(db);
        CustomerDataManager.downloadCustomerData(db);
        CheckInTypeDataManager.downloadCheckInTypeData(db);
        CheckInPointDataManager.downloadCheckInPointData(db);
        PhotographerDataManager.downloadPhotographerData(db);
    }

}
