package com.budgebars.rotelle.gui;

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
    String name;

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
        int value = getIntent().getIntExtra("position", 1);

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

            //Set resource to the following clicked exercise
            if(value ==1)
            {
                detail_image.setImageResource(R.drawable.fitness);
                title.setText("testing 1");
            }
            else if(value ==2)
            {
                detail_image.setImageResource(R.drawable.fitness);
                title.setText("testing 2");
            }
        }


    }
}
