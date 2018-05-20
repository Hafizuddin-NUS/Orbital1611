package com.example.hafiz.fitnessplan;

import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExercises extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;
    ImageView detail_image;
    Button btnStart;

    Boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercises);

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
                    new CountDownTimer(20000, 1000) {
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
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            //Set resource to the following clicked exercise
            detail_image.setImageResource(image_id);
            title.setText(name);
        }


    }
}
