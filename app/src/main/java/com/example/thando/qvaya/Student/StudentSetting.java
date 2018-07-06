package com.example.thando.qvaya.Student;
import com.example.thando.qvaya.Login.*;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton;
import com.example.thando.qvaya.R;
import com.example.thando.qvaya.pushNotificationStudent.BackgroundSetReminderMb;
import com.example.thando.qvaya.pushNotificationStudent.SharedPrefManagerMB;

import java.sql.Driver;

import static com.example.thando.qvaya.Student.StudentHome.StudentNumber;


public class StudentSetting extends AppCompatActivity {
    String[] reminder=new String[9];
    String[] everyday=new String[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_setting);
        findViewById(R.id.SetReminderStudentBTN).setEnabled(false);//making submit button deactivated by default
        findViewById(R.id.checkBox).setEnabled(false);
        findViewById(R.id.checkBox2).setEnabled(false);
        findViewById(R.id.checkBox6).setEnabled(false);
        findViewById(R.id.checkBox4).setEnabled(false);
        findViewById(R.id.checkBox7).setEnabled(false);
        findViewById(R.id.checkBox8).setEnabled(false);
        findViewById(R.id.checkBox5).setEnabled(false);
        findViewById(R.id.checkBox9).setEnabled(false);
        findViewById(R.id.checkBox10).setEnabled(false);

        for(int k=0;k<9;k++)
        {
            reminder[k]="";
            everyday[k]="NO";
        }

        //swicth 1
        Switch  mySwitch1 = (Switch) findViewById(R.id.switch5);
        mySwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox).setEnabled(true);
                    reminder[0]="06:30";
                    CheckBox checkbox1=(CheckBox) findViewById(R.id.checkBox);
                    checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[0]="YES";
                            }
                            else
                            {
                                everyday[0]="NO";
                            }
                        }
                    });

                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox).setEnabled(false);
                    reminder[0]="";
                }
            }});

        //swicth 2
        Switch  mySwitch2 = (Switch) findViewById(R.id.switch6);
        mySwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox2).setEnabled(true);
                    reminder[1]="07:00";
                    CheckBox checkbox2=(CheckBox) findViewById(R.id.checkBox2);
                    checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[1]="YES";
                            }
                            else
                            {
                                everyday[1]="NO";
                            }
                        }
                    });

                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox2).setEnabled(false);
                    reminder[1]="";
                }
            }});

        //swicth 3
        Switch  mySwitch3 = (Switch) findViewById(R.id.switch7);
        mySwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox6).setEnabled(true);
                    reminder[2]="07:30";
                    CheckBox checkbox3=(CheckBox) findViewById(R.id.checkBox6);
                    checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[2]="YES";
                            }
                            else
                            {
                                everyday[2]="NO";
                            }
                        }
                    });
                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox6).setEnabled(false);
                    reminder[2]="";
                }

            }});

        //swicth 4
        Switch  mySwitch4 = (Switch) findViewById(R.id.switch8);
        mySwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox4).setEnabled(true);
                    reminder[3]="08:00";
                    CheckBox checkbox4=(CheckBox) findViewById(R.id.checkBox4);
                    checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[3]="YES";
                            }
                            else
                            {
                                everyday[3]="NO";
                            }
                        }
                    });
                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox4).setEnabled(false);
                    reminder[3]="";
                }
            }});

        //swicth 5
        Switch  mySwitch5 = (Switch) findViewById(R.id.switch9);
        mySwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox7).setEnabled(true);
                    reminder[4]="08:30";
                    CheckBox checkbox5=(CheckBox) findViewById(R.id.checkBox7);
                    checkbox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[4]="YES";
                            }
                            else
                            {
                                everyday[4]="NO";
                            }
                        }
                    });

                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox7).setEnabled(false);
                    reminder[4]="";
                }
            }});

        //swicth 6
        Switch  mySwitch6 = (Switch) findViewById(R.id.switch10);
        mySwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox8).setEnabled(true);
                    reminder[5]="09:00";
                    CheckBox checkbox6=(CheckBox) findViewById(R.id.checkBox8);
                    checkbox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[5]="YES";
                            }
                            else
                            {
                                everyday[5]="NO";
                            }
                        }
                    });
                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox8).setEnabled(false);
                    reminder[5]="";
                }
            }});

        //swicth 7
        Switch  mySwitch7 = (Switch) findViewById(R.id.switch11);
        mySwitch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox5).setEnabled(true);
                    reminder[6]="10:30";
                    CheckBox checkbox7=(CheckBox) findViewById(R.id.checkBox5);
                    checkbox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[6]="YES";
                            }
                            else
                            {
                                everyday[6]="NO";
                            }
                        }
                    });
                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox5).setEnabled(false);
                    reminder[6]="";
                }
            }});

        //swicth 8
        Switch  mySwitch8 = (Switch) findViewById(R.id.switch12);
        mySwitch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox9).setEnabled(true);
                    reminder[7]="13:00";
                    CheckBox checkbox8=(CheckBox) findViewById(R.id.checkBox9);
                    checkbox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[7]="YES";
                            }
                            else
                            {
                                everyday[7]="NO";
                            }
                        }
                    });
                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox9).setEnabled(false);
                    reminder[7]="";
                }
            }});

        //swicth 9
        Switch  mySwitch9 = (Switch) findViewById(R.id.switch13);
        mySwitch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    // Toast.makeText(StudentSetReminder.this, "swicth is on", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(true);
                    findViewById(R.id.checkBox10).setEnabled(true);
                    reminder[8]="15:00";
                    CheckBox checkbox9=(CheckBox) findViewById(R.id.checkBox10);
                    checkbox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked)
                            {
                                everyday[8]="YES";
                            }
                            else
                            {
                                everyday[8]="NO";
                            }
                        }
                    });
                }
                else
                {
                    findViewById(R.id.SetReminderStudentBTN).setEnabled(false);
                    findViewById(R.id.checkBox10).setEnabled(false);
                    reminder[8]="";
                }
            }});

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), StudentHome.class));

            }
        });

    }

    //perform onclick for all the toggle bars and the checkboxes,
    // activate the set reminder button only if any of them is activated
    // which means by default the button will be deactivated


    //Creating a string request

    public void SetReminderStudentBTN(View view) {

        try {
            for (int i = 0; i < 9; i++) {
                if (!(reminder[i].equalsIgnoreCase(""))) {

                    // Toast.makeText(StudentSetReminder.this, reminder[i] + " " + everyday[i] + " by student " + bundle.getString("username"), Toast.LENGTH_SHORT).show();
                    String time = reminder[i];
                    String isEveryday = everyday[i];
                    String studentNumber = StudentNumber;
                    BackgroundSetReminderMb b = new BackgroundSetReminderMb(this);
                    //token
                    String token = SharedPrefManagerMB.getInstance(this).getDeviceToken();


                    if (token != null) {

                        b.execute(studentNumber,reminder[i].toString(),everyday[i],token.toString());
                    } else {
                        //if token is null that means something wrong
                        Toast.makeText(StudentSetting.this, "tkError01: Something went wrong please try again.", Toast.LENGTH_SHORT).show();
                    }
                    //end

                }
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(StudentSetting.this,ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}