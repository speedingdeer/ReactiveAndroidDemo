package com.speedingdeer.reactiveandroiddemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.speedingdeer.reactiveandroiddemo.R;

public class ListenerFragment extends Fragment {

    // classic listener
    private OnListenerFragmentInteractionListener mListener;

    private View mView;
    private Button mButton;

    public ListenerFragment() { } // Required empty public constructor

    public static ListenerFragment newInstance() {
        ListenerFragment fragment = new ListenerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment, container, false);
        mButton = (Button) mView.findViewById(R.id.button);
        return mView;
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListenerFragmentInteractionListener) {
            mListener = (OnListenerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListenerFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
