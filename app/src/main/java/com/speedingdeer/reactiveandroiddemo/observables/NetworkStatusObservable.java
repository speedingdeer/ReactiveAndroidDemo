package com.speedingdeer.reactiveandroiddemo.observables;

import com.speedingdeer.reactiveandroiddemo.ReactiveAndroidDemo;

import rx.Observable;
import rx.Subscriber;

public class NetworkStatusObservable implements Observable.OnSubscribe<Boolean> {

    private NetworkStatus mNetworkStatus = new NetworkStatus();

    @Override
    public void call(final Subscriber<? super Boolean> subscriber) {

        mNetworkStatus.setOnChangedListener(new OnChangedListener() {
            @Override
            public void onChanged(boolean status) {
                subscriber.onNext(status);
            }
        });
        // Emit initial value.
        subscriber.onNext(ReactiveAndroidDemo.getInstance().isNetworkAvailable());
    }

    public void statusChanged() {
        mNetworkStatus.changed();
    }


    public class NetworkStatus {

        private boolean mStatus;
        private OnChangedListener mOnChangedListener;

        public NetworkStatus() {
            mStatus = ReactiveAndroidDemo.getInstance().isNetworkAvailable();
        }

        public boolean getStatus() { return mStatus; }
        public void changed() {
            mStatus = ReactiveAndroidDemo.getInstance().isNetworkAvailable();
            if (mOnChangedListener != null) { mOnChangedListener.onChanged(mStatus); }

        }

        public void setOnChangedListener(OnChangedListener listener) {
            this.mOnChangedListener = listener;
        }

    }


    public interface OnChangedListener {
        void onChanged(boolean status);
    }


}
