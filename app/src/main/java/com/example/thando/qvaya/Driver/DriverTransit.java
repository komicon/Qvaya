package com.example.thando.qvaya.Driver;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.thando.qvaya.R;
import com.example.thando.qvaya.Student.StudentHome;

public class DriverTransit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_transit);


        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), DriverHome .class));
            }
        });
    }

    public void BusIsHereBTN(View view) {
        Toast.makeText(this, "Bus Is Here", Toast.LENGTH_SHORT).show();
    }

    public void BusDelayBtn(View view) {

        Toast.makeText(this, "Bus Delayed", Toast.LENGTH_SHORT).show();
    }

    public void DoneBTN(View view) {

        Toast.makeText(this, "Trip Done", Toast.LENGTH_SHORT).show();
    }
}
