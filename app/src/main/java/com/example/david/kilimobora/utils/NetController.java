package com.example.david.kilimobora.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetController {


    public   AsyncHttpClient client;

    public NetController(){
            client=new AsyncHttpClient(true,80,443);
        client.addHeader("Accept", "application/json");
    }

    public   void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url),params,responseHandler);
    }

    public  void post(String url,RequestParams params,JsonHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url),params,responseHandler);
    }
    private  String getAbsoluteUrl(String relativeUrl){
        return  CoreUtils.base_url+relativeUrl;
    }
}
