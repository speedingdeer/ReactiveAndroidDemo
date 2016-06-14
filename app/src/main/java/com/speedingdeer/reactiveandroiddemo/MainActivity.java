package com.speedingdeer.reactiveandroiddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.speedingdeer.reactiveandroiddemo.events.ConnectionChangedEvent;
import com.speedingdeer.reactiveandroiddemo.fragments.EventFragment;
import com.speedingdeer.reactiveandroiddemo.fragments.ListenerFragment;
import com.speedingdeer.reactiveandroiddemo.fragments.ReactiveFragment;
import com.speedingdeer.reactiveandroiddemo.receivers.Receiver;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity
        implements ListenerFragment.OnListenerFragmentInteractionListener, // Listener Demo
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // build UI - select the first item
        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Class fragmentClass = null;
        switch (item.getItemId()) {
            case R.id.nav_listener: fragmentClass = ListenerFragment.class; break;
            case R.id.nav_event: fragmentClass = EventFragment.class; break;
            case R.id.nav_reactive: fragmentClass = ReactiveFragment.class; break;
        }
        if (fragmentClass == null) { return false; } // should never happen

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentClass.getCanonicalName());
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (fragment == null) { fragment = (Fragment) fragmentClass.newInstance(); }
            transaction.replace(R.id.content_main, fragment, fragmentClass.getCanonicalName());
            transaction.commit();

        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "Error in Fragment transactions", e);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Listener DEMO

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(mNavigationView.getMenu().getItem(0).isChecked()) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ListenerFragment fragment = (ListenerFragment) fragmentManager.findFragmentByTag(ListenerFragment.class.getCanonicalName());
                fragment.onReceive(intent);
            }
        }
    };

    public void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(this.receiver, filter);
    }

    public void onPause() {
        super.onPause();
        this.unregisterReceiver(this.receiver);
    }


    @Override
    public void onFragmentInteraction() {
        // show hide the progress bar
    }
}
