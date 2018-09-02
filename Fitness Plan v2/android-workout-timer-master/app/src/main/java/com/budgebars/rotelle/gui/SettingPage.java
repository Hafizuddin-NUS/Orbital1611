package com.budgebars.rotelle.gui;


import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.budgebars.rotelle.Database.FitnessPlanDB;
import com.budgebars.rotelle.R;

import java.util.Calendar;
import java.util.Date;

public class SettingPage extends AppCompatActivity {

    Button btnSave;
    RadioButton easy,medium,hard;
    RadioGroup group;
    FitnessPlanDB fitnessPlanDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        btnSave = (Button) findViewById(R.id.btnSave);

        group = (RadioGroup) findViewById(R.id.Group);
        easy = (RadioButton) findViewById(R.id.easy);
        medium = (RadioButton) findViewById(R.id.medium);
        hard = (RadioButton) findViewById(R.id.hard);

        fitnessPlanDB = new FitnessPlanDB(this);

        int mode = fitnessPlanDB.getSettingMode();
        setRadioButton(mode);

        //event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                saveWorkoutMode();
                finish();
            }
        });

    }


    private void saveWorkoutMode() {
        int selectID = group.getCheckedRadioButtonId();
        SharedPreferences.Editor editor = getSharedPreferences("workout", MODE_PRIVATE).edit();
        if(selectID == easy.getId()) {
            fitnessPlanDB.saveSettingMode(0);
            editor.putInt("difficulty", 0);
            editor.commit();
        }
        else if(selectID == medium.getId()) {
            fitnessPlanDB.saveSettingMode(1);
            editor.putInt("difficulty", 1);
            editor.commit();
        }
        else if(selectID == hard.getId()) {
            fitnessPlanDB.saveSettingMode(2);
            editor.putInt("difficulty", 2);
            editor.commit();
        }

    }

    private void setRadioButton(int mode) {
        if(mode == 0) group.check(R.id.easy);
        else if(mode == 1) group.check(R.id.medium);
        else if(mode == 2) group.check(R.id.hard);
    }
}
