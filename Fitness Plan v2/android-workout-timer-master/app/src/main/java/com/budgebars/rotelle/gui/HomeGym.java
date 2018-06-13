package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.budgebars.rotelle.R;

public class HomeGym extends AppCompatActivity {

    Button btnHome,btnGym,btnhiit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_gym);

        btnHome = (Button)findViewById(R.id.btnHome);
        btnGym = (Button)findViewById(R.id.btnGym);
        btnhiit= (Button)findViewById(R.id.btnhiit);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGym.this, ListHome.class);
                startActivity(intent);
            }
        });

        btnGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGym.this, ListHome.class);
                startActivity(intent);
            }
        });

        btnhiit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGym.this, ExerciseListingActivity.class);
                startActivity(intent);
            }
        });



    }
}
