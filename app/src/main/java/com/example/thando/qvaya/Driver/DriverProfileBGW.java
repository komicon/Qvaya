package com.example.thando.qvaya.Driver;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
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

public class DriverProfileBGW  extends AsyncTask<String,Void,String> {

    Context context;
    ProgressDialog progressDialog;
    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    List<String> arrResult = new ArrayList();
    boolean isFirst = false;



    DriverProfileBGW (Context ctx)
    {
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {



        String type = params[0];
        //String upd_url = "https://qvayaapp.000webhostapp.com/Mtha/ReassignDrivers.php";
        //String upd_url2 = "https://qvayaapp.000webhostapp.com/Mtha/getoldDriverTripdetails.php";
        //String fil_url = "https://qvayaapp.000webhostapp.com/Mtha/pushResponce/sendMultiplePushRes.php";
        String fetch_url = "https://qvayaapp.000webhostapp.com/MB/readDriver.php";
//        String upd_url = "https://10.0.2.226/proj/UpdateBusCoordinator.php";
        if(type.equals("sefing"))
        {

            isFirst = true;
            try {
                String EmployeeID = params[1];
                // String timeTime = params[2];



                URL url = new URL(fetch_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data  = URLEncoder.encode("EmployeeID","UTF-8")+"="+URLEncoder.encode(EmployeeID,"UTF-8");


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



        }

        return null;
    }

    ProgressDialog loading;

    @Override
    protected void onPreExecute() {
       //alertDialog = new AlertDialog.Builder(context).create();
       //alertDialog.setTitle("Driver Profile");
        //super.onPreExecute();
        //loading = ProgressDialog.show(context,"Please wait",null,true,true);
        //loading.setMessage("Fetching profile");
        //loading.show();




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




        pd1 = new ProgressDialog(context);
        pd1.setTitle("Fetching Infomation");
        pd1.setMessage("Please wait.");
        pd1.setCancelable(true);
        pd1.setIndeterminate(true);
        pd1.show();
        pd1.hide();

        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("reallocating driver");

    }

    @Override
    protected void onPostExecute(String result) {
//        alertDialog.setMessage(result);
//        alertDialog.show();
        // Intent intent = new Intent(context,ReallocateDriverDelatAlocate.class);

        // Intent intent = new Intent(getBaseContext(), SignoutActivity.class);
        //intent.putExtra("result", result );
        //Intent

        if(isFirst == false)
        {
            pd1.dismiss();
            alertDialog.setMessage("Profile found");
            alertDialog.show();
            //Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
