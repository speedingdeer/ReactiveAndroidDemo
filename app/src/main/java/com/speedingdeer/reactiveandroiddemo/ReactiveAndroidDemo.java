package com.speedingdeer.reactiveandroiddemo;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ReactiveAndroidDemo extends Application {

    private static ReactiveAndroidDemo mInstance;
    private ConnectivityManager mConnectivityManager;
    // volley queue
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    public static synchronized ReactiveAndroidDemo getInstance() {
        return mInstance;
    }

    // for event demo

    // for reactive demo

    // getters

    public RequestQueue getRequestQueue() { return mRequestQueue; }

    // helpers

    public  boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
