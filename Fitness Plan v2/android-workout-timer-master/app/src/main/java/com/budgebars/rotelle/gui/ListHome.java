package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.budgebars.rotelle.R;

public class ListHome extends AppCompatActivity {

    Button btnabs,btnstrength, btncardio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_home);

        btnabs = (Button) findViewById(R.id.btnabs);
        btnstrength = (Button) findViewById(R.id.btnstrength);
        btncardio = findViewById(R.id.btncardio);

        btnabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, RecommendedList.class);
                intent.putExtra("exercise", 1);
                startActivity(intent);
            }
        });
        btnstrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, RecommendedList.class);
                intent.putExtra("exercise", 2);
                startActivity(intent);
            }
        });
        btncardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, RecommendedList.class);
                intent.putExtra("exercise", 3);
                startActivity(intent);
            }
        });




    }
}
