package com.example.thando.qvaya.AdminDriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thando.qvaya.R;

//import com.example.thando.qvaya.activities.AdminReadDriver;

/**
 * Created by Thando
 */

public class DriversAdminFragment extends Fragment {
    private static final String TAG = "BusCoordinatorAdminFragment";

    private CardView CardViewCreatedriver ;
    private CardView CardViewDeletedriver ;
    private CardView CardViewUpdatedriver ;
    private CardView CardViewReaddriver ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driveradminfragment,container,false);
        CardViewCreatedriver =  view.findViewById(R.id.createdriveradminCV);

        CardViewCreatedriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminCreateDriver.class);
                startActivity(intent);
            }
        });


        //--------

        CardViewDeletedriver =  view.findViewById(R.id.deletedriveradminCV);

        CardViewDeletedriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminDeleteDriver.class);
                startActivity(intent);
            }
        });

        //-----
        CardViewUpdatedriver  =  view.findViewById(R.id.updatedriveradminCV);

        CardViewUpdatedriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminUpdateDriver.class);
                startActivity(intent);
            }
        });


        //-----------
        CardViewReaddriver   =  view.findViewById(R.id.readdriveradminCV);

        CardViewReaddriver .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminReadDriver.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
