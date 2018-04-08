package com.example.thando.qvaya.AdminCoordinator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.thando.qvaya.AdminCoordinator.AdminCreateCoordinator;
import com.example.thando.qvaya.AdminCoordinator.AdminDeleteCoordinator;
import com.example.thando.qvaya.AdminCoordinator.AdminReadCoordinator;
import com.example.thando.qvaya.AdminCoordinator.AdminUpdateCoordinator;
import com.example.thando.qvaya.R;

/**
 * Created by Thando
 */

public class BusCoordinatorAdminFragment extends Fragment {
    private static final String TAG = "BusCoordinatorAdminFragment";

    private CardView CardViewCreateCoordinator;
    private CardView CardViewDeleteCoordinator;
    private CardView CardViewUpdateCoordinator;
    private CardView CardViewReadCoordinator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bus_coordinator_admin_fragment,container,false);
        CardViewCreateCoordinator =  view.findViewById(R.id.createCoordinatoradminCV);

        CardViewCreateCoordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminCreateCoordinator.class);
                startActivity(intent);
            }
        });


        //--------

        CardViewDeleteCoordinator =  view.findViewById(R.id.deleteCoordinatoradminCV);

        CardViewDeleteCoordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminDeleteCoordinator.class);
                startActivity(intent);
            }
        });

        //-----
        CardViewUpdateCoordinator =  view.findViewById(R.id.updateCoordinatoradminCV);

        CardViewUpdateCoordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminUpdateCoordinator.class);
                startActivity(intent);
            }
        });


        //-----------
        CardViewReadCoordinator =  view.findViewById(R.id.readCoordinatoradminCV);

        CardViewReadCoordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminReadCoordinator.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
