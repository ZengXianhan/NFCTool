package com.example.corpit.testnfc.Data;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by corpit on 5/7/2017.
 */

public class DataRequest {
    private void postRequest(String url,Map<String,String> map){
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpUtils okHttpUtils=new OkHttpUtils(okHttpClient);
        okHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
}
