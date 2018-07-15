package com.budgebars.rotelle.gui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.budgebars.rotelle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    Button btnHome,btnGym,btnhiit;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        btnHome = view.findViewById(R.id.btnHome);
        btnGym = view.findViewById(R.id.btnGym);
        btnhiit= view.findViewById(R.id.btnhiit);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListHome.class);
                startActivity(intent);
            }
        });

        btnGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListHome.class);
                startActivity(intent);
            }
        });

        btnhiit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExerciseListingActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

}
