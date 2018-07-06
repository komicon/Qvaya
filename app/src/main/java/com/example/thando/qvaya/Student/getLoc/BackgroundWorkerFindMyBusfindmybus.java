package com.example.thando.qvaya.Student.getLoc;

/**
 * Created by Thando on 2018/05/05.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class BackgroundWorkerFindMyBusfindmybus extends AsyncTask<String,Void,String> {
    Context context;
    public  static String Driver = "";
    ProgressDialog pd1 ;
    AlertDialog alertDialog;
    String username = "";
    public BackgroundWorkerFindMyBusfindmybus(Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://qvayaapp.000webhostapp.com/Thando/findmybus.php";
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


    }

    @Override
    protected void onPostExecute(String result) {
       /* if(pd1!=null) pd1.dismiss();
       alertDialog.setMessage(result);
        alertDialog.show();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
*/
        Driver = result;
       Log.i("zzzz",result);

        /*
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
        }*/
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}