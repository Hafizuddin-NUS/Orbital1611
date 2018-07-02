package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.budgebars.rotelle.R;

public class RecommendedList extends AppCompatActivity {

    ListView absList;
    int value1;
    String[] listviewitemsAbs = {"Abs Exercise 1", "Abs Exercise 2", "Abs Exercise 3"};
    String[] listviewitemsCardio = {"Cardio Exercise 1", "Cardio Exercise 2", "Cardio Exercise 3"};
    String[] listviewitemsStrength = {"Str Exercise 1", "Str Exercise 2", "Str Exercise 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_abs_list);

        //get value for abs or cardio exercise list
        int value = getIntent().getIntExtra("exercise", 1);
        value1 = value;

        absList = (ListView) findViewById(R.id.abslist);
        ListAdapter adapter;

        switch(value){
            case 1: adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listviewitemsAbs);
                    break;
            case 2: adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listviewitemsStrength);
                    break;
            case 3: adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listviewitemsCardio);
                    break;
            default:adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listviewitemsStrength);
                    break;
        }

        absList.setAdapter(adapter);
        absList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String TemplistView = listviewitemsAbs[position].toString();

                switch (value1){
                    case 1: TemplistView = listviewitemsAbs[position].toString();
                            break;
                    case 2: TemplistView = listviewitemsStrength[position].toString();
                            break;
                    case 3: TemplistView = listviewitemsCardio[position].toString();
                            break;
                }

                Intent intent = new Intent(RecommendedList.this, ViewExercises.class);
                intent.putExtra("list",value1);
                intent.putExtra("name", TemplistView);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

    }
}
