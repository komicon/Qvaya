package com.example.thando.qvaya.AdminDriver;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.thando.qvaya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.*;
import java.util.ArrayList;


public class AdminUpdateDriver extends AppCompatActivity implements Spinner.OnItemSelectedListener{
    private TextInputLayout surname;
    private TextInputLayout email;
    private TextInputLayout Age_in;
    private TextInputLayout cellNum_in;
    private TextInputLayout address_in;
    private TextInputLayout Job_in;
    private TextInputLayout Year_in;
    private TextInputLayout name_in;
    private Spinner  sGender;
    private String empNum;
    private Spinner eID;
    private ArrayList<String> employees;
    private JSONArray result;
    private ProgressDialog loading;
    private static final String REGISTER_URL = "https://qvayaapp.000webhostapp.com/Wonder/updateDriver.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_driver);

        surname =  findViewById(R.id.surname_in_layout);
        name_in = findViewById(R.id.name_in_layout);
        email   = findViewById(R.id.email_in_layout);
        Age_in = findViewById(R.id.Age_in_layout);
        cellNum_in = findViewById(R.id.cellNum_in_layout);
        address_in = findViewById(R.id.address_in_layout);
        Job_in  = findViewById(R.id.Job_in_layout);
        Year_in  = findViewById(R.id.Year_in_layout);
        sGender = (Spinner) findViewById(R.id.spGender);

        ArrayList<String> list = new ArrayList<>();
        list.add("Select-Gender");
        list.add("Female");
        list.add("Male");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_spinner_dropdown_item,list);

        sGender.setAdapter(adapter);

        //Initializing the ArrayList
        employees = new ArrayList<String>();

        //Initializing Spinner
        eID = (Spinner) findViewById(R.id.empID);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        eID.setOnItemSelectedListener(this);

        //Initializing TextViews


        //This method will fetch the data from the URL
        getData();






//        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                startActivity(new Intent(view.getContext(), AdminHome.class));
//            }
//        });
    }
    public void btnSubmit(View view){

        // updateDriver driver = new updateDriver();

        String name = name_in.getEditText().getText().toString().trim();
        String sname = surname.getEditText().getText().toString().trim();
        String em   = email.getEditText().getText().toString().trim();
        String age  = Age_in.getEditText().getText().toString().trim();
        String cell = cellNum_in.getEditText().getText().toString().trim();
        String address = address_in.getEditText().getText().toString().trim();
        String job     = Job_in.getEditText().getText().toString().trim();
        String year    = Year_in.getEditText().getText().toString().trim();
        String gender  = sGender.getSelectedItem().toString().trim();
        String empID   = eID.getSelectedItem().toString().trim();

        int yr = Integer.parseInt(year);

        // register(name,sname,cell,em,address,age,gender,yr,empID);
        updateDriver backgroundWorker = new updateDriver(this);
        backgroundWorker.execute(empID,name,sname,gender,em,age,address,job,cell,year);



        loading = ProgressDialog.show(AdminUpdateDriver.this,"Please wait",null,true,true);
        // Toast.makeText(getApplicationContext(),"Driver created", Toast.LENGTH_SHORT).show();

        loading.setTitle("UPDATING DRIVER");
        loading.setMessage("Please wait..");
        loading.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                loading.cancel();
                // Toast.makeText(getApplicationContext(),"Driver Registration done!", Toast.LENGTH_SHORT).show();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);

        //Toast.makeText(getApplicationContext()," "+name+" "+sname+" "+cell+" "+em+" "+address+" "+age+" "+gender+" "+yr+" "+job+" "+empID, Toast.LENGTH_SHORT).show();




    }
    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(ConfigWonder.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(ConfigWonder.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getEmployees(result);
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

    private void getEmployees(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                employees.add(json.getString(ConfigWonder.TAG_EmpID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        eID.setAdapter(new ArrayAdapter<String>(AdminUpdateDriver.this, android.R.layout.simple_spinner_dropdown_item, employees));
    }

    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(ConfigWonder.TAG_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
    //Doing the same with this method as we did with getName()
    private String getSurname(int position){
        String sname="";
        try {
            JSONObject json = result.getJSONObject(position);
            sname = json.getString(ConfigWonder.TAG_SNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sname;
    }

    private String getGender(int position){
        String gender="";
        try {
            JSONObject json = result.getJSONObject(position);
            gender = json.getString(ConfigWonder.TAG_GEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gender;
    }

    private String getEmail(int position){
        String email="";
        try {
            JSONObject json = result.getJSONObject(position);
            email = json.getString(ConfigWonder.TAG_EMAIL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return email;
    }
    private String getAge(int position){
        String age="";
        try {
            JSONObject json = result.getJSONObject(position);
            age = json.getString(ConfigWonder.TAG_AGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return age;
    }

    private String getAddress(int position){
        String address="";
        try {
            JSONObject json = result.getJSONObject(position);
            address = json.getString(ConfigWonder.TAG_HOMEADD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return address;
    }

    private String getJobType(int position){
        String jType="";
        try {
            JSONObject json = result.getJSONObject(position);
            jType = json.getString(ConfigWonder.TAG_JTYPLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jType;
    }
    private String getCellNumber(int position){
        String cell="";
        try {
            JSONObject json = result.getJSONObject(position);
            cell = json.getString(ConfigWonder.TAG_CELLNU);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cell;
    }

    private String getYear(int position){
        String year="";
        try {
            JSONObject json = result.getJSONObject(position);
            year = json.getString(ConfigWonder.TAG_YEARST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return year;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
//        textViewName.setText(getName(position));
//        textViewCourse.setText(getCourse(position));
//        textViewSession.setText(getSession(position));

        name_in.getEditText().setText(getName(position));
        email.getEditText().setText(getEmail(position));
        Age_in.getEditText().setText(getAge(position));
        cellNum_in.getEditText().setText(getCellNumber(position));
        address_in.getEditText().setText(getAddress(position));
        Job_in.getEditText().setText(getJobType(position));
        Year_in.getEditText().setText(getYear(position));
        surname.getEditText().setText(getSurname(position));

        if(getGender(position).equalsIgnoreCase("Male"))
        {
            sGender.setSelection(2);
        }
        else
        if(getGender(position).equalsIgnoreCase("Female")){
            sGender.setSelection(1);
        }

    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        name_in.getEditText().setText("");
        email.getEditText().setText("");
        Age_in.getEditText().setText("");
        cellNum_in.getEditText().setText("");
        address_in.getEditText().setText("");
        Job_in.getEditText().setText("");
        Year_in.getEditText().setText("");
        sGender.setSelection(0);
    }

    public void register(String name,String sname,String cellPhone,String Email,String address,String Age,String Gender,int year,String EmpID){
        String urlDriver = "?name="+name+"&sname="+sname+"&cellPhone="+cellPhone+"&Email="+Email+"&address="+address+"&Age="+Age+"&Gender="+Gender+"&year="+year+"&empID="+EmpID;

        class RegisterDriver extends AsyncTask<String,Void,String> {


            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(AdminUpdateDriver.this,"Please wait",null,true,true);
                // Toast.makeText(getApplicationContext(),"Driver created", Toast.LENGTH_SHORT).show();

                loading.setTitle("UPDATING DRIVER");
                loading.setMessage("Please wait..");
                loading.show();

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        loading.cancel();
                        // Toast.makeText(getApplicationContext(),"Driver Registration done!", Toast.LENGTH_SHORT).show();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                //  Toast.makeText(getApplicationContext(),"Internet Connection error.", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"hey postExecute", Toast.LENGTH_SHORT).show();

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
                    //Toast.makeText(getApplicationContext(),"Try And catch", Toast.LENGTH_SHORT).show();

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

    public boolean validateName(){
        String fname = name_in.getEditText().getText().toString().trim();

        if(fname.isEmpty()){
            return false;
        }
        else
            return true;
    }

    public boolean validateSurname(){
        String lname = surname.getEditText().getText().toString().trim();

        if(lname.isEmpty()){
            return false;
        }
        else
            return true;
    }

    public boolean validateGender(){
        String gndr = sGender.getSelectedItem().toString().trim();

        if(gndr.equalsIgnoreCase("Select-Gender")){

            return false;
        }
        else
            return true;
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

    public boolean validateCellNumber(){
        String num = cellNum_in.getEditText().getText().toString().trim();

        if(cellNum_in.getEditText().getText().toString().trim().length() < 10){
            cellNum_in.setError("phone number cannot contain numbers less then 10");
            return  false;
        }
        else{


            return true;
        }
    }


}

