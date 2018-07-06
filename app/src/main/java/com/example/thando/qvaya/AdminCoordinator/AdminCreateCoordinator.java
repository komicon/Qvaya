package com.example.thando.qvaya.AdminCoordinator;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thando.qvaya.AdminDriver.AdminHome;
import com.example.thando.qvaya.R;


public class AdminCreateCoordinator extends AppCompatActivity {

    //Gender
    Spinner spin;

    String genderEmployee;

    private EditText emailBus;
    private EditText age;
    private EditText homAddEmp;
    private EditText firstnametxt;
    private EditText employeeSurname;
    private EditText cellnumbertxt;
    private EditText yearstarted;
    private AppCompatButton btnCreateDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_coordinator);

        //Gender of the employee
        spin =  (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> passengerAdapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        passengerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(passengerAdapter);




        //End of the gender of employee




        cellnumbertxt =  findViewById(R.id.CellNumbertxt);


        firstnametxt = findViewById(R.id.EmpNameTxt);

        InputFilter filter1 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        firstnametxt.setFilters(new InputFilter[] { filter1 });


        //-----------
        employeeSurname = findViewById(R.id.EmpSurnametxt);

        InputFilter filter2 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        employeeSurname.setFilters(new InputFilter[] { filter2 });



        //--------------



        emailBus = findViewById(R.id.EmailEMployeeBus);

        //InputFilter filter3 = new InputFilter() {
        //   public CharSequence filter(CharSequence source, int start, int end,
        //                               Spanned dest, int dstart, int dend) {
        //        for (int i = start; i < end; i++) {
        //            if (!Character.isLetter(source.charAt(i)) && !Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
        //                Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Letters Only!", Toast.LENGTH_SHORT).show();
        //                return "";
        //            }
        //        }
        //        return null;
        //    }

        //  };

        //  emailBus.setFilters(new InputFilter[] { filter3 });
        //--------------------------------------------------------

        //-------------

        age = findViewById(R.id.AgeEmptxt);

        InputFilter filter4 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Numbers Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        age.setFilters(new InputFilter[] { filter4 });

        //------------
        homAddEmp = findViewById(R.id.HomeAddEmptxt);

        InputFilter filter5 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i)) && !Character.isDigit(source.charAt(i))&& !Character.isWhitespace(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Letters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        homAddEmp.setFilters(new InputFilter[] { filter5 });
        //-------------
        cellnumbertxt = findViewById(R.id.CellNumbertxt);

        InputFilter filter6 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Letters Only and numbers!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        cellnumbertxt.setFilters(new InputFilter[] { filter6 });



        //-------------
        yearstarted = findViewById(R.id.testtxt);

        InputFilter filter7 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Numbers Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        yearstarted.setFilters(new InputFilter[] { filter7 });
        //--------------

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));
            }
        });


    }

    public void CreateCoordinaAdminBTN(View view) {

        boolean error = false;
        genderEmployee= spin.getSelectedItem().toString();

        if(cellnumbertxt.getText().toString().length() < 10  )
        {
            error = true;
            cellnumbertxt.setError("Invalid Phone Number");
            if(cellnumbertxt.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(cellnumbertxt.getText().toString().length() > 10){
            error = true;
            cellnumbertxt.setError("Invalid Phone Number, Must be ten Digits");
            if(cellnumbertxt.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(firstnametxt.getText().toString().isEmpty())
        {
            error = true;
            firstnametxt.setError("Whats your first name?");
        }
        if(employeeSurname.getText().toString().isEmpty())
        {
            error = true;
            employeeSurname.setError("Whats your last name?");
        }

        if(emailBus.getText().toString().isEmpty())
        {
            error = true;
            emailBus.setError("Enter email");
        }


        if(genderEmployee.equalsIgnoreCase("--Select Gender--"))
        {
            error = true;
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
        }

        if(age.getText().toString().isEmpty())
        {
            error = true;
            age.setError("Enter Age");
            if(age.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(age.getText().toString().length()>= 3)
        {
            error = true;
            age.setError("Enter Age, Must be two Digits");
            if(age.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        if(homAddEmp.getText().toString().isEmpty())
        {
            error = true;
            homAddEmp.setError("Enter Home Address");
        }
        if(yearstarted.getText().toString().isEmpty())
        {
            error = true;
            yearstarted.setError("Enter Year Started");
        }
        if(yearstarted.getText().toString().length() !=4){
            error = true;
            yearstarted.setError("Invalid Year, Must be four Digits");
            if(yearstarted.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(!error)
        {

            //Assigning all the variables to the string before inserting to the database
            String genderEmployee = spin.getSelectedItem().toString();
            String JobType ="Bus Coordinator";
            String empName = firstnametxt.getText().toString();
            String empSurname = employeeSurname.getText().toString();
            String empEmail = emailBus.getText().toString();
            String empCell = cellnumbertxt.getText().toString();
            String empYear = yearstarted.getText().toString();
            String empHome = homAddEmp.getText().toString();
            String empAge = age.getText().toString();
            String type="InsertNewBusCoordinator";

            BackgroundCreateCoordinator backgroundWorker = new BackgroundCreateCoordinator(this);
            backgroundWorker.execute(type, genderEmployee, JobType,empName,empSurname,empEmail,empCell,empYear,empHome,empAge);


            Toast.makeText(this, "Coordinator created", Toast.LENGTH_SHORT).show();
            firstnametxt.getText().clear();
            employeeSurname.getText().clear();
            emailBus.getText().clear();
            cellnumbertxt.getText().clear();

            yearstarted.getText().clear();
            age.getText().clear();
            homAddEmp.getText().clear();
            spin.setSelection(0);


        } else {




            if(firstnametxt.getText().toString().isEmpty())
            {
                firstnametxt.requestFocus();
                if(firstnametxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }






            if(employeeSurname.getText().toString().isEmpty())
            {
                employeeSurname.requestFocus();
                if(employeeSurname.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }



            if(emailBus.getText().toString().isEmpty())
            {
                emailBus.requestFocus();
                if(emailBus.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }


            if(age.getText().toString().isEmpty())
            {
                age.setError("Invalid Must Enter digits");
                age.requestFocus();
                if(age.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }

            if(homAddEmp.getText().toString().isEmpty())
            {
                homAddEmp.requestFocus();
                if(homAddEmp.requestFocus()) {
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

            if(yearstarted.getText().toString().length() > 4 || yearstarted.getText().toString().length() < 4)
            {
                yearstarted.setError("Invalid Year");
                yearstarted.requestFocus();
                if(yearstarted.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        }

    }
}
