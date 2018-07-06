package com.example.thando.qvaya.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thando.qvaya.R;
import com.example.thando.qvaya.Student.BackgroundForgortPassword;

public class FogortPassword extends AppCompatActivity {

    private EditText studentNumber;
    public Button SendToMyEmailBTN = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogort_password);

        studentNumber =(EditText) findViewById(R.id.StudentNumberTxtForgort);

        SendToMyEmailBTN = (Button) findViewById(R.id.SendToMyEmail);


        InputFilter filter6 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(FogortPassword.this, "Invalid Input \n  numbers only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        studentNumber.setFilters(new InputFilter[] { filter6 });


    }

    public void SendEmailBTN(View view) {

        boolean error = false;
        if(studentNumber.getText().toString().length() < 8  )
        {
            error = true;
            studentNumber.setError("Please make sure it a correct student number");
            if(studentNumber.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(!error)
        {

            //Assigning all the variables to the string before inserting to the database

            String studentNumberForEmail = studentNumber.getText().toString();
            String type="InsertTheStudentNumber";

            BackgroundForgortPassword background = new BackgroundForgortPassword(this);
            background.execute(type,studentNumberForEmail);


            studentNumber.getText().clear();

        }
        else

        {
            if (studentNumber.getText().toString().isEmpty()) {
                studentNumber.requestFocus();
                if (studentNumber.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        }

    }
}
