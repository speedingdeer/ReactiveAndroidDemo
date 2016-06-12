package com.speedingdeer.reactiveandroiddemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;


public class Receiver extends BroadcastReceiver {

    public Receiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Log.d(getClass().getSimpleName(), ConnectivityManager.CONNECTIVITY_ACTION);
            // @TODO: implement me, talk to MainActivity and fragments!
        }

    }

}
