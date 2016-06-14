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
import com.speedingdeer.reactiveandroiddemo.events.ConnectionChangedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class EventFragment extends Fragment {

    // ui
    private View mView;
    private Button mButton;

    public EventFragment() { } // Required empty public constructor

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

        getActivity().setTitle(R.string.event);

        mView = inflater.inflate(R.layout.fragment, container, false);
        mButton = (Button) mView.findViewById(R.id.button);

        // build ui
        mButton.setEnabled(ReactiveAndroidDemo.getInstance().isNetworkAvailable());

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    // subscribers

    @Subscribe
    public void onConnectionChanged(ConnectionChangedEvent e) {
        mButton.setEnabled(e.isNetworkAvailable());
    }

}
