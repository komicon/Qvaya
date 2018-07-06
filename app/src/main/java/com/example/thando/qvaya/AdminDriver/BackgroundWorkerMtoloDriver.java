package com.example.thando.qvaya.AdminDriver;


/**
 * Created by SEC TECHNOLOGY on 2018/06/07.
 */

import android.os.AsyncTask;
import android.os.Handler;
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
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.app.ProgressDialog;

public class BackgroundWorkerMtoloDriver extends AsyncTask<String,Void,String> {

    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    Context context;
    //AlertDialog alertDialog;


    BackgroundWorkerMtoloDriver(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {





        //String type = params[0];
        String upd_url = "https://qvayaapp.000webhostapp.com/Thabiso/deletingDriverRecord.php";
//        String upd_url = "https://10.0.2.226/proj/UpdateBusCoordinator.php";
        // if(type.equals("button2_delete"))
        // {

        try {
            String yearLeftVariable = params[0];
            String reasonLeavingVariable = params[1];
            String valueFromSpinner = params[2];

            URL url = new URL(upd_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data  =    URLEncoder.encode("YearLeft","UTF-8")+"="+URLEncoder.encode(yearLeftVariable,"UTF-8")+"&"
                    + URLEncoder.encode("Reason","UTF-8")+"="+URLEncoder.encode(reasonLeavingVariable,"UTF-8")
                    +"&"+ URLEncoder.encode("EmployeeID","UTF-8")+"="+URLEncoder.encode(valueFromSpinner,"UTF-8");


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





    @Override
    protected void onPreExecute() {
        pd1 = new ProgressDialog(context);

        pd1.setTitle("Deleting data");
        pd1.setMessage("Please wait.");
        pd1.setCancelable(true);
        pd1.setIndeterminate(true);
        pd1.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Delete Status");
    }

    @Override
    protected void onPostExecute(String result) {

        pd1.dismiss();
        alertDialog.setMessage("Driver Deleted Succcessfully");
        alertDialog.show();


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
