package com.example.thando.qvaya.AdminCoordinator;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

public class BackgroundCreateCoordinator extends AsyncTask<String,Void,String>{
    Context context;
    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    BackgroundCreateCoordinator(Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
       // String CreateCoord_url = "https://qvayaapp.000webhostapp.com/CreateBusCoord.php";
        String CreateCoord_url = "https://qvayaapp.000webhostapp.com/Lungcebo/CreateBusCoord.php";
        if(type.equals("InsertNewBusCoordinator"))
        {
          try {
              String genderEmployee = params[1];
              String JobType = params[2];
              String empName = params[3];
              String empSurname = params[4];
              String empEmail = params[5];
              String empCell = params[6];
              String empYear = params[7];
              String empHome = params[8];
              String empAge = params[9];
              URL url = new URL(CreateCoord_url);
              HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
              httpURLConnection.setRequestMethod("POST");
              httpURLConnection.setDoOutput(true);
              httpURLConnection.setDoInput(true);
              OutputStream outputStream = httpURLConnection.getOutputStream();
              BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
              String post_data = URLEncoder.encode("genderEmployee","UTF-8")+"="+URLEncoder.encode(genderEmployee,"UTF-8")+"&"
                 +URLEncoder.encode("JobType","UTF-8")+"="+URLEncoder.encode(JobType,"UTF-8")+"&"
                      +URLEncoder.encode("empName","UTF-8")+"="+URLEncoder.encode(empName,"UTF-8")+"&"
                      +URLEncoder.encode("empSurname","UTF-8")+"="+URLEncoder.encode(empSurname,"UTF-8")+"&"
                      +URLEncoder.encode("empEmail","UTF-8")+"="+URLEncoder.encode(empEmail,"UTF-8")+"&"
                      +URLEncoder.encode("empCell","UTF-8")+"="+URLEncoder.encode(empCell,"UTF-8")+"&"
                      +URLEncoder.encode("empYear","UTF-8")+"="+URLEncoder.encode(empYear,"UTF-8")+"&"
                      +URLEncoder.encode("empHome","UTF-8")+"="+URLEncoder.encode(empHome,"UTF-8")+"&"
                      +URLEncoder.encode("empAge","UTF-8")+"="+URLEncoder.encode(empAge,"UTF-8");
              bufferedWriter.write(post_data);
              bufferedWriter.flush();
              bufferedWriter.close();
              outputStream.close();
              InputStream inputStream = httpURLConnection.getInputStream();
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
              String result="";
              String line="";
              while((line = bufferedReader.readLine())!= null) {
                  result += line;
              }
              bufferedReader.close();
              inputStream.close();
              httpURLConnection.disconnect();
              return result;
          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {


        pd1 = new ProgressDialog(context);
        pd1.setTitle("Saving");
        pd1.setMessage("Please wait.");
        pd1.setCancelable(true);
        pd1.setIndeterminate(true);
        pd1.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Saved status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(pd1!=null) pd1.dismiss();
        alertDialog.setMessage(result);
        alertDialog.show();

        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
