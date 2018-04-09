package com.example.thando.qvaya.Driver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;


import com.example.thando.qvaya.R;



public class DriverHome extends AppCompatActivity {
    private CardView CardViewTransit;
    private CardView CardViewDriverProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        CardViewTransit =  findViewById(R.id.DriverTransitCV);

        CardViewTransit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DriverHome.this, DriverTransit.class);
                startActivity(intent);
            }
        });

        //--------

        CardViewDriverProfile =  findViewById(R.id.DriverProfileCV);

        CardViewDriverProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverHome.this, DriverProfile.class);
                startActivity(intent);
            }
        });

    }
}
