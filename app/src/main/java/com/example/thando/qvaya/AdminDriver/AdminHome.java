package com.example.thando.qvaya.AdminDriver;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.thando.qvaya.AdminCoordinator.BusCoordinatorAdminFragment;
import com.example.thando.qvaya.R;
import com.example.thando.qvaya.SectionsPageAdapter;

public class AdminHome extends AppCompatActivity {

    private static final String TAG = "AdminHome";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    CardView mycard ;
    Intent i ;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new DriversAdminFragment(), "Drivers");
        adapter.addFragment(new BusCoordinatorAdminFragment(), "Bus Coordinator");

        viewPager.setAdapter(adapter);
    }

}
