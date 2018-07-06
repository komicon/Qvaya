package com.example.thando.qvaya.AdminDriver;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class AdminDeleteDriver extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;


    private EditText LeavingReason_txt;


    EditText LeavingReason_ID;
    Button button2_delete;
    String delete_varieble;
    String LeavingReason_ID_variable;
    String yearLeftVariable;
    String valueFromSpinner;
    BackgroundWorkerMtoloDriver b;

    @Override


    // onCreate method start here
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_driver);
        //Initializing the ArrayList
        students = new ArrayList<String>();
        //Initializing Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener



        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
        }
        //This method will fetch the data from the URL

        LeavingReason_txt = findViewById(R.id.LeavingReason_ID);

        // code for deleye button start here
        //editText_empID = (EditText) findViewById(R.id.editText_empID);
        //editText_empID = (EditText) findViewById(R.id.editText_empID);
        button2_delete = (Button) findViewById(R.id.button2_delete);
        LeavingReason_ID = (EditText) findViewById(R.id.LeavingReason_ID);


        // creating system date time
        Calendar calendar = Calendar.getInstance();
        String curreentDate = DateFormat.getDateInstance().format(calendar.getTime());


        getData();

    }
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
    public void DeleteDriverAdminBTN(View view) {

        b = new BackgroundWorkerMtoloDriver(this);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        //  Toast.makeText(getApplicationContext(), "Driver Deleted", Toast.LENGTH_SHORT).show();
                        // creating system date time
                        Calendar calendar = Calendar.getInstance();
                        String curreentDate = DateFormat.getDateInstance().format(calendar.getTime());


                        yearLeftVariable = curreentDate;

                        // delete_varieble = editText_empID.getText().toString();
                        LeavingReason_ID_variable = LeavingReason_ID.getText().toString();
                        valueFromSpinner = spinner.getSelectedItem().toString();


                        b.execute(yearLeftVariable,LeavingReason_ID_variable,valueFromSpinner);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        Toast.makeText(getApplicationContext(), "Driver Not deleted", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Are you sure to delete?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        //  AskOption();
        // AlertDialog diaBox = AskOption();
        //diaBox.show();


    }


    //  populate a spinner from json
    private void getData(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Config_deleting_driverMtolo.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(Config_deleting_driverMtolo.JSON_ARRAY);

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
                students.add(json.getString(Config_deleting_driverMtolo.TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(AdminDeleteDriver.this, android.R.layout.simple_spinner_dropdown_item, students));
    }


    //Method to get student name of a particular position

    //this method will execute when we pic an item from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        //textViewName.setText(getName(position));
        // textViewCourse.setText(getCourse(position));
        //textViewSession.setText(getSession(position));
    }

    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}




