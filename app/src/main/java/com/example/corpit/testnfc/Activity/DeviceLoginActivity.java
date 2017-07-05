package com.example.corpit.testnfc.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.corpit.testnfc.Data.AppPreference;
import com.example.corpit.testnfc.Data.MyDatabaseHelper;
import com.example.corpit.testnfc.DataManager.CheckInPointDataManager;
import com.example.corpit.testnfc.DataManager.CheckInTypeDataManager;
import com.example.corpit.testnfc.DataManager.DeviceDataManager;
import com.example.corpit.testnfc.DataManager.PhotographerDataManager;
import com.example.corpit.testnfc.Model.CheckInPoint;
import com.example.corpit.testnfc.Model.CheckInType;
import com.example.corpit.testnfc.Model.Device;
import com.example.corpit.testnfc.Model.Photographer;
import com.example.corpit.testnfc.R;
import com.example.corpit.testnfc.Util.CommonFunction;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 29/6/2017.
 */

public class DeviceLoginActivity extends AutoLayoutActivity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private Button btn_enter;
    private Spinner sp_login_device;
    private Spinner sp_login_feature;
    private Spinner sp_login_check_in_type;
    private Spinner sp_login_check_in_point;
    private Spinner sp_photographer;
    private RelativeLayout rl_check_in_option;
    private RelativeLayout rl_photo_option;
    private ArrayAdapter adapter4;
    private ArrayAdapter adapter5;

    private long clickTime = 0; // 第一次点击的时间

    private String selectedDevice = new String();
    private String selectedFeature = new String();
    private String selectedType = new String();
    private String selectedPoint = new String();
    private String selectedPhotographer = new String();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_login);
        dbHelper = CommonFunction.createOrUseDataBase(this);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        selectedType = getCheckInTypeData(db).get(0);
        initView();
        initData();
        initEvent();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        rl_check_in_option = (RelativeLayout) findViewById(R.id.rl_check_in_option);
        rl_photo_option = (RelativeLayout) findViewById(R.id.rl_photo_option);
        sp_login_device = (Spinner) findViewById(R.id.sp_login_device);
        sp_login_feature = (Spinner) findViewById(R.id.sp_login_feature);
        sp_login_check_in_type = (Spinner) findViewById(R.id.sp_login_check_in_type);
        sp_login_check_in_point = (Spinner) findViewById(R.id.sp_login_check_in_point);
        sp_photographer = (Spinner) findViewById(R.id.sp_photographer);
        btn_enter = (Button) findViewById(R.id.btn_login_enter);

        if (selectedFeature.contains("Check In")) {
            rl_check_in_option.setVisibility(View.VISIBLE);
            rl_photo_option.setVisibility(View.GONE);
        } else if (selectedFeature.contains("Taking Photo")) {
            rl_check_in_option.setVisibility(View.GONE);
            rl_photo_option.setVisibility(View.VISIBLE);
        } else {
            rl_check_in_option.setVisibility(View.VISIBLE);
            rl_photo_option.setVisibility(View.GONE);
        }
    }

    private void initData() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                getDeviceData(db));
        sp_login_device.setAdapter(adapter);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                getFeatureData());
        sp_login_feature.setAdapter(adapter2);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                getCheckInTypeData(db));
        sp_login_check_in_type.setAdapter(adapter3);
        adapter4 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                getCheckInPointData(db, selectedType));
        sp_login_check_in_point.setAdapter(adapter4);
        adapter5 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                getPhotographerData(db));
        sp_photographer.setAdapter(adapter5);
    }

    private void updateAdapter() {
        adapter4 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,
                getCheckInPointData(db, selectedType));
        sp_login_check_in_point.setAdapter(adapter4);
    }

    private void initEvent() {
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreference.updatePref(getApplicationContext(), AppPreference.DEVICE, DeviceDataManager.getDeviceId(db, selectedDevice));
                AppPreference.updatePref(getApplicationContext(), AppPreference.FEATURE, selectedFeature);
                Intent intent = new Intent();
                switch (selectedFeature) {
                    case "Check In":
                        AppPreference.updatePref(getApplicationContext(), AppPreference.CHECK_IN_TYPE, CheckInTypeDataManager.getCheckInTypeId(db, selectedType));
                        AppPreference.updatePref(getApplicationContext(), AppPreference.CHECK_IN_POINT, CheckInPointDataManager.getCheckInPointId(db, selectedPoint));
                        if (selectedType.contains("Hotel")) {
                            intent = new Intent(getApplicationContext(), CheckInActivity.class);
                        } else if (selectedType.contains("Gala")) {
                            intent = new Intent(getApplicationContext(), CheckInActivity.class);
                        } else if (selectedType.contains("Drive")) {
                            intent = new Intent(getApplicationContext(), CheckInActivity.class);
                        }
                        break;
                    case "Taking Photo":
                        AppPreference.updatePref(getApplicationContext(), AppPreference.PHOTOGRAPHER, selectedPhotographer);
                        intent = new Intent(getApplicationContext(), CheckInActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });

        sp_login_device.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDevice = getDeviceData(db).get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedDevice = getDeviceData(db).get(0);
            }
        });

        sp_login_feature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFeature = getFeatureData().get(position);
                initView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedFeature = getFeatureData().get(0);
            }
        });

        sp_login_check_in_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = getCheckInTypeData(db).get(position);
                updateAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedType = getCheckInTypeData(db).get(0);

            }
        });

        sp_login_check_in_point.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPoint = getCheckInPointData(db, selectedType).get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPoint = getCheckInPointData(db, selectedType).get(0);

            }
        });

        sp_photographer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPhotographer = getPhotographerData(db).get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPhotographer = getPhotographerData(db).get(0);

            }
        });
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            String toast = "再按一次后退键退出程序";
            CommonFunction.showToast(this, toast, false);
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }

    private ArrayList<String> getDeviceData(SQLiteDatabase db) {
        ArrayList<String> data = new ArrayList<>();
        List<Device> list = DeviceDataManager.getDevice(db);
        if (list.size() > 0) {
            for (Device d :
                    list) {
                data.add(d.deviceName);
            }
        }
        return data;
    }

    private ArrayList<String> getFeatureData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Check In");
        data.add("Taking Photo");
        return data;
    }

    private ArrayList<String> getCheckInTypeData(SQLiteDatabase db) {
        ArrayList<String> data = new ArrayList<>();
        List<CheckInType> list = CheckInTypeDataManager.getCheckInType(db);
        if (list.size() > 0) {
            for (CheckInType d :
                    list) {
                data.add(d.typeName);
            }
        }
        return data;
    }

    private ArrayList<String> getCheckInPointData(SQLiteDatabase db, String checkInTypeName) {
        String checkInTypeId = CheckInTypeDataManager.getCheckInTypeId(db, checkInTypeName);
        ArrayList<String> data = new ArrayList<>();
        List<CheckInPoint> list = CheckInPointDataManager.getCheckInPoint(db, checkInTypeId);
        if (list.size() > 0) {
            for (CheckInPoint d :
                    list) {
                data.add(d.pointName);
            }
        }
        return data;
    }

    private ArrayList<String> getPhotographerData(SQLiteDatabase db) {
        ArrayList<String> data = new ArrayList<>();
        List<Photographer> list = PhotographerDataManager.getPhotographer(db);
        if (list.size() > 0) {
            for (Photographer d :
                    list) {
                data.add(d.name);
            }
        }
        return data;
    }
}
