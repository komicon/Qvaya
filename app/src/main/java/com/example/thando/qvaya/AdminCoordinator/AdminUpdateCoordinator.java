package com.example.thando.qvaya.AdminCoordinator;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thando.qvaya.R;

import com.example.thando.qvaya.AdminDriver.AdminHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//import org.apache.http.message.BasicNameValuePair;
// import org.apache.http.NameValuePair;


import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class AdminUpdateCoordinator extends AppCompatActivity implements AdapterView.OnItemSelectedListener , Validator.ValidationListener {

    @NotEmpty
    private EditText firstnametxt;

    @NotEmpty
    private EditText lastnametxt;

    //    @NotEmpty
//    @Email
    private EditText emailTxt;

    @NotEmpty
    private EditText adressTxt;

    @NotEmpty
    private EditText ageTxt;

    @NotEmpty
    private EditText cellnumbertxt;

    @NotEmpty
    private EditText yearStarTxt;

    private Validator validator;

//    @NotEmpty
//    private EditText passwordtxt;


    private Spinner  empId;


    private Spinner gender;


    private Spinner jopType;

    String genVal;

    String jobtypeVal;

    String empIdVal;

    String emplooyID;


    //An ArrayList for Spinner Items
    private ArrayList<String> employees;

    //JSON Array
    private JSONArray result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_coordinator);


        validator  =  new Validator(this);
        validator.setValidationListener(this);

        firstnametxt = findViewById(R.id.FirstNameTxt);
        lastnametxt = findViewById((R.id.LastNametxt));
        ageTxt = findViewById(R.id.agetxt);
        emailTxt = findViewById(R.id.Emailtxt);
        adressTxt = findViewById(R.id.Adresstxt);
        cellnumbertxt =  findViewById(R.id.CellNumbertxt);
//        passwordtxt = findViewById(R.id.passwordtxt);
        yearStarTxt = findViewById(R.id.YearStarttxt);

        empId = findViewById(R.id.spnEmpID);
        gender = findViewById(R.id.spnGender);
        jopType = findViewById(R.id.JobTypeSpn);

//        empId.get

        ArrayAdapter<CharSequence> adapterJob = ArrayAdapter.createFromResource(this,
                R.array.Job_Type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterJob.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        jopType.setAdapter(adapterJob);

        jopType.setOnItemSelectedListener(this);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        empId.setAdapter(adapter);

        //gender

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        gender.setAdapter(adapter2);
        gender.setOnItemSelectedListener(this);



        employees = new ArrayList<String>();

        getData();

        empId.setOnItemSelectedListener(this);


        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminUpdateCoordinator.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AdminUpdateCoordinator.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        lastnametxt.setFilters(new InputFilter[] { filter2 });

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));
            }
        });


    }

    private void getEmplooyess(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                employees.add(json.getString(ConfigUpCoordXul.TAG_EmpID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        //Setting adapter to show the items in the spinner
        empId.setAdapter(new ArrayAdapter<String>(AdminUpdateCoordinator.this, android.R.layout.simple_spinner_dropdown_item, employees));
    }
    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(ConfigUpCoordXul.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(ConfigUpCoordXul.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getEmplooyess(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


    private String getName(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getSname(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_SNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getGen(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_GEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getEmail(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_EMAIL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getAge(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_AGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getAddr(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_HOMEADD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getJobType(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_JTYPLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getYearStarted(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_YEARST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }

    private String getCellNum(int position){
        String session="";
        try {
            JSONObject json = result.getJSONObject(position);
            session = json.getString(ConfigUpCoordXul.TAG_CELLNU);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return session;
    }


    public void UpdateCoordinatorAdminBTN(View view) {

        String type = "Update";
        validator.validate();
        int errNum =  vailadionAll(view);


        String cellnumber = cellnumbertxt.getText().toString();
        String firstname = firstnametxt.getText().toString();
        String yearStar = yearStarTxt.getText().toString();
        String lastname = lastnametxt.getText().toString();
        String adress = adressTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String age = ageTxt.getText().toString();

        int  isErr = 0;

        if(email.isEmpty())
        {
            email = "Null";
        }
        else
        {

            email = email.toUpperCase();
        }



        if(genVal.equalsIgnoreCase("Choose Gender"))
        {

            Toast.makeText(this,"Pls Select Gender",Toast.LENGTH_LONG).show();
            isErr++;
        }


        if(empIdVal == null)
        {

            Toast.makeText(this,"Pls Select Employee ID",Toast.LENGTH_LONG).show();
            isErr++;
        }


        if(jobtypeVal.equalsIgnoreCase("Choose JobType"))
        {

            Toast.makeText(this,"Pls Select JobType",Toast.LENGTH_LONG).show();
            isErr++;;
        }


        if(isErr == 0 && errNum == 0 )
        {


            BackgroundWorkerUpCoordXul backgroundWorkerUpCoordXul = new BackgroundWorkerUpCoordXul(this);
            backgroundWorkerUpCoordXul.execute(type,empIdVal,firstname,lastname,genVal,email,age,adress,jobtypeVal,cellnumber,yearStar);
        }
        else
        {

            Toast.makeText(this,"Fix Errors",Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        TextView textView = (TextView)view;
//
//        genVal = (String) textView.getText();spn



//        if( parent.equals(R.id.spnGender))
//        {
//
//            TextView textViewGen = (TextView)view;
//            genVal = (String) textViewGen.getText();
//            Toast.makeText(this,"  " + genVal,Toast.LENGTH_SHORT).show();
//        }

        switch(parent.getId()) {


            case R.id.JobTypeSpn:
                TextView textViewJob = (TextView)view;

                jobtypeVal = (String) textViewJob.getText();

                // Toast.makeText(this,"  " + jobtypeVal,Toast.LENGTH_SHORT).show();
                break;

            case R.id.spnEmpID:

                firstnametxt.setText(getName(position));
                lastnametxt.setText(getSname(position));
                emailTxt.setText(getEmail(position));
                adressTxt.setText(getAddr(position));
                ageTxt.setText(getAge(position));
                cellnumbertxt.setText(getCellNum(position));
                yearStarTxt.setText(getYearStarted(position));
                lastnametxt.setText(getSname(position));


                if(getGen(position).equals("Male"))
                {
                    gender.setSelection(1);
                    genVal = "Male";
                }
                else if(getGen(position).equals("Female"))
                {
                    gender.setSelection(2);
                    genVal = "Female";

                }
                else
                {
                    gender.setSelection(0);
                    genVal = "Choose Gender";

                }


                if(getJobType(position).equals("Driver"))
                {
                    jopType.setSelection(1);
                    jobtypeVal = "Driver";
                }
                else if((getJobType(position).equals("Admin")))
                {
                    jopType.setSelection(2);
                    jobtypeVal = "Admin";
                }
                else if((getJobType(position).equals("Bus Coordinator")))
                {
                    jopType.setSelection(3);
                    jobtypeVal = "Bus Coordinator";
                }
                else
                {
                    jopType.setSelection(0);
                    jobtypeVal = "Choose JobType";
                }

                TextView textViewEmpId = (TextView)view;

                empIdVal = (String) textViewEmpId.getText();
                break;

            case R.id.spnGender:
                // Do stuff for spinner1
                TextView textViewGen = (TextView)view;
                genVal = (String) textViewGen.getText();
                //  Toast.makeText(this,"  " + genVal,Toast.LENGTH_SHORT).show();

                break;
        }


        // Toast.makeText(this,"  " + genVal,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }

    @Override
    public void onValidationSucceeded() {



    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for(ValidationError error : errors){

            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if(view instanceof EditText){
                ((EditText) view).setError(message);
            }
            else {

                //  Toast.makeText(this,message,Toast.LENGTH_LONG).show();

            }

        }

    }
    public int vailadionAll( View view)
    {
        int count = 0;
        String cellnumber = cellnumbertxt.getText().toString();
        String firstname = firstnametxt.getText().toString();
        String yearStar = yearStarTxt.getText().toString();
        String lastname = lastnametxt.getText().toString();
        String adress = adressTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String age = ageTxt.getText().toString();



        if(firstname.isEmpty()) {
            firstnametxt.setError("First Name required");
            count++;
        }

        if(yearStar.isEmpty() == false) {
            if (Integer.parseInt(yearStar) < 2004) {
                yearStarTxt.setError("There were no external residences at that time ");
                count++;
            }

            if( Integer.parseInt(yearStar) > 2018)
            {
                yearStarTxt.setError("Current year is 2018");
                count++;

            }
        }

        if(yearStar.length() < 4) {
            yearStarTxt.setError("Year Started must be 4 characters long ");
            count++;
        }

        if(yearStar.isEmpty()) {
            yearStarTxt.setError("Year Started required");
            count++;
        }


        if(lastname.isEmpty()) {
            lastnametxt.setError("Last Name required");
            count++;
        }

        if(adress.isEmpty()) {
            adressTxt.setError("Adress required");
            count++;
        }

//        if(email.isEmpty()) {
//            emailTxt.setError("Email required");
//        }

        if(cellnumber.length() < 10) {
            cellnumbertxt.setError("Cell Number must contain 10 characters");
            count++;
        }

        if(age.isEmpty()) {
            ageTxt.setError("Age required");
            count++;
        }

        if(age.isEmpty() == false) {
            if (Integer.parseInt(age) < 20) {
                ageTxt.setError("Coordinator cannot be 20 years younger");
                count++;
            }
        }

        if(cellnumber.isEmpty() == true) {
            cellnumbertxt.setError("Cell Number required");
            count++;
        }


        if(!cellnumber.startsWith("0")) {
            cellnumbertxt.setError("Cell Number is invalid it must start with 0");
            count++;
        }

        if(cellnumber.length() < 10) {
            cellnumbertxt.setError("Cell Number must contain 10 characters");
            count++;
        }
        if(email.length() > 0 && email.length() < 12)
        {
            emailTxt.setError("Drivers licence must Be 12 characters not less");
            count++;

        }

        return count;
    }
}
