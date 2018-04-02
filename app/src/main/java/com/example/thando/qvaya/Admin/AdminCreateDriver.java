package com.example.thando.qvaya.Admin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thando.qvaya.R;

public class AdminCreateDriver extends AppCompatActivity {

    private EditText firstnametxt;
    private EditText lastnametxt;
    private EditText usernametxt;
    private EditText cellnumbertxt;
    private EditText passwordtxt;
    private AppCompatButton btnCreateDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_driver);
        firstnametxt = findViewById(R.id.FirstNameTxt);

        cellnumbertxt =  findViewById(R.id.CellNumbertxt);
        passwordtxt = findViewById(R.id.passwordtxt);

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateDriver.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        firstnametxt.setFilters(new InputFilter[] { filter });


        //-----------
        lastnametxt = findViewById(R.id.LastNametxt);

        InputFilter filter2 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateDriver.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        lastnametxt.setFilters(new InputFilter[] { filter2 });



        //--------------



        usernametxt = findViewById(R.id.userNameTxt);

        InputFilter filter3 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateDriver.this, "Invalid Input \n Letters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        usernametxt.setFilters(new InputFilter[] { filter3 });

        //-------------



        //------------



        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));
            }
        });
    }



    public void CreateDriverAdminBTN(View view) {

        boolean error = false;

        if(cellnumbertxt.getText().toString().length() < 10)
        {
            error = true;
            cellnumbertxt.setError("Invalid Phone Number");
        }
        if(firstnametxt.getText().toString().isEmpty())
        {
            error = true;
            firstnametxt.setError("Whats your first name?");
        }
        if(lastnametxt.getText().toString().isEmpty())
        {
            error = true;
            lastnametxt.setError("Whats your last name?");
        }
        if(passwordtxt.getText().toString().length() < 5)
        {
            error = true;
            passwordtxt.setError("Enter password");
        }
        if(usernametxt.getText().toString().isEmpty())
        {
            error = true;
            usernametxt.setError("Enter User Name");
        }
        if(!error)
        {
            Toast.makeText(this, "Driver created", Toast.LENGTH_SHORT).show();
            firstnametxt.getText().clear();
            lastnametxt.getText().clear();
            usernametxt.getText().clear();
            cellnumbertxt.getText().clear();
            passwordtxt.getText().clear();

        } else {




            if(firstnametxt.getText().toString().isEmpty())
            {
                firstnametxt.requestFocus();
                if(firstnametxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }






            if(lastnametxt.getText().toString().isEmpty())
            {
                lastnametxt.requestFocus();
                if(lastnametxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }



            if(usernametxt.getText().toString().isEmpty())
            {
                usernametxt.requestFocus();
                if(usernametxt.requestFocus()) {
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



            if(cellnumbertxt.getText().toString().length() < 10 || cellnumbertxt.getText().toString().isEmpty())
            {
                cellnumbertxt.setError("Invalid Must Have 10 digits");
                cellnumbertxt.requestFocus();
                if(cellnumbertxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }

        }
    }

    private void checkRequiredFields() {
        if (!firstnametxt.getText().toString().isEmpty() && !lastnametxt.getText().toString().isEmpty() && !usernametxt.getText().toString().isEmpty()) {
            btnCreateDriver.setEnabled(true);
        } else {
            btnCreateDriver.setEnabled(false);
        }
    }
}
