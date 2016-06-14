package com.speedingdeer.reactiveandroiddemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.speedingdeer.reactiveandroiddemo.events.ConnectionChangedEvent;

import org.greenrobot.eventbus.EventBus;


public class Receiver extends BroadcastReceiver {

    public Receiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            // event demo
            EventBus.getDefault().post(new ConnectionChangedEvent());
            // reactive demo
            // @TODO: implement network state observable
        }

    }

}
