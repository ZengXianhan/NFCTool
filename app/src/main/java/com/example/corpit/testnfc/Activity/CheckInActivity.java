package com.example.corpit.testnfc.Activity;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.corpit.testnfc.Data.AppPreference;
import com.example.corpit.testnfc.Data.MyDatabaseHelper;
import com.example.corpit.testnfc.DataManager.CheckInDataManager;
import com.example.corpit.testnfc.DataManager.CustomerDataManager;
import com.example.corpit.testnfc.Model.CheckIn;
import com.example.corpit.testnfc.Model.Customer;
import com.example.corpit.testnfc.R;
import com.example.corpit.testnfc.Util.CommonFunction;
import com.example.corpit.testnfc.Util.NFCFunction;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

public class CheckInActivity extends AutoLayoutActivity {
    private NfcAdapter mNfcAdapter;//NFC适配器，就是那个NFC读卡器
    private PendingIntent pendingIntent;//用于截获系统Intent，因为系统检测到NFC卡片时会选择程序去响应
    private IntentFilter[] mIntentFilters;//IntentFilter表示满足本程序响应的条件
    private String[][] mTechLists;

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private TextView tv_customer_id,tv_customer_name,tv_customer_time,tv_count_number;
    private Button btn_view_list;
    private TextView text_device_info,text_title;
    private Button btn_logout;

    private String NFCNumber = new String("");
    private String customerName = new String("");
    private String checkInTime = new String("");
    private List<Customer> customerList;

    private long clickTime = 0; // 第一次点击的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        //init NFC
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            String toast = "NFC is not available on this device.";
            CommonFunction.showToast(this, toast, false);
        }
        //查看NFC是否开启
        else if (!mNfcAdapter.isEnabled()) {
            String toastString = "Please open NFC feature";
            CommonFunction.showToast(this, toastString, false);
        }
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mIntentFilters = new IntentFilter[]{new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)};
        mTechLists = new String[][]{new String[]{Ndef.class.getName()}, new String[]{NdefFormatable.class.getName()}
        };

        //Database
        dbHelper = CommonFunction.createOrUseDataBase(this);
        db = dbHelper.getWritableDatabase();

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        tv_count_number = (TextView) findViewById(R.id.tv_count_number);
        tv_customer_id = (TextView) findViewById(R.id.tv_customer_id);
        tv_customer_name = (TextView) findViewById(R.id.tv_customer_name);
        tv_customer_time = (TextView) findViewById(R.id.tv_customer_time);
        btn_view_list = (Button) findViewById(R.id.btn_view_list);
        //Title Bar
        text_device_info = (TextView) findViewById(R.id.text_device_info);
        text_title = (TextView) findViewById(R.id.text_title);
        btn_logout = (Button) findViewById(R.id.btn_logout);
    }

    private void initData() {
        String title = AppPreference.getPref(getApplicationContext(),AppPreference.FEATURE);
        text_title.setText(title);
        String device = AppPreference.getPref(getApplicationContext(),AppPreference.DEVICE);
        text_device_info.setText(device);

        if (NFCNumber.length()<1){
            tv_customer_id.setVisibility(View.INVISIBLE);
        }else {
            List<Customer> customers = CustomerDataManager.getCustomer(db,NFCNumber);
            String customerId = customers.get(0).id;
            tv_customer_id.setText("Customer ID: "+ customerId);
            tv_customer_id.setVisibility(View.VISIBLE);
        }

        if (customerName.length()<1){
            tv_customer_name.setVisibility(View.INVISIBLE);
        }else {
            tv_customer_name.setText("Customer Name: "+ customerName);
            tv_customer_name.setVisibility(View.VISIBLE);
        }

        if (checkInTime.length()<1){
            tv_customer_time.setVisibility(View.INVISIBLE);
        }else {
            tv_customer_time.setText("Check in time: "+ checkInTime);
            tv_customer_time.setVisibility(View.VISIBLE);
        }

        tv_count_number.setText("The total number of check in is "+ CheckInDataManager.getCheckIn(db).size());
    }

    private void initEvent(){
        btn_view_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CheckInListActivity.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreference.clearUserPreference(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(),DeviceLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] dataId = tag.getId();
            String id = NFCFunction.bytesToHexString(dataId);
            customerList = CustomerDataManager.getCustomer(db,id);
            if (customerList.size()>0){
                customerName = customerList.get(0).name;
                NFCNumber = id;
                String time = CommonFunction.getCurrentTime();
                checkInTime = time;
                newCheckIn(db, id, time);
                initData();
            }else {
                CommonFunction.showToast(getApplicationContext(), "Invalid Customer", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CommonFunction.showToast(getApplicationContext(), "Invalid Card", false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, mIntentFilters, mTechLists);
        }
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            String toast = "Tap again to exit";
            CommonFunction.showToast(this,toast,false);
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }

    private void newCheckIn(SQLiteDatabase db, String id, String time) {
        CheckIn checkIn = new CheckIn(time, id);
        if (CheckInDataManager.getCheckInByNFCNumber(db, checkIn.NFCNumber).size() == 0) {
            ContentValues cv = new ContentValues();
            cv.put("checkInTime", checkIn.checkInTime);
            cv.put("NFCNumber", checkIn.NFCNumber);
            CheckInDataManager.insertCheckIn(cv, db);
        } else {
            CommonFunction.showToast(getApplicationContext(), "This card has already check in.", false);
            checkInTime = CheckInDataManager.getCheckInByNFCNumber(db,checkIn.NFCNumber).get(0).checkInTime;
        }
    }

}
