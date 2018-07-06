package com.example.thando.qvaya.AdminDriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thando.qvaya.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.*;

public class AdminCreateDriver extends AppCompatActivity {

    private TextInputLayout  fName;
    private TextInputLayout  sName;
    private TextInputLayout  cellNum;
    private TextInputLayout  email;
    private TextInputLayout  homeAddress;
    private TextInputLayout  age;
    private Spinner sp,genderSp;

    private  static  final String REGISTER_URL ="https://qvayaapp.000webhostapp.com/Wonder/driverInsert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_driver);
        fName = findViewById(R.id.name_in_layout);
        sName = findViewById(R.id.surname_in_layout);
        cellNum = findViewById(R.id.cellNum_in_layout);

        email   = findViewById(R.id.email_in_layout);
        homeAddress = findViewById(R.id.address_in_layout);
        age = findViewById(R.id.Age_in_layout);
        sp = (Spinner) findViewById(R.id.spinner7);
        genderSp = findViewById(R.id.spinner7);


        ArrayList<String> list = new ArrayList<>();
        list.add("Select-Gender");
        list.add("Female");
        list.add("Male");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_spinner_dropdown_item,list);

        sp.setAdapter(adapter);





        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));

            }
        });
    }

    private void registerDriver(){
        String name = fName.getEditText().getText().toString().trim();
        String sname = sName.getEditText().getText().toString().trim();
        String cellPhone = cellNum.getEditText().getText().toString().trim();
        String Email    = email.getEditText().getText().toString().trim();
        String address  = homeAddress.getEditText().getText().toString().trim();
        String Age      = age.getEditText().getText().toString().trim();
        String Gender   = sp.getSelectedItem().toString().trim();

        Calendar cal    = Calendar.getInstance();
        int year        = cal.get(Calendar.YEAR);

        String jobType  = "Driver";

        register(name,sname,cellPhone,Email,address,Age,Gender,year,jobType);

    }

    public void register(String name,String sname,String cellPhone,String Email,String address,String Age,String Gender,int year,String jobType){
        String urlDriver = "?name="+name+"&sname="+sname+"&cellPhone="+cellPhone+"&Email="+Email+"&address="+address+"&Age="+Age+"&Gender="+Gender+"&year="+year+"&jobType="+jobType;

        class RegisterDriver extends AsyncTask<String,Void,String>{


            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(AdminCreateDriver.this,"Please wait",null,true,true);
                // Toast.makeText(getApplicationContext(),"Driver created", Toast.LENGTH_SHORT).show();

                loading.setTitle("Creating Driver");
                loading.setMessage("Please wait..");
                loading.show();

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        loading.cancel();
                        Toast.makeText(getApplicationContext(),"Driver Registration done!", Toast.LENGTH_SHORT).show();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                //  Toast.makeText(getApplicationContext(),"Internet Connection error.", Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader = null;

                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String result;
                    result = bufferReader.readLine();

                    //Toast.makeText(getApplicationContext(),"Try and catch", Toast.LENGTH_LONG).show();
                    return result;

                }
                catch (Exception e)
                {
                    return null;
                }
            }

        }
        RegisterDriver rd = new RegisterDriver();
        rd.execute(urlDriver);

    }



    private  boolean validateSpinner(){
        String Spnr = genderSp.getSelectedItem().toString();
        if(Spnr.equalsIgnoreCase("Select-Gender")){
            Toast.makeText(getApplicationContext(),"Please select gender!", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }
    private boolean validateName(){
        String name = fName.getEditText().getText().toString().trim();

        if(name.isEmpty()) {
            fName.setError("Field can't be empty!");
            return false;
        }
        else{
            fName.setError(null);
            return true;
        }

    }

    private boolean validateSurname(){
        String surname = sName.getEditText().getText().toString().trim();

        if(surname.isEmpty()){
            sName.setError("Field can't be empty!");
            return false;
        }
        else {
            sName.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailA = email.getEditText().getText().toString().trim();

        if(emailA.isEmpty()){
            email.setError("Field can't be empty!");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateCellNum(){
        String cell = cellNum.getEditText().getText().toString().trim();

        if(cell.isEmpty()){
            cellNum.setError("Field can't be empty!");
            return false;
        }
        else
        if(cell.length()>10 || cell.length()<10){
            cellNum.setError("Cell phone number should contain 10 digits");
            return false;
        }
        else {
            cellNum.setError(null);
            return true;
        }
    }
    private boolean validateAddress(){
        String address = homeAddress.getEditText().getText().toString().trim();

        if(address.isEmpty()){
            homeAddress.setError("Field can't be empty!");
            return false;
        }
        else {
            homeAddress.setError(null);
            return true;
        }
    }

    private boolean validateAge(){
        String AgeInput = age.getEditText().getText().toString().trim();

        if(AgeInput.isEmpty()){
            age.setError("Field can't be empty!");
            return false;
        }
        else
        if (AgeInput.length()>2 || (Integer.parseInt(AgeInput))>80){
            age.setError("Sorry we only hire drivers who are 80 years old and less!");
            return false;
        }
        else{
            age.setError(null);
            return true;
        }
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void btnSubmit(View view) {
        String emailA = email.getEditText().getText().toString().trim();


        if(emailValidator(emailA)){
            email.setError(null);
        }
        else
        if(emailA.isEmpty()){
            email.setError("Field can't be empty");
        }
        else
        {

            email.setError("Invalid email!");
        }

        if( !(validateAge()) | !(validateAddress()) | !(validateCellNum()) | !(validateName()) | !(validateSurname()) | !(validateSpinner())){
            //emailValidator(emailA);
            validateAge();
            validateAddress();
            validateCellNum();
            validateName();
            validateSurname();
            validateSpinner();
        }
        else{
            registerDriver();

        }
    }





}
