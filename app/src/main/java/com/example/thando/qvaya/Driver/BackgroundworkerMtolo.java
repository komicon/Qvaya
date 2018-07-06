package com.example.thando.qvaya.Driver;



/**
 * Created by SEC TECHNOLOGY on 2018/06/14.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;



public class BackgroundworkerMtolo extends AsyncTask<String,Void,String> {


    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    Context context;

    List<String> arrResult = new ArrayList();


    BackgroundworkerMtolo(Context ctx)
    {
        context = ctx;
    }

    protected String doInBackground(String... params) {

//android:layout_marginEnd="16dp"
        // android:layout_marginStart="47dp"
        //android:layout_marginStart="13dp"
        //android:layout_marginEnd="22dp"
        //    android:layout_marginStart="51dp

        String upd_url = "https://qvayaapp.000webhostapp.com/Thabiso/DriverOffTrip.php";


        try {

            String empID_variable_String = params[0];
            String tripDate = params[1];




            URL url = new URL(upd_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data  =    URLEncoder.encode("TripID","UTF-8")+"="+URLEncoder.encode(empID_variable_String,"UTF-8")+"&"+
                    URLEncoder.encode("Date","UTF-8")+"="+URLEncoder.encode(tripDate,"UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

            String result = "";
            String line = "";

            while((line = bufferedReader.readLine()) != null)
            {

                result += line;

                arrResult.add(line);
            }


            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return  result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return null;
    }

    ProgressDialog loading;


    protected void onPreExecute() {
        pd1 = new ProgressDialog(context);
        pd1.setTitle("Driver is Finishing a Trip");
        pd1.setMessage("Please wait...");
        pd1.setCancelable(true);
        pd1.setIndeterminate(true);
        pd1.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Trip Done Status");
    }


    protected void onPostExecute(String result) {
        pd1.dismiss();
        alertDialog.setMessage(result);
        alertDialog.show();


    }


}



