package com.example.thando.qvaya.BusCoordinator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.thando.qvaya.R;
import com.example.thando.qvaya.pushNitificationRealocatedriver.ReallocateDriverDelatAlocate;
import com.example.thando.qvaya.pushnotification.CustomListViewDelay;

import java.util.ArrayList;


import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import static android.app.PendingIntent.getActivity;

/**
 * Created by Thando on 2018/05/12.
 */

public class BusCoordinatorDelaysFragment extends Fragment  {


    String urladdress="https://qvayaapp.000webhostapp.com/Thando/delaylistview.php";
    String[] name;
    String[] email;
    String[] imagepath;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;

    private static final String TAG = "BusCoordinatorAdminFragment";

    String delay;
    ArrayList<String> array_list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buscoordinatordelaysfragment,container,false);
        listView =(ListView)view.findViewById(R.id.delays_listview);




        /*if (delay!= null){
            delay= getArguments().getString("titlelist");
        }else{
            delay = "No Delays";
        }*/

        //String delay = "";



       /* listView .setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), Bus_Coordinator_reallocate.class);
                // add data to the intent...
                startActivity(intent);
            }}*/



        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListViewDelay customListView=new CustomListViewDelay(getActivity(),name,email,imagepath);
        listView.setAdapter(customListView);


        /*ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array_list);
        listView.setAdapter(adapter);*/

                listView .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // ListView Clicked item index

                        LinearLayout parents = (LinearLayout) view;
                        TextView t = (TextView) parents.findViewById(R.id.tvemail);


                        String itemValue = (String) listView.getItemAtPosition(position);
                        String values=t.getText().toString();


                        // Show Alert
                        String s = "Position :" + itemValue + " ListItem: " + values;
                        Log.i("driverid", s);

                        Intent intent = new Intent(getActivity(), ReallocateDriverDelatAlocate.class);
                        intent.putExtra("name",itemValue);
                        intent.putExtra("email",values);
                       // Toast.makeText(getActivity(), name[position].toString()+" <----->"+ email[position].toString(), Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                        intent.putExtra("name",itemValue);
                        intent.putExtra("email",values);
                      //Toast.makeText(BusCoordinatorDelaysFragment.this, name[position].toString()+" <----->"+ email[position].toString(), Toast.LENGTH_SHORT).show();
                    }
                });//listview on click

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {









                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();



                /*Fragment currentFragment = getFragmentManager().findFragmentByTag("BusCoordinatorAdminFragment");
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();*/
                Snackbar.make(view, "Delays Refreshing...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        return view;



    }



    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        // ListView Clicked item index
        int itemPosition = position;

        //parent.getChildAt(position - listView.getLastVisiblePosition()).findViewById(R.id.listView1);
        String itemValue = (String) listView.getItemAtPosition(position);

        //???????????????????????????.getText().toString();

        // Show Alert
        Toast.makeText(getContext(),
                "Position :" + itemPosition + " ListItem: " + itemValue,
                Toast.LENGTH_LONG
        ).show();
    }

    private void collectData()
    {
//Connection
        try{

            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

//JSON
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            name=new String[ja.length()];
            email=new String[ja.length()];
            imagepath=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                name[i]=jo.getString("name");
                email[i]=jo.getString("email");
                imagepath[i]=jo.getString("photo");
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }
}