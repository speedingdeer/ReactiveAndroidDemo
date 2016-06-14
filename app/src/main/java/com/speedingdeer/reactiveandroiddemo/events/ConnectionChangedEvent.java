package com.speedingdeer.reactiveandroiddemo.events;

import com.speedingdeer.reactiveandroiddemo.ReactiveAndroidDemo;

/**
 * The ConnectionChangedEvent carries the current connection status
 */
public class ConnectionChangedEvent extends Event {
    private boolean mNetworkAvailable;

    public ConnectionChangedEvent() {
        mNetworkAvailable = ReactiveAndroidDemo.getInstance().isNetworkAvailable();
    }

    public boolean isNetworkAvailable() { return mNetworkAvailable; }
}
