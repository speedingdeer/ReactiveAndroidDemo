package com.speedingdeer.reactiveandroiddemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.speedingdeer.reactiveandroiddemo.R;
import com.speedingdeer.reactiveandroiddemo.ReactiveAndroidDemo;

import rx.Observable;
import rx.functions.Action1;


public class ReactiveFragment extends Fragment {

    // ui
    private View mView;
    private Button mButton;

    public ReactiveFragment() { } // Required empty public constructor

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle(R.string.reactive);

        mView = inflater.inflate(R.layout.fragment, container, false);
        mButton = (Button) mView.findViewById(R.id.button);

        // build ui
        mButton.setEnabled(ReactiveAndroidDemo.getInstance().isNetworkAvailable());

        // subscribe rx
        Observable.create(ReactiveAndroidDemo.getInstance().getNetworkStatusObservable()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mButton.setEnabled(aBoolean);
            }
        });

        return mView;
    }

}
