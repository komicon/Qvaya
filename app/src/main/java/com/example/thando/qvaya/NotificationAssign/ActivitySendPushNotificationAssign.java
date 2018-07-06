package com.example.thando.qvaya.NotificationAssign;

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

public class ActivitySendPushNotificationAssign extends  AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private Button buttonSendPush;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private ProgressDialog progressDialog;
   // BusCoordinatorAssignBusesFragment bs = new BusCoordinatorAssignBusesFragment();



    private EditText editTextTitle, editTextMessage, editTextImage;

    private boolean isSendAllChecked;
    private List<String> devices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_push_notification);



        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        spinner = (Spinner) findViewById(R.id.spinnerDevices);
        buttonSendPush = (Button) findViewById(R.id.buttonSendPush);

        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        editTextImage = (EditText) findViewById(R.id.editTextImageUrl);

        devices = new ArrayList<>();

        radioGroup.setOnCheckedChangeListener(this);
        buttonSendPush.setOnClickListener(this);

        loadRegisteredDevices();
    }

    //method to load all the devices from database
    private void loadRegisteredDevices() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Devices...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPointsAssign.URL_FETCH_DEVICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray jsonDevices = obj.getJSONArray("preferences");

                                for (int i = 0; i < jsonDevices.length(); i++) {
                                    JSONObject d = jsonDevices.getJSONObject(i);
                                    devices.add(d.getString("StudentNumber"));
                                }

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                        ActivitySendPushNotificationAssign.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        devices);

                                spinner.setAdapter(arrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

        };
        MyVolleyAssign.getInstance(this).addToRequestQueue(stringRequest);
    }

    //this method will send the push
    //from here we will call sendMultiple() or sendSingle() push method
    //depending on the selection
    private void sendPush() {
        if (isSendAllChecked) {
           // sendMultiplePush();
        } else {
            sendSinglePush();
        }
    }

    public void sendMultiplePush(String time,String resname) {


        final String title = "Qvaya";
        final String message = "THE "+time+" HAS BEEN DISPATCHED FROM THE DEPOT TO "+resname;
        final String image = null;


        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPointsAssign.URL_SEND_MULTIPLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Toast.makeText(ActivitySendPushNotificationAssign.this, response, Toast.LENGTH_LONG).show();
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
                params.put("message", message);

                if (!TextUtils.isEmpty(image))
                    params.put("image", image);
                return params;
            }
        };

        MyVolleyAssign.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void sendSinglePush() {
        final String title = editTextTitle.getText().toString();
        final String message = editTextMessage.getText().toString();
        final String image = editTextImage.getText().toString();
        final String email = spinner.getSelectedItem().toString();

        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPointsAssign.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Toast.makeText(ActivitySendPushNotificationAssign.this, response, Toast.LENGTH_LONG).show();
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
                params.put("message", message);

                if (!TextUtils.isEmpty(image))
                    params.put("image", image);

                params.put("email", email);
                return params;
            }
        };

        MyVolleyAssign.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonSendAll:
                isSendAllChecked = true;
                spinner.setEnabled(false);
                break;

            case R.id.radioButtonSendOne:
                isSendAllChecked = false;
                spinner.setEnabled(true);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        //calling the method send push on button click
        sendPush();
    }
}
