package com.example.thando.qvaya.AdminDriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.thando.qvaya.R;

import com.bumptech.glide.Glide;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

/**
 * Created by delaroy on 3/31/17.
 */
public class DetailActivity extends AppCompatActivity {
    TextView NumberOfSong, NameOfsong, Joindriver;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminReadDriver.class));
            }
        });

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        NameOfsong = (TextView) findViewById(R.id.name);
        Joindriver = (TextView) findViewById(R.id.driverjoinyeardata);
        NumberOfSong = (TextView) findViewById(R.id.numberofsongsdata);

        String songname = getIntent().getExtras().getString("name");
        String numofsongs = getIntent().getExtras().getString("ratings");
        String thumbnail = getIntent().getExtras().getString("thumbnail");
        String joindriver = getIntent().getExtras().getString("YearOfRelease");

        NameOfsong.setText(songname);
        NumberOfSong.setText(numofsongs);
        Joindriver.setText(joindriver);

        Glide.with(this)
                .load(thumbnail)

                .into(imageView);



    }

}
