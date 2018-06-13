package com.example.hafiz.fitnessplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListHome extends AppCompatActivity {

    Button btnabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_home);

        btnabs = (Button) findViewById(R.id.btnabs);

        btnabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, ListExercises.class);
                startActivity(intent);
            }
        });



    }
}
