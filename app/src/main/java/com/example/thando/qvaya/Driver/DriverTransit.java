package com.example.thando.qvaya.Driver;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.thando.qvaya.R;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.thando.qvaya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.thando.qvaya.R;
import com.example.thando.qvaya.Student.StudentHome;
import com.example.thando.qvaya.pushnotification.ActivitySendPushNotificationDelay;
import com.example.thando.qvaya.pushnotification.EndPointsDelays;
import com.example.thando.qvaya.pushnotification.MyVolleyDelay;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.security.AccessController.getContext;

public class DriverTransit extends AppCompatActivity  implements View.OnClickListener{

    String employeeID = "";
    String destination = "";
    String timeTrip = "";
    TextView timeText;
    Button here;
    String ServerURL = "https://qvayaapp.000webhostapp.com/Thando/displayprofile.php" ;
    //String ServerURL = "https://qvayaapp.000webhostapp.com/Thando/pushNotification/delaybusco.php" ;
    EditText namem, email ;
    Button button;
    String TempName, TempEmail ;
    String selected = "";
    String empid;
    String newString = "";
    String username = "";
    Switch s ;
    Boolean Level = false;
    Button delaybtn;
    Switch s1;
    Switch s2;
    String statusSwitch1, statusSwitch2,statusSwitch3;
    private List<String> devices;
    private String m_Text = "";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_transit);

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), DriverHome .class));
            }
        });

        username = getIntent().getStringExtra("langa");

        s = (Switch) findViewById(R.id.switch3);
        delaybtn = findViewById(R.id.delayBTN2);
        delaybtn.setEnabled(false);

         s1 =  findViewById(R.id.switch1);
         s2 =  findViewById(R.id.switch2);

        s.setOnCheckedChangeListener(changeChecker);
        s1.setOnCheckedChangeListener(changeChecker);
        s2.setOnCheckedChangeListener(changeChecker);

        //------
        timeText = findViewById(R.id.timertxt);
        here = findViewById(R.id.HereBTN);

        employeeID = username ;

        String type = "first";
        BackgroundWorkerHereXul bkw = new BackgroundWorkerHereXul(this);
        bkw.execute(type,employeeID);

        /// grab from database from database
        try {

            JSONObject jobj = new JSONObject(bkw.get());

            JSONArray jr =  jobj.getJSONArray("tripDetails");


            for(int i=0;i<jr.length();i++){
                try {
                    //Getting json object
                    JSONObject json = jr.getJSONObject(i);

                    //Adding the name of the student to array list
                    destination = json.getString(ConfigsHereXul.TAG_ResISNames);

                    // Toast.makeText(this,json.getString(ConfigsHereXul.TAG_ResISNames),Toast.LENGTH_LONG).show();
                    timeTrip = json.getString(ConfigsHereXul.TAG_Time);

                    // Toast.makeText(this,destination.substring(0,3),Toast.LENGTH_LONG).show();
                    // Toast.makeText(this,json.getString(ConfigsHereXul.TAG_Time),Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void BusIsHereBTN(View view) {

//300000
        new CountDownTimer(300000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                updateTimer((int) (millisUntilFinished/1000));


            }

            @Override
            public void onFinish() {

                MediaPlayer player = MediaPlayer.create(getBaseContext(),R.raw.bell);
                player.start();
                timeText.setText("Time Out !!");
                here.setEnabled(true);
            }
        }.start();



        sendMultiplePush("The bus is about to leave in : 5 minutes","Buss leaving");
        here.setEnabled(false);

    }
    private void sendMultiplePush(String messages,String titles) {
        final String title = titles ;                  //editTextTitle.getText().toString();
        final String message = messages;                // editTextMessage.getText().toString();
        final String image = "";                //editTextImage.getText().toString();
        //Criteria();
        //   progressDialog.setMessage("Sending Push");
        // progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPointsHereXul.URL_SEND_MULTIPLE_PUSH_Here,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  progressDialog.dismiss();

                        Toast.makeText(DriverTransit.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String resId = destination.substring(0,3);

                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", message);
                params.put("ResDestination",resId);
                params.put("timeTrip",timeTrip);

                if (!TextUtils.isEmpty(image))
                    params.put("image", image);
                return params;
            }
        };

        MyVolleyHereXul.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void updateTimer(int secondsleft)
    {
        int minutes = (int) secondsleft / 60;
        int seconds = secondsleft - minutes * 60;

        String secondsString = Integer.toString(seconds);

        if(seconds <= 9){

            secondsString = "0" + secondsString;

        }

        timeText.setText(minutes +" : "+secondsString);

    }

    public void InsertData(final String name, final String email){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = name ;
                String EmailHolder = email ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(DriverTransit.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name, email);
    }

    public void BusDelayBtn(View view) {


            // final Dialog alert = builder.create();

            final EditText txtUrl = new EditText(this);

// Set the default text to a link of the Queen
            txtUrl.setHint("Reason for delay");

            new AlertDialog.Builder(this)
                    .setTitle("Level 3 Delay")
                    .setMessage("Driver ID: " + username)
                    .setView(txtUrl)
                    .setPositiveButton("Send Delay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String url = txtUrl.getText().toString();
                            Toast.makeText(DriverTransit.this, "Reason for delay is--->"+url , Toast.LENGTH_SHORT).show();
                            final String title = username;
                            final String message = url;
                            final String image = "";
                            final String idnum = "Emp1027";

                            //-- add listview


                            //---
                            progressDialog = new ProgressDialog(DriverTransit.this);
                            progressDialog.setMessage("Sending Push");
                            progressDialog.show();



                            StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPointsDelays.URL_SEND_SINGLE_PUSH,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            progressDialog.dismiss();

                                            Toast.makeText(DriverTransit.this, response, Toast.LENGTH_LONG).show();
                                            Log.i("Results",response);



                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("title", title);
                                    if(statusSwitch1.equals("ON")){
                                        selected = "1";
                                    }
                                    if(statusSwitch2.equals("ON")){
                                        selected = "2";
                                    }
                                    if(statusSwitch3.equals("ON")){
                                        selected = "3";
                                    }
                                    params.put("message", message + " \n,Delay!  " );
                                    if (!TextUtils.isEmpty(image))
                                        params.put("image", image);
                                    params.put("email", idnum );
                                    InsertData(title,message);
                                    return params;
                                }
                            };
                            MyVolleyDelay.getInstance(DriverTransit.this).addToRequestQueue(stringRequest);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .show();
    }
    public void DoneBTN(View view) {

        // receiving data from other activity
        // Bundle bundle = getIntent().getExtras();
        //String recVariable = bundle.getString("ImpassingEmpID");

        Calendar calendar = Calendar.getInstance();
        String curreentDate = DateFormat.getDateInstance().format(calendar.getTime());


        String tripDate = curreentDate;

        String empID_variable =  username;
        //String empID_variable_String = Integer.toString(empID_variable_Int);


        BackgroundworkerMtolo b = new BackgroundworkerMtolo(this);
        b.execute(empID_variable,tripDate);

        Toast.makeText(this, "Trip Done", Toast.LENGTH_SHORT).show();

    }

    CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (s1.isChecked())
                statusSwitch1 = s1.getTextOn().toString();
            else
                statusSwitch1 = s1.getTextOff().toString();
            if (s2.isChecked())
                statusSwitch2 = s2.getTextOn().toString();
            else
                statusSwitch2 = s2.getTextOff().toString();
            if (s.isChecked()) {
                statusSwitch3 = s.getTextOn().toString();
                delaybtn.setEnabled(true);
            }

            else
                statusSwitch3 = s.getTextOff().toString();


            if (isChecked){
                if (buttonView == s1) {
                    s2.setChecked(false);
                    s.setChecked(false);

                }
                if (buttonView == s2) {
                    s1.setChecked(false);
                    s.setChecked(false);

                }
                if (buttonView == s) {
                    s2.setChecked(false);
                    s1.setChecked(false);

                }
                //Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1 + "\n" + "Switch2 :" + statusSwitch2 + "\n" + "Switch3 :" + statusSwitch3, Toast.LENGTH_LONG).show(); // display the current state for switch's
            }
        }
    };
    @Override
    public void onClick(View v) {
    }
}