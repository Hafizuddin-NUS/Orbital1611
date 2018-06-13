package com.example.hafiz.fitnessplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeGym extends AppCompatActivity {

    Button btnHome,btnGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_gym);

        btnHome = (Button)findViewById(R.id.btnHome);
        btnGym = (Button)findViewById(R.id.btnGym);

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



    }
}
