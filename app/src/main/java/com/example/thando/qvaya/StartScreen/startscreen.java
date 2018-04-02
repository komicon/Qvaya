package com.example.thando.qvaya.StartScreen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.thando.qvaya.Login.Login;
import com.example.thando.qvaya.PreferenceManager.PreferenceManager;
import com.example.thando.qvaya.R;

public class startscreen extends AppCompatActivity {

     VideoView videoHolder;
    private ViewPager viewPager;
    private WelcomeActivity.MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PreferenceManager prefManager;

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(startscreen.this, Login.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PreferenceManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_startscreen);
        videoHolder = (VideoView) findViewById(R.id.vid1);
        try{

            Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.s8);
            videoHolder.setVideoURI(video);

            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    jump();
                }

            });
            videoHolder.start();
        } catch(Exception ex) {
            jump();
        }



    }

    private void jump() {
        if(isFinishing())
            return;
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}