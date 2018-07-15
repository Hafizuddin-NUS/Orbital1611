package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.budgebars.rotelle.Database.FitnessPlanDB;
import com.budgebars.rotelle.R;
import com.budgebars.rotelle.Utils.Common;


public class ViewExercises extends AppCompatActivity {

    int image_id;

    TextView timer,title;
    ImageView detail_image;
    Button btnStart;

    Boolean isRunning = false;

    FitnessPlanDB fitnessPlanDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);

        fitnessPlanDB = new FitnessPlanDB(this);
        int value = getIntent().getIntExtra("position", 0);
        int list = getIntent().getIntExtra("list", 1);
        final String name = getIntent().getStringExtra("name");

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.title);
        detail_image = (ImageView)findViewById(R.id.detail_img);
        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRunning)
                {
                    btnStart.setText("Done");

                    int timeLimit = 0;
                    if(fitnessPlanDB.getSettingMode() == 0)
                        timeLimit = Common.TIME_LIMIT_EASY;
                    else if(fitnessPlanDB.getSettingMode() == 1)
                            timeLimit = Common.TIME_LIMIT_MEDIUM;
                    else if(fitnessPlanDB.getSettingMode() == 2)
                        timeLimit = Common.TIME_LIMIT_HARD;


                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long l) {
                            timer.setText(""+l/1000);
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewExercises.this, "Finish!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ViewExercises.this, HomeGym.class);

                            //save String for workout done
                            SharedPreferences preferences = getSharedPreferences("workout", getApplicationContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = getSharedPreferences("workout", MODE_PRIVATE).edit();

                            String workout_done = preferences.getString("names","Exercise Done: ");
                            workout_done = workout_done + name + ", ";
                            editor.putString("names", workout_done);
                            editor.commit();
                            startActivity(intent);
                            finish();
                        }
                    }.start();
                }
                else
                {
                    Toast.makeText(ViewExercises.this, "Finish!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                isRunning = !isRunning;

            }
        });


        if(getIntent() != null)
        {
            switch(list){
                case 1: if(value ==0)
                        {
                            detail_image.setImageResource(R.drawable.abs);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.abs);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.abs);
                            title.setText(name);
                        }
                        break;
                case 2: if(value ==0)
                        {
                            detail_image.setImageResource(R.drawable.fitness);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.fitness);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.fitness);
                            title.setText(name);
                        }
                            break;
                case 3: if(value ==0)
                        {
                            detail_image.setImageResource(R.drawable.cardio);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.cardio);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.cardio);
                            title.setText(name);
                        }
                            break;
            }

        }



    }


}
