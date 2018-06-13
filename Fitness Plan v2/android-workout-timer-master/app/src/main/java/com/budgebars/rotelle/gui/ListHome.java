package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.budgebars.rotelle.R;

public class ListHome extends AppCompatActivity {

    Button btnabs,btnstrength;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_home);

        btnabs = (Button) findViewById(R.id.btnabs);
        btnstrength = (Button) findViewById(R.id.btnstrength);

        btnabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, ViewExercises.class);
                intent.putExtra("position", 1);
                startActivity(intent);
            }
        });
        btnstrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, ViewExercises.class);
                intent.putExtra("position", 2);
                startActivity(intent);
            }
        });



    }
}
