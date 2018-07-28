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

import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;


public class ViewExercises extends AppCompatActivity {

    int image_id;

    TextView timer,title;
    Button btnStart;
    GifImageView detail_image;

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
        detail_image = findViewById(R.id.detail_img);
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
                            detail_image.setImageResource(R.drawable.abs1);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.abs2);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.abs3);
                            title.setText(name);
                        }
                        else if(value ==3)
                        {
                            detail_image.setImageResource(R.drawable.abs4);
                            title.setText(name);
                        }
                        else if(value ==4)
                        {
                            detail_image.setImageResource(R.drawable.abs5);
                            title.setText(name);
                        }
                        else if(value ==5)
                        {
                            detail_image.setImageResource(R.drawable.abs6);
                            title.setText(name);
                        }
                        else if(value ==6)
                        {
                            detail_image.setImageResource(R.drawable.abs7);
                            title.setText(name);
                        }
                        else if(value ==7)
                        {
                            detail_image.setImageResource(R.drawable.abs8);
                            title.setText(name);
                        }
                        else if(value ==8)
                        {
                            detail_image.setImageResource(R.drawable.abs9);
                            title.setText(name);
                        }
                        else if(value ==9)
                        {
                            detail_image.setImageResource(R.drawable.abs10);
                            title.setText(name);
                        }
                        else if(value ==10)
                        {
                            detail_image.setImageResource(R.drawable.abs11);
                            title.setText(name);
                        }
                        else if(value ==11)
                        {
                            detail_image.setImageResource(R.drawable.abs12);
                            title.setText(name);
                        }
                        else if(value ==12)
                        {
                            detail_image.setImageResource(R.drawable.abs13);
                            title.setText(name);
                        }
                        else if(value ==13)
                        {
                            detail_image.setImageResource(R.drawable.abs14);
                            title.setText(name);
                        }
                        break;

                case 2: if(value ==0)
                        {
                            detail_image.setImageResource(R.drawable.str1);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.str2);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.str3);
                            title.setText(name);
                        }
                        else if(value ==3)
                        {
                            detail_image.setImageResource(R.drawable.str4);
                            title.setText(name);
                        }
                        break;

                case 3: if(value ==0)
                        {
                            detail_image.setImageResource(R.drawable.cardio1);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.cardio2);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.cardio3);
                            title.setText(name);
                        }
                        else if(value ==3)
                        {
                            detail_image.setImageResource(R.drawable.cardio4);
                            title.setText(name);
                        }
                        else if(value ==4)
                        {
                            detail_image.setImageResource(R.drawable.cardio5);
                            title.setText(name);
                        }
                        else if(value ==5)
                        {
                            detail_image.setImageResource(R.drawable.cardio6);
                            title.setText(name);
                        }
                        else if(value ==6)
                        {
                            detail_image.setImageResource(R.drawable.cardio7);
                            title.setText(name);
                        }
                        else if(value ==7)
                        {
                            detail_image.setImageResource(R.drawable.cardio8);
                            title.setText(name);
                        }
                        else if(value ==8)
                        {
                            detail_image.setImageResource(R.drawable.cardio9);
                            title.setText(name);
                        }
                        else if(value ==9)
                        {
                            detail_image.setImageResource(R.drawable.cardio10);
                            title.setText(name);
                        }
                        else if(value ==10)
                        {
                            detail_image.setImageResource(R.drawable.cardio11);
                            title.setText(name);
                        }
                        else if(value ==11)
                        {
                            detail_image.setImageResource(R.drawable.cardio12);
                            title.setText(name);
                        }
                        break;
                case 5: if(value ==0)
                        {
                            detail_image.setImageResource(R.drawable.strgym1);
                            title.setText(name);
                        }
                        else if(value ==1)
                        {
                            detail_image.setImageResource(R.drawable.strgym2);
                            title.setText(name);
                        }
                        else if(value ==2)
                        {
                            detail_image.setImageResource(R.drawable.strgym3);
                            title.setText(name);
                        }
                        else if(value ==3)
                        {
                            detail_image.setImageResource(R.drawable.strgym4);
                            title.setText(name);
                        }
                        else if(value ==4)
                        {
                            detail_image.setImageResource(R.drawable.strgym5);
                            title.setText(name);
                        }
                        else if(value ==5)
                        {
                            detail_image.setImageResource(R.drawable.strgym6);
                            title.setText(name);
                        }
                        else if(value ==6)
                        {
                            detail_image.setImageResource(R.drawable.strgym7);
                            title.setText(name);
                        }
                        else if(value ==7)
                        {
                            detail_image.setImageResource(R.drawable.strgym8);
                            title.setText(name);
                        }
                        else if(value ==8)
                        {
                            detail_image.setImageResource(R.drawable.strgym9);
                            title.setText(name);
                        }
                        break;
                case 6: if(value ==0)
                {
                    detail_image.setImageResource(R.drawable.cardiogym1);
                    title.setText(name);
                }
                else if(value ==1)
                {
                    detail_image.setImageResource(R.drawable.cardiogym2);
                    title.setText(name);
                }
                break;
            }

        }



    }


}
