package com.example.thando.qvaya.pushNotificationStudent;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONObject;

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



public class BackgroundSetReminderMb extends AsyncTask<String,Void,String> {


    Context context;
    AlertDialog alertDialog;
    List<String> arrResult = new ArrayList();


    public BackgroundSetReminderMb(Context ctx)
    {
        context = ctx;
    }


    protected String doInBackground(String... params) {


        String upd_url = "https://qvayaapp.000webhostapp.com/MB/readAndInsertPreference.php";
        //String result = "";

        try {

            String studentNumber = params[0];
            String time=params[1];
            String isEveryday=params[2];
            String token=params[3];


            URL url = new URL(upd_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data  =    URLEncoder.encode("studentNumber","UTF-8")+"="+URLEncoder.encode(studentNumber,"UTF-8")+"&"+
                    URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"+
                    URLEncoder.encode("isEveryday","UTF-8")+"="+URLEncoder.encode(isEveryday,"UTF-8")+"&"+
                    URLEncoder.encode("token","UTF-8")+"="+URLEncoder.encode(token,"UTF-8");

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
      //alertDialog = new AlertDialog.Builder(context).create();
       //alertDialog.setTitle("Update Status");
       //super.onPreExecute();
     loading = ProgressDialog.show(context,"Please wait",null,true,true);
        loading.setMessage("Setting reminder..");
        loading.show();
    }


    protected void onPostExecute(String result) {
       //alertDialog.setMessage(result);
        //alertDialog.show();
        loading.hide();
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }


}



