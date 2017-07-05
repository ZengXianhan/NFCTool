package com.example.corpit.testnfc.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.corpit.testnfc.Adapter.CheckInAdapter;
import com.example.corpit.testnfc.Data.AppPreference;
import com.example.corpit.testnfc.Data.DataRequest;
import com.example.corpit.testnfc.Data.MyDatabaseHelper;
import com.example.corpit.testnfc.DataManager.CheckInDataManager;
import com.example.corpit.testnfc.DataManager.CheckInTypeDataManager;
import com.example.corpit.testnfc.DataManager.DeviceDataManager;
import com.example.corpit.testnfc.Model.CheckIn;
import com.example.corpit.testnfc.Model.JsonModel.ConfigurationTest;
import com.example.corpit.testnfc.Model.JsonModel.JsonDemo;
import com.example.corpit.testnfc.R;
import com.example.corpit.testnfc.Util.CommonFunction;
import com.example.corpit.testnfc.Util.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class CheckInListActivity extends AutoLayoutActivity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private ListView lv_check_in_list;
    private Button btn_upload_data,btn_logout;
    private TextView text_device_info,text_title;

    private CheckInAdapter adapter;
    private List<CheckIn> data;

    private String postResult;

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
        btn_upload_data = (Button) findViewById(R.id.btn_upload_data);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        text_device_info = (TextView) findViewById(R.id.text_device_info);
        text_title = (TextView) findViewById(R.id.text_title);
        btn_logout.setVisibility(View.INVISIBLE);
    }

    private void initData(){
        //ListView Data
        data = CheckInDataManager.getCheckIn(db, AppPreference.getPref(getApplicationContext(),AppPreference.CHECK_IN_TYPE));
        adapter = new CheckInAdapter(getApplicationContext(),data,db);
        lv_check_in_list.setAdapter(adapter);
        //Title Bar Data
        String title = CheckInTypeDataManager.getCheckInTypeName(db, AppPreference.getPref(getApplicationContext(), AppPreference.CHECK_IN_TYPE)) ;
        text_title.setText(title);
        String device = DeviceDataManager.getDeviceName(db,AppPreference.getPref(getApplicationContext(), AppPreference.DEVICE));
        text_device_info.setText(device);
    }

    private void initEvent(){
        btn_upload_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
    }

    private void uploadData(){
        String url = "http://203.125.155.188/StandardMobile/apiv1/masterapi";
        Map<String,String> map = new HashMap<>();
        map.put("page","Configuration");
        postRequest(url,map);
    }

    private void postRequest(String url,Map<String,String> map){
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpUtils okHttpUtils=new OkHttpUtils(okHttpClient);
        okHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response, int id) {
                postResult = response;
                Logger.info("TAG!!!",postResult);
                //test GSON
                Gson gson = new Gson();
                Type listType = new TypeToken<JsonDemo>(){}.getType();
                JsonDemo jsonResult = gson.fromJson(postResult,listType);
                List<ConfigurationTest> configurationTests = jsonResult.data;
                int c = configurationTests.size();
            }
        });
    }
}
