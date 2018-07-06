package com.example.thando.qvaya.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;


import com.example.thando.qvaya.R;

import static android.app.PendingIntent.getActivity;

public class StudentHome extends AppCompatActivity {
    private CardView CardViewStudentMap ;
    private CardView CardViewStudentProfile;
    private CardView CardViewStudentViewRes;
    private CardView CardViewSettingsStudent;
    private CardView CardViewEvents;
    String username = "";
    public  static  String StudentNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        username = getIntent().getStringExtra("langa");

        StudentNumber = username;
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        CardViewStudentMap =  findViewById(R.id.StudentSettingCV);

        CardViewStudentMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StudentHome.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        //--------

        CardViewStudentProfile =  findViewById(R.id.profileStudentCV);

        CardViewStudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHome.this, StudentProfile.class);
                startActivity(intent);
            }
        });

        //-----
        CardViewStudentViewRes =  findViewById(R.id.StudentViewResCV);

        CardViewStudentViewRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHome.this, StudentViewRes.class);
                startActivity(intent);
            }
        });

        //-----------
        CardViewSettingsStudent =  findViewById(R.id.SetReminderStudentCV);

        CardViewSettingsStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHome.this, StudentSetting.class);
                startActivity(intent);
            }
        });

        //-----------
        CardViewEvents =  findViewById(R.id.StudentEventsCV);
        CardViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHome.this, StudentEvent.class);
                startActivity(intent);
            }
        });
    }
}
