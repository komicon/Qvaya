package com.example.thando.qvaya.pushnotification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.thando.qvaya.BusCoordinator.BusCoordinatorDelaysFragment;
import com.example.thando.qvaya.Login.Login;
import com.example.thando.qvaya.pushNitificationRealocatedriver.ReallocateDriverDelatAlocate;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingServiceDelay  extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    String push_message;
    String push_title;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());

                push_message = json.getJSONObject("data").getString("message");
                push_title   = json.getJSONObject("data").getString("title");

                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    //this method will display the notification
    //We are passing the JSONObject that is received from
    //firebase cloud messaging
    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");

            //creating MyNotificationManager object
            MyNotificationManagerDelay mNotificationManager = new MyNotificationManagerDelay(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(),Login.class);
            Intent populateListview = new Intent(getApplicationContext(),BusCoordinatorDelaysFragment.class);
            populateListview.putExtra("messgagelist", push_message);
            populateListview.putExtra("titlelist",  push_title);


            // Intent intent = new Intent(getBaseContext(), SignoutActivity.class);

            intent.putExtra("EXTRA_SESSION_ID", title );

            // push_message ;
            //push_title;





            Bundle bundle = new Bundle();
            bundle.putString("push_title", push_title);
            // set Fragmentclass Arguments
            // Fragmentclass fragobj = new Fragmentclass();
            // fragobj.setArguments(bundle);



            //if there is no image
            if(imageUrl.equals("null")){
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message,imageUrl, intent);
            }else{
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

}
