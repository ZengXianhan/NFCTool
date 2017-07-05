package com.example.corpit.testnfc.Activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corpit.testnfc.R;
import com.example.corpit.testnfc.Util.CommonFunction;
import com.example.corpit.testnfc.Util.NFCFunction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Time;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NfcAdapter mNfcAdapter;//NFC适配器，就是那个NFC读卡器
    private PendingIntent pendingIntent;//用于截获系统Intent，因为系统检测到NFC卡片时会选择程序去响应
    private IntentFilter[] mIntentFilters;//IntentFilter表示满足本程序响应的条件
    private String[][] mTechLists;
    private TextView tv_tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        tv_tag = (TextView) findViewById(R.id.tv_tag);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
        {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        try {
            String info = NFCFunction.read(getApplicationContext(),tagFromIntent);
            tv_tag.setText(info);
        }catch (Exception e){
            e.printStackTrace();
            tv_tag.setText("Error");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, mIntentFilters, mTechLists);
        }
    }

}
