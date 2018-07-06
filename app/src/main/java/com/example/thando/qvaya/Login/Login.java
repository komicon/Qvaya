package com.example.thando.qvaya.Login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thando.qvaya.AdminDriver.AdminHome;
import com.example.thando.qvaya.Driver.DriverTransit;
import com.example.thando.qvaya.R;
import com.example.thando.qvaya.pushnotification.EndPointsDelays;
import com.example.thando.qvaya.pushnotification.MainActivityDelays;
import com.example.thando.qvaya.pushnotification.MyVolleyDelay;
import com.example.thando.qvaya.pushnotification.SharedPrefManagerDelay;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

public class Login extends AppCompatActivity implements View.OnClickListener{
    EditText UsernameEt, PasswordEt;
    String empid;
    private Button buttonRegister;
   // private EditText editTextEmail;
    private ProgressDialog progressDialog;


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
        UsernameEt = (EditText)findViewById(R.id.UsernameLogin);
        PasswordEt = (EditText)findViewById(R.id.PasswordLogin);


        buttonRegister = (Button) findViewById(R.id.loginbtn);


        if( buttonRegister!= null){
            buttonRegister.setOnClickListener(this);
        }


    }
    public void OnLogin(View view) {

        boolean error = false;



        if(PasswordEt.getText().toString().length() < 6 ||PasswordEt.getText().toString().isEmpty())
        {
            UsernameEt.getText().clear();
            PasswordEt.getText().clear();
            error = true;
            PasswordEt.setError("Invalid Must Have 6 - 8 digits");
            PasswordEt.requestFocus();
            if(PasswordEt.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(UsernameEt.getText().toString().length() < 6  || UsernameEt.getText().toString().isEmpty())
        {
            UsernameEt.getText().clear();
            PasswordEt.getText().clear();
            error = true;
            UsernameEt.setError("Invalid Must Have 6 - 8 digits");
            UsernameEt.requestFocus();
            if(UsernameEt.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }


        if(!error) {

            String username = UsernameEt.getText().toString();
            String password = PasswordEt.getText().toString();
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);



           // empid = username;
            Log.i("p1",username);
            Log.i("p2",UsernameEt.getText().toString());

            UsernameEt.getText().clear();
            PasswordEt.getText().clear();

        }




}
    public void forgotpassbtn(View view) {

        startActivity(new Intent(Login.this, FogortPassword.class));
    }
    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPrefManagerDelay.getInstance(this).getDeviceToken();
        final String email = UsernameEt.getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            Log.i("working","Token not generated");
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPointsDelays.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                            JSONArray info = new JSONArray(response);

                            String name = info.getJSONObject(0).getString("name");
                            String picture = info.getJSONObject(0).getString("picture");



                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(Login.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                            Log.i("working",obj.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        MyVolleyDelay.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            //Log.i("p3",empid);


            sendTokenToServer();
            OnLogin(v);
        }
    }
}