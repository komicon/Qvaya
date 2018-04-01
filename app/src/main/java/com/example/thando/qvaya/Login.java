package com.example.thando.qvaya;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;


public class Login extends  BaseActivity {

    private EditText drivernumbertxt;
    private EditText passwordtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_login);
        drivernumbertxt = findViewById(R.id.studentnumber);

        InputFilter filter3 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(Login.this, "Invalid Input \n Digits Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        drivernumbertxt.setFilters(new InputFilter[] { filter3 });

        passwordtxt= findViewById(R.id.passwordtxt);



        InputFilter[] editFilters =  passwordtxt.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter.LengthFilter(10); //the desired length
        passwordtxt.setFilters(newFilters);


    }
    public void loginBTN(View view){

        boolean error = false;

        if(passwordtxt.getText().toString().length() < 5)
        {
            error = true;
            passwordtxt.setError("Enter password");
        }

        if(!error)
        {
            startActivity(new Intent(Login.this, AdminHome.class));
            drivernumbertxt.getText().clear();
            passwordtxt.getText().clear();

        } else {




            if(drivernumbertxt.getText().toString().isEmpty())
            {
                drivernumbertxt.requestFocus();
                if(drivernumbertxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }


            if(passwordtxt.getText().toString().length() < 5||passwordtxt.getText().toString().isEmpty())
            {
                passwordtxt.setError("Invalid Must Have 5 digits");
                passwordtxt.requestFocus();
                if(passwordtxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }


        }




    }
}
