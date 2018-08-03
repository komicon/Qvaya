package com.example.thando.qvaya.pushNitificationRealocatedriver;

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

public class BackgroundWorkerDelatAlocate extends AsyncTask<String,Void,String> {

    Context context;


    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    List<String>  arrResult = new  ArrayList();
    boolean isFirst = false;

    BackgroundWorkerDelatAlocate(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {


        String type = params[0];
        String upd_url = "https://qvayaapp.000webhostapp.com/Mtha/ReassignDrivers.php";
        String upd_url2 = "https://qvayaapp.000webhostapp.com/Mtha/getoldDriverTripdetails.php";
        String fil_url = "https://qvayaapp.000webhostapp.com/Mtha/pushResponce/sendMultiplePushRes.php";
//        String upd_url = "https://10.0.2.226/proj/UpdateBusCoordinator.php";
        if(type.equals("Update"))
        {

            isFirst = false;
            try {
                String empIdOld = params[1];
                String empIdNew = params[2];
                String time = params[3];
                String ress = params[4];
                String reason = params[5];
                String bus = params[6];



                URL url = new URL(upd_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data  = URLEncoder.encode("EmployID","UTF-8")+"="+URLEncoder.encode(empIdOld,"UTF-8")+"&"
                        + URLEncoder.encode("EmployeeID","UTF-8")+"="+URLEncoder.encode(empIdNew,"UTF-8")+"&"
                        + URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"
                        + URLEncoder.encode("ResID","UTF-8")+"="+URLEncoder.encode(ress,"UTF-8")+"&"
                        + URLEncoder.encode("ReasonOfDelay","UTF-8")+"="+URLEncoder.encode(reason,"UTF-8")+"&"
                        + URLEncoder.encode("buss","UTF-8")+"="+URLEncoder.encode(bus,"UTF-8");


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
        else if(type.equals("first"))
        {

            isFirst = true;
            try {
                String empIdOld2 = params[1];
                //String reason = params[5];



                URL url = new URL(upd_url2);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data  = URLEncoder.encode("EmployedID","UTF-8")+"="+URLEncoder.encode(empIdOld2,"UTF-8");


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
        else if(type.equals("sefing"))
        {

            isFirst = true;
            try {
                String ressOld = params[1];
                String timeTime = params[2];



                URL url = new URL(fil_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data  = URLEncoder.encode("oldResss","UTF-8")+"="+URLEncoder.encode(ressOld,"UTF-8")+"&"
                        + URLEncoder.encode("oldTimes","UTF-8")+"="+URLEncoder.encode(timeTime,"UTF-8");


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




        pd1 = new ProgressDialog(context);
        pd1.setTitle("Updating Infomation");
        pd1.setMessage("Please wait.");
        pd1.setCancelable(true);
        pd1.setIndeterminate(true);
        pd1.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("reallocating driver");

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
            alertDialog.setMessage("realocated successful");
            alertDialog.show();
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
