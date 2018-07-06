package com.example.thando.qvaya.AdminDriver;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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

public class updateDriver  extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    List<String> arrResult = new ArrayList();
    ;

    updateDriver(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {


       // String type = params[0];
        String upd_url = "https://qvayaapp.000webhostapp.com/Wonder/updateDriver.php";
//        String upd_url = "https://10.0.2.226/proj/UpdateBusCoordinator.php";


            try {
                String empId = params[0];
                String firstname = params[1];
                String lastname = params[2];
                String gender = params[3];
                String email = params[4];
                String age = params[5];
                String adress = params[6];
                String jobType = params[7];
                String cellnumber = params[8];
                String yearStar = params[9];


                URL url = new URL(upd_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("EmployeeID", "UTF-8") + "=" + URLEncoder.encode(empId, "UTF-8") + "&"
                        + URLEncoder.encode("EmployeeName", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&"
                        + URLEncoder.encode("EmployeeSurname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&"
                        + URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&"
                        + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("Age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&"
                        + URLEncoder.encode("HomeAddress", "UTF-8") + "=" + URLEncoder.encode(adress, "UTF-8") + "&"
                        + URLEncoder.encode("JobType", "UTF-8") + "=" + URLEncoder.encode(jobType, "UTF-8") + "&"
                        + URLEncoder.encode("CellNumber", "UTF-8") + "=" + URLEncoder.encode(cellnumber, "UTF-8") + "&"

                        + URLEncoder.encode("YearStarted", "UTF-8") + "=" + URLEncoder.encode(yearStar, "UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {

                    result += line;

                    arrResult.add(line);
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




        return null;
    }

    ProgressDialog loading;

    @Override
    protected void onPreExecute() {
//       alertDialog = new AlertDialog.Builder(context).create();
//       alertDialog.setTitle("Update Status");
//        super.onPreExecute();
//        loading = ProgressDialog.show(context,"Please wait",null,true,true);
//        loading.setMessage("Updating Coordinator");
//        loading.show();
//
//        Runnable prRunnable = new Runnable () {
//
//            public void  run() {
//
//            loading.cancel();
//            Toast.makeText(context, "Bus Coordinator Updated", Toast.LENGTH_SHORT).show();
//
//            }
//        };
//        Handler handler = new Handler();
//        handler.postDelayed(prRunnable,3000);
    }

    @Override
    protected void onPostExecute(String result) {
//        alertDialog.setMessage(result);
//        alertDialog.show();

        //  Toast.makeText(context,"Done"+result,Toast.LENGTH_LONG).show();;


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

