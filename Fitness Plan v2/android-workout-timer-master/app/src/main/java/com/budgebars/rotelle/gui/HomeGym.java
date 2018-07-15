package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.budgebars.rotelle.R;

public class HomeGym extends AppCompatActivity {

    Button btnContinue,btnDone;
    TextView workout_Names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnContinue = findViewById(R.id.btnContinue);
        btnDone = findViewById(R.id.btnDone);
        workout_Names = findViewById(R.id.finished_workout);

        SharedPreferences preferences = getSharedPreferences("workout", getApplicationContext().MODE_PRIVATE);
        String workout_done = preferences.getString("names","");

        workout_Names.setText(workout_done);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeGym.this, ListHome.class);
                startActivity(intent);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("workout", MODE_PRIVATE).edit();
                editor.clear().commit();
            }
        });



    }
}
