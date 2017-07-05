package com.example.corpit.testnfc.Util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by corpit on 29/6/2017.
 */

public class NFCFunction {
    public static void initNFC(Context context, NfcAdapter mNfcAdapter, PendingIntent pendingIntent,IntentFilter[] mIntentFilters,String[][] mTechLists){
        //获取默认NFC设备
        mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
        if (mNfcAdapter == null) {
            String toast = "NFC is not available on this device.";
            CommonFunction.showToast(context,toast,false);
        }
        //查看NFC是否开启
        else if (!mNfcAdapter.isEnabled()){
            String toastString = "请在系统设置中先启用NFC功能";
            CommonFunction.showToast(context,toastString,false);
        }

        pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mIntentFilters = new IntentFilter[]{new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)};
        mTechLists = new String[][]{new String[]{Ndef.class.getName()}, new String[]{NdefFormatable.class.getName()}
        };

    }

    // 读取方法
    public static String read(Context context, Tag tag) throws Exception {
        if (tag != null) {
            //解析Tag获取到NDEF实例
            Ndef ndef = Ndef.get(tag);
            //打开连接
            ndef.connect();
            //获取NDEF消息
            NdefMessage message = ndef.getNdefMessage();
            //将消息转换成字节数组
            byte[] data = message.toByteArray();
            //将字节数组转换成字符串
            String str = new String(data, Charset.forName(""));
            //关闭连接
            ndef.close();
            return str;
        } else {
            String toastInfo = "设备与nfc卡连接断开，请重新连接...";
            CommonFunction.showToast(context,toastInfo,false);
        }
        return null;
    }

    public static String readFromTag(Intent intent){
        Parcelable[] rawArray = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage mNdefMsg = (NdefMessage)rawArray[0];
        NdefRecord mNdefRecord = mNdefMsg.getRecords()[0];
        try {
            if(mNdefRecord != null){
                String readResult = new String(mNdefRecord.getPayload(),"UTF-8");
                return readResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();

        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.toUpperCase(Character.forDigit(
                    (src[i] >>> 4) & 0x0F, 16));
            buffer[1] = Character.toUpperCase(Character.forDigit(src[i] & 0x0F,
                    16));
            stringBuilder.append(buffer);
            stringBuilder.append(":");
        }
        String finalString = stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
        return finalString;
    }

}
