package com.speedingdeer.reactiveandroiddemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.speedingdeer.reactiveandroiddemo.ReactiveAndroidDemo;
import com.speedingdeer.reactiveandroiddemo.events.ConnectionChangedEvent;

import org.greenrobot.eventbus.EventBus;


public class Receiver extends BroadcastReceiver {

    public Receiver() {
        super();
    }

    // listener
    public static String NETWORK_ON = "network_on";
    public static String NETWORK_OFF = "network_off";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {


            // listener demo
            if(ReactiveAndroidDemo.getInstance().isNetworkAvailable()) {
                context.sendBroadcast(new Intent(NETWORK_ON));
            } else {
                context.sendBroadcast(new Intent(NETWORK_OFF));
            }


            // event demo
            EventBus.getDefault().post(new ConnectionChangedEvent());


            // reactive demo
            // @TODO: implement network state observable
        }

    }

}
