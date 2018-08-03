package com.example.thando.qvaya.BusCoordinator;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thando.qvaya.NotificationAssign.EndPointsAssign;
import com.example.thando.qvaya.NotificationAssign.MyVolleyAssign;
import com.example.thando.qvaya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2/28/2017.
 */



public class BusCoordinatorAssignBusesFragment extends Fragment implements AdapterView.OnItemSelectedListener {



    private static final String TAG = "Tab1Fragment";


    //Declaring an Spinner
    private Spinner spinner;
    private Spinner spin;
    private Spinner timeSpinner;

    private JSONArray results4;
    public String numPlate="";



    private ArrayList<String> numberPlate;
    private Spinner busSpinner;
    //An ArrayList for Spinner Items
    private ArrayList<String> students;
    private ArrayList<String> res;
    private  ArrayList<String> studentRequestedBus;
    public TextView err;


    //JSON Array
    private JSONArray result;
    private JSONArray result2;
    private JSONArray results3;
    public String nameOfRes = "";
    public String empnum = "";
    public String timeSpecificallySelected = "";
    public static  String totNumOfStudents="";

    public Button setItem = null;


    String[] SmithMUT = {"07:00", "08:30", "09:00", "10:30", "13:00", "15:00"};
    String[] ShaJehanMUT = {"07:00", "07:30", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] SeaPointMUT = {"07:00", "07:30", "08:00", "10:30", "13:00", "15:00"};
    String[] PlazaLodgeMUT = {"07:00", "07:30", "08:00", "10:30", "13:00", "15:00"};
    String[] PilglenMUT = {"07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] PalmerstonMUT = {"07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] UbomboMUT = {"07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] FessifernMUT = {"07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] ExecHotelMUT = {"07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] DurbanHotelMUT = {"07:00", "08:00"};
    String[] AdrianRoadMUT = {"07:00", "08:00", "10:30", "13:00", "15:00"};
    String[] BereaCourtMUT = {"07:00", "08:00", "08:30", "09:00", "10:30", "13:00", "15:00"};
    String[] AstraMUT = {"07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] RenaissanceMUT = {"06:30", "07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] PoyntonMUT = {"06:30", "07:00", "08:00", "08:30", "09:00", "10:30", "13:00", "15:00"};
    String[] KillarneyMUT = {"06:30", "07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] LonsdaleMUT = {"06:30", "07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};
    String[] BearnardMUT = {"06:30", "07:00", "08:00", "09:00", "10:30", "13:00", "15:00"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bus_coordinator_set_reminder_delay, container, false);


        //Initializing the ArrayList
        students = new ArrayList<String>();
        res = new ArrayList<String>();

        numberPlate = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) view.findViewById(R.id.getFreeDrivers);
        spin = (Spinner) view.findViewById(R.id.getAllResidence);
        timeSpinner = (Spinner) view.findViewById(R.id.getResTimeTable);
        err = (TextView) view.findViewById(R.id.sumOfStudents);

        setItem = (Button) view.findViewById(R.id.AssignBusBTN);
        busSpinner = (Spinner)view.findViewById(R.id.getFreeBuses);
        setItem.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           sendToServer();
                                           final String title = "*Qvaya*";
                                           final String message = "THE "+timeSpecificallySelected+" BUS HAS BEEN DISPATCHED FROM THE DEPOT TO "+nameOfRes;
                                           final String image = null;
                                           //driverID = spinner.getSelectedItem().toString().trim();
                                           sendToServer3(message,nameOfRes);

                                           // progressDialog.setMessage("Sending Push");
                                           //  progressDialog.show();

                                           StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPointsAssign.URL_SEND_MULTIPLE_PUSH,
                                                   new Response.Listener<String>() {
                                                       @Override
                                                       public void onResponse(String response) {
                                                           // progressDialog.dismiss();

                                                           Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
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
                                                   params.put("time",timeSpecificallySelected);
                                                   params.put("resname",nameOfRes);
                                                   params.put("driverID",empnum);

                                                   if (!TextUtils.isEmpty(image))
                                                       params.put("image", image);
                                                   return params;
                                               }
                                           };

                                           MyVolleyAssign.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);

                                           AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                           builder.setCancelable(true);
                                           builder.setTitle("Sent...");
                                           builder.setMessage("Driver has been assigned");
                                           builder.setInverseBackgroundForced(true);
                                           builder.setPositiveButton("OK",
                                                   new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog,
                                                                           int which) {
                                                           dialog.dismiss();
                                                       }
                                                   });
                                           AlertDialog alert = builder.create();
                                           alert.show();

                                       }

                                   }
        );


        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener
        spinner.setOnItemSelectedListener(this);
        spin.setOnItemSelectedListener(this);
        timeSpinner.setOnItemSelectedListener(this);

        //This method will fetch the data from the URL
        getData();
        getData2();
        getDataOfNumberPlate();
        return view;
    }

    private void getData() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(BusCoordConfigAssign.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array

                            result = j.getJSONArray(BusCoordConfigAssign.JSON_ARRAY);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getData2() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(BusCoordConfigAssign.DATA_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array


                            result2 = j.getJSONArray(BusCoordConfigAssign.JSON_ARRAY2);

                            //Calling method getStudents to get the students from the JSON Array
                            getRes(result2);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j) {

        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                students.add(json.getString(BusCoordConfigAssign.TAG_EmployeeID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapterComplaint = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, students);
        adapterComplaint.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapterComplaint);

        //Setting adapter to show the items in the spinner
        //spinner.setAdapter(new ArrayAdapter<String>( getContext(),android.R.layout.simple_list_item_1, students));
    }

    private void getRes(JSONArray j) {
        //Traversing through all the items in the json array

        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                res.add(json.getString(BusCoordConfigAssign.TAG_ResIS));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapterComplaint = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, res);
        adapterComplaint.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(adapterComplaint);

        //Setting adapter to show the items in the spinner
        //spinner.setAdapter(new ArrayAdapter<String>( getContext(),android.R.layout.simple_list_item_1, students));
    }

    //Method to get Employee number of a particular position
    private String getEMployeeNumber(int position) {

        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object

            empnum = spinner.getSelectedItem().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return empnum;
    }

    //Method to get Employee number of a particular position
    //Method to get Employee number of a particular position
    public String getResSelected(int position) {

        List<String> res = new ArrayList<String>();
        SpinnerAdapter arrayAdapterList1 = null;
        try {
            //Getting object of given index
            JSONObject json = result2.getJSONObject(position);

            //Fetching name from that object
            nameOfRes = spin.getSelectedItem().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (nameOfRes.equals("385 Smith")) {
            res.addAll(Arrays.asList(SmithMUT));

        }
        if (nameOfRes.equals("Adrian Road")) {
            res.addAll(Arrays.asList(AdrianRoadMUT));
        }


        if (nameOfRes.equals("Bearnard Close")) {
            res.addAll(Arrays.asList(BearnardMUT));


        }
        if (nameOfRes.equals("Astra Hotel")) {
            res.addAll(Arrays.asList(AdrianRoadMUT));
        }

        if (nameOfRes.equals("Berea Court")) {
            res.addAll(Arrays.asList(BereaCourtMUT));

        }
        if (nameOfRes.equals("Durban Hotel")) {
            res.addAll(Arrays.asList(DurbanHotelMUT));

        }
        if (nameOfRes.equals("Executive Hotel")) {
            res.addAll(Arrays.asList(ExecHotelMUT));


        }
        if (nameOfRes.equals("Fessifern Building")) {
            res.addAll(Arrays.asList(FessifernMUT));

        }
        if (nameOfRes.equals("Killarney Hotel")) {
            res.addAll(Arrays.asList(KillarneyMUT));

        }
        if (nameOfRes.equals("Lonsdale Hotel")) {
            res.addAll(Arrays.asList(LonsdaleMUT));

        }
        if (nameOfRes.equals("Palmerston Hotel")) {
            res.addAll(Arrays.asList(PalmerstonMUT));

        }
        if (nameOfRes.equals("Pilglen Mews")) {
            res.addAll(Arrays.asList(PilglenMUT));
        }
        if (nameOfRes.equals("Plaza Lodge")) {
            res.addAll(Arrays.asList(PlazaLodgeMUT));
        }
        if (nameOfRes.equals("Poynton House")) {
            res.addAll(Arrays.asList(PoyntonMUT));

        }
        if (nameOfRes.equals("Renaissance Building")) {
            res.addAll(Arrays.asList(RenaissanceMUT));
        }
        if (nameOfRes.equals("Sea Point Towers")) {
            res.addAll(Arrays.asList(SeaPointMUT));

        }
        if (nameOfRes.equals("Sha Jehan Res")) {
            res.addAll(Arrays.asList(ShaJehanMUT));

        }
        if (nameOfRes.equals("Ubombo Res")) {
            res.addAll(Arrays.asList(UbomboMUT));
        }


        arrayAdapterList1 = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, res);
        timeSpinner.setAdapter(arrayAdapterList1);
        return timeSpecificallySelected;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

        switch (parent.getId()) {
            case R.id.getAllResidence:
                getResSelected(i);

                break;
            case R.id.getFreeDrivers:
                getEMployeeNumber(i);

                break;
            case R.id.getResTimeTable:
                timeSpecificallySelected= timeSpinner.getSelectedItem().toString();
                sendToServer2();
                getData3();

                break;

            case R.id.getFreeBuses:
                numPlate= busSpinner.getSelectedItem().toString();



        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void sendToServer(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://qvayaapp.000webhostapp.com/Lungcebo/getJsonArrayFromAndroid.php";
        StringRequest postRequest = new StringRequest (Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
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
                params.put("empnum",empnum);
                params.put("nameOfRes",nameOfRes);
                params.put("timeSpecificallySelected",timeSpecificallySelected);
                params.put("numberPlate",numPlate);
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void sendToServer2(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://qvayaapp.000webhostapp.com/Lungcebo/getResIDandGetNumberOfStudents.php";
        StringRequest postRequest = new StringRequest (Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
//                        JSONObject obj = null;
//                        JSONObject obj2 = null;
                        try {
//                            obj = new JSONObject(response);
//
//                            Log.i("working 222222",obj.getString("StudentRequested"));
//                            obj2 = new JSONObject(obj.getString("StudentRequested"));
//
//
//                            Log.d("working 111111",obj2.getString("number"));


                            String outputJson =response;



                            JSONArray ja = new JSONObject(response).getJSONArray("StudentRequested");
                            totNumOfStudents = ja.getJSONObject(0).getString("number");

                            Log.d("working 7777",totNumOfStudents);


                            err.setText("number of students :"+totNumOfStudents);
                            Log.d("Response", response);
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
                params.put("nameOfRes",nameOfRes);
                params.put("timeSpecificallySelected",timeSpecificallySelected);
                return params;
            }
        };
        queue.add(postRequest);
    }
    private void getData3() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(BusCoordConfigAssign.DATA_URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);
                            results3 =j.getJSONArray(BusCoordConfigAssign.JSON_ARRAY3);
                            for (int i = 0 ;i<results3.length();i++){
                                JSONObject jo = results3.getJSONObject(i);
                                totNumOfStudents = jo.optString(BusCoordConfigAssign.TAG_Number);
                            }
                            //Storing the Array of JSON String to our JSON Array


                            results3 = j.getJSONArray(BusCoordConfigAssign.JSON_ARRAY3);


                            //Calling method getStudents to get the students from the JSON Array

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    public void sendToServer3(final String message, final String nameOfRes){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://qvayaapp.000webhostapp.com/Lungcebo/getNotificationDetails.php";
        StringRequest postRequest = new StringRequest (Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
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
                params.put("message",message);
                params.put("resname",nameOfRes);

                return params;
            }
        };
        queue.add(postRequest);
    }

    private void getDataOfNumberPlate() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(BusCoordConfigAssign.DATA_URL4,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array


                            results4 = j.getJSONArray(BusCoordConfigAssign.JSON_ARRAY4);

                            //Calling method getStudents to get the students from the JSON Array
                            getNumberPlates(results4);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getNumberPlates(JSONArray j) {

        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                numberPlate.add(json.getString(BusCoordConfigAssign.TAG_NumberPate));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapterComplaint = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, numberPlate);
        adapterComplaint.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        busSpinner.setAdapter(adapterComplaint);

        //Setting adapter to show the items in the spinner
        //spinner.setAdapter(new ArrayAdapter<String>( getContext(),android.R.layout.simple_list_item_1, students));
    }
}
