package com.example.thando.qvaya.AdminCoordinator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//import com.example.thando.qvaya.AdminDriver.Background;
import com.example.thando.qvaya.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
//import android.app.AlertDialog;

import java.util.Calendar;


import com.example.thando.qvaya.AdminDriver.AdminHome;

public class    AdminDeleteCoordinator extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;
    private EditText LeavingReason_txt;
    String valueFromSpinner;
    BackgroundWorkerMtolo backgroundWorkerMtolo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_coordinator);



        //Initializing the ArrayList
        students = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner);

        LeavingReason_txt = findViewById(R.id.LeavingReason_ID);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);



        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));


            }
        });



        getData();
    }


    // Confirming to delete
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                // .setIcon(R.drawable.)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dialog.dismiss();
                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Config_delete_busCoordinatorMtolo.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Config_delete_busCoordinatorMtolo.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


    private void getStudents(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString(Config_delete_busCoordinatorMtolo.TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(AdminDeleteCoordinator.this, android.R.layout.simple_spinner_dropdown_item, students));
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        //textViewName.setText(getName(position));
        // textViewCourse.setText(getCourse(position));
        //textViewSession.setText(getSession(position));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void DeleteCoordinatoAdminBTN(View view) {

        //  AskOption();
        backgroundWorkerMtolo = new BackgroundWorkerMtolo(this);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Do your Yes progress

                        Calendar calendar = Calendar.getInstance();
                        String curreentDate = DateFormat.getDateInstance().format(calendar.getTime());

                        //String type = "button2_delete";
                        valueFromSpinner = spinner.getSelectedItem().toString();
                        String reasonLeavingVariable = LeavingReason_txt.getText().toString();

                        String yearLeftVariable = curreentDate;

                        backgroundWorkerMtolo.execute(yearLeftVariable,reasonLeavingVariable,valueFromSpinner);

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        Toast.makeText(getApplicationContext(), "Coordinator Not deleted", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Are you sure to delete?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        // creating system date time



//        AlertDialog diaBox = AskOption();
//        diaBox.show();

    }




}
