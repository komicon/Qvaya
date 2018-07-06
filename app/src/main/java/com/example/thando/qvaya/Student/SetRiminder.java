package com.example.thando.qvaya.Student;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.thando.qvaya.AdminDriver.AdminHome;
import com.example.thando.qvaya.R;

public class SetRiminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_riminder);


        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), StudentHome.class));
            }
        });
    }

    public void SetReminderStudentBTN(View view) {

        Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show();
    }
}
