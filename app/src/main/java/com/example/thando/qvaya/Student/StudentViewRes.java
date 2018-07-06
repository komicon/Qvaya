package com.example.thando.qvaya.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
import java.util.Arrays;
import java.util.List;


public class StudentViewRes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerRes;

    public String nameOfRes = "";
    String specific ="";
    //An ArrayList for Spinner Items
    private ArrayList<String> res;
    private ArrayList<String> times;

    List<String> newList;
    ListView ViewAllResTime;
    ArrayAdapter<String> arrayAdapter;
    private JSONArray result;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_res);


        ViewAllResTime =(ListView)findViewById(R.id.RestimetablelistView);
        times = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,times);
        ViewAllResTime.setVisibility(View.VISIBLE);

        res = new ArrayList<String>();
        spinnerRes =(Spinner) findViewById(R.id.StuentSelectRes);
        getData();

        spinnerRes.setOnItemSelectedListener(this);
    }


    private void getData() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(StudentConfig.DATA_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array

                            result = j.getJSONArray(StudentConfig.JSON_ARRAY2);

                            //Calling method getStudents to get the students from the JSON Array
                            AllResidences(result);
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

    private void AllResidences(JSONArray j) {

        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                res.add(json.getString(StudentConfig.TAG_ResIS));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapterComplaint = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res);
        adapterComplaint.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerRes.setAdapter(adapterComplaint);

        //Setting adapter to show the items in the spinner
        //spinner.setAdapter(new ArrayAdapter<String>( getContext(),android.R.layout.simple_list_item_1, students));
    }

    public void getResSelected(int position) {

        List<String> res = new ArrayList<String>();
        SpinnerAdapter arrayAdapterList1 = null;
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            nameOfRes = spinnerRes.getSelectedItem().toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(nameOfRes.equals("385 Smith"))
        {
            newList = Arrays.asList(SmithMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Adrian Road"))
        {
            newList = Arrays.asList(AdrianRoadMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Astra Hotel"))
        {
            newList = Arrays.asList(AstraMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Bearnard Close"))
        {
            newList = Arrays.asList(BearnardMUT);
            times.addAll(newList);

        }
        if(nameOfRes.equals("Berea Court"))
        {
            newList = Arrays.asList(BereaCourtMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Durban Hotel"))
        {
            newList = Arrays.asList(DurbanHotelMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Executive Hotel"))
        {
            newList = Arrays.asList(ExecHotelMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Fessifern Building"))
        {
            newList = Arrays.asList(FessifernMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Killarney Hotel"))
        {
            newList = Arrays.asList(KillarneyMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Lonsdale Hotel"))
        {
            newList = Arrays.asList(LonsdaleMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Palmerston Hotel"))
        {
            newList = Arrays.asList(PalmerstonMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Pilglen Mews"))
        {
            newList = Arrays.asList(PilglenMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Plaza Lodge"))
        {
            newList = Arrays.asList(PlazaLodgeMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Poynton House"))
        {
            newList = Arrays.asList(PoyntonMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Renaissance Building"))
        {
            newList = Arrays.asList(RenaissanceMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Sea Point Towers"))
        {
            newList = Arrays.asList(SeaPointMUT);
            times.addAll(newList);
        }
        if(nameOfRes.equals("Sha Jehan Res"))
        {
            newList = Arrays.asList(ShaJehanMUT);
            times.addAll(newList);

        }
        if(nameOfRes.equals("Ubombo Res"))
        {
            newList = Arrays.asList(UbomboMUT);
            times.addAll(newList);
        }

        ViewAllResTime.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

        switch (parent.getId()) {
            case R.id.StuentSelectRes:
                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();
                getResSelected(i);
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
