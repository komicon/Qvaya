package com.example.thando.qvaya.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;


import com.example.thando.qvaya.R;

import static android.app.PendingIntent.getActivity;

public class StudentHome extends AppCompatActivity {
    private CardView CardViewSetReminder;
    private CardView CardViewStudentProfile;
    private CardView CardViewStudentViewRes;
    private CardView CardViewSettingsStudent;
    private CardView CardViewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        CardViewSetReminder =  findViewById(R.id.SetReminderStudentCV);

        CardViewSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHome.this, SetRiminder.class);
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
        CardViewSettingsStudent =  findViewById(R.id.StudentSettingCV);

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
