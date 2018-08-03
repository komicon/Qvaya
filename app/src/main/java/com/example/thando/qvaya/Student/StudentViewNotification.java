package com.example.thando.qvaya.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.thando.qvaya.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import static com.example.thando.qvaya.Student.StudentHome.StudentNumber;

public class StudentViewNotification extends AppCompatActivity {

    public String Message;
    public JSONArray result;
    public String Resname;


    ListView notificationList;
    ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arr ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_notification);

        sendToServer();

        arr =new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        final String theStudent = StudentNumber;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://qvayaapp.000webhostapp.com/Lungcebo/GetAllNotifications.php";
        StringRequest postRequest = new StringRequest (Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            String outputJson =response;

                            Log.d("Response", response);
                            notificationList =(ListView) findViewById(R.id.ViewNotificationList);
                            for(int i = 0;i < response.length();i++) {
                                JSONArray ja = new JSONObject(response).getJSONArray("result");
                                Message = ja.getJSONObject(i).getString("Message");
                                Resname = ja.getJSONObject(i).getString("ResID");

                                arr.add(Message);
                                //arr.add(Resname);


                                notificationList.setAdapter(arrayAdapter);
                            }
                            Log.d("Message------",Message);
                            Log.d("ResID------",Resname);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error) {
                        Log.d("ERROR", "error => " +error.toString());
                    }
                }
        )   {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("theStudent",theStudent);

                return params;
            }
        };
        queue.add(postRequest);

    }

    public void sendToServer(){


    }
}
