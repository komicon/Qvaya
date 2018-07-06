package com.example.thando.qvaya.Login;

/**
 * Created by Thando on 2018/05/05.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.example.thando.qvaya.AdminDriver.AdminHome;
import com.example.thando.qvaya.BusCoordinator.BusCoordinatorHome;
import com.example.thando.qvaya.Driver.DriverHome;
import com.example.thando.qvaya.Student.StudentHome;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thando.qvaya.AdminDriver.AdminHome;
import com.example.thando.qvaya.Driver.DriverHome;
import com.example.thando.qvaya.StartScreen.BaseActivity;
import com.example.thando.qvaya.R;
import com.example.thando.qvaya.Student.StudentHome;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

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

import static android.support.v4.content.ContextCompat.startActivity;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    String username = "";
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://qvayaapp.000webhostapp.com/Thando/Login.php";
        //String login_url = "https://barcodescanner.000webhostapp.com/QvayaApp/Login.php";
        if(type.equals("login")) {
            try {

                String user_name = params[1];
                String password = params[2];
                username =user_name;
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        pd1.setTitle("Retrieving data");
        pd1.setMessage("Please wait.");
        pd1.setCancelable(true);
        pd1.setIndeterminate(true);
        pd1.show();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(pd1!=null) pd1.dismiss();
       /* alertDialog.setMessage(result);
        alertDialog.show();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();*/




        if(result.equalsIgnoreCase("Student")){
            Intent intent = new Intent(context, StudentHome.class);
            intent.putExtra("langa",username);

            context.startActivity(intent);

        }else
        if(result.equalsIgnoreCase("Admin")){


            Intent intent = new Intent(context, AdminHome.class);

            context.startActivity(intent);
        }else
        if(result.equalsIgnoreCase("Driver")){



            Intent intent = new Intent(context,DriverHome.class);
            intent.putExtra("langa",username);

            context.startActivity(intent);
        }else
        if(result.equalsIgnoreCase("Student")){


            Intent intent = new Intent(context,StudentHome.class);
            intent.putExtra("langa",username);

            context.startActivity(intent);
        }else
        if(result.equalsIgnoreCase("Bus Coordinator")){
            Intent intent = new Intent(context,BusCoordinatorHome.class);
            intent.putExtra("",username);
            //Toast.makeText(context, "BusCoordinator", Toast.LENGTH_LONG).show();
            context.startActivity(intent);
        }else{

            alertDialog.setMessage("Username or Password incorrect");
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}