package com.example.thando.qvaya.Student;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

public class BackTasks extends AsyncTask<String,Void,String> {

    Context context;


    BackTasks(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {


        String type = params[0];
        String upd_url2 = "https://qvayaapp.000webhostapp.com/Mtha/getDriverFromStudent.php";
        if(type.equals("first"))
        {
            try {
                String studNum = params[1];
                URL url = new URL(upd_url2);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data  = URLEncoder.encode("studenNumber","UTF-8")+"="+URLEncoder.encode(studNum,"UTF-8");


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



        }


        return null;
    }

   // ProgressDialog loading;

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
