package com.example.hafiz.fitnessplan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.hafiz.fitnessplan.Database.FitnessPlanDB;

import java.security.acl.Group;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class SettingPage extends AppCompatActivity {

    Button btnSave;
    RadioButton easy,medium,hard;
    RadioGroup group;
    FitnessPlanDB fitnessPlanDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        btnSave = (Button) findViewById(R.id.btnSave);

        group = (RadioGroup) findViewById(R.id.Group);
        easy = (RadioButton) findViewById(R.id.easy);
        medium = (RadioButton) findViewById(R.id.medium);
        hard = (RadioButton) findViewById(R.id.hard);

        switchAlarm = (ToggleButton) findViewById(R.id.switchAlarm);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        fitnessPlanDB = new FitnessPlanDB(this);

        int mode = fitnessPlanDB.getSettingMode();
        setRadioButton(mode);

        //event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                saveWorkoutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingPage.this,"SAVED",Toast.LENGTH_SHORT).show();
                finish();
            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        if(checked)
        {
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            //Set time to alarm
            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
            calendar.set(toDay.getYear(),toDay.getMonth(),toDay.getDay(),timePicker.getHour(),timePicker.getMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);

            Log.d("Debug", "Alarm will wake at : " + timePicker.getHour()+ ":"+timePicker.getMinute());
        }
        else
        {
            //Cancel alarm
            Intent intent = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }

    }

    private void saveWorkoutMode() {
        int selectID = group.getCheckedRadioButtonId();
        if(selectID == easy.getId())
            fitnessPlanDB.saveSettingMode(0);
        else if(selectID == medium.getId())
            fitnessPlanDB.saveSettingMode(1);
        else if(selectID == hard.getId())
            fitnessPlanDB.saveSettingMode(2);
    }

    private void setRadioButton(int mode) {
        if(mode == 0) group.check(R.id.easy);
        else if(mode == 1) group.check(R.id.medium);
        else if(mode == 2) group.check(R.id.hard);
    }
}
