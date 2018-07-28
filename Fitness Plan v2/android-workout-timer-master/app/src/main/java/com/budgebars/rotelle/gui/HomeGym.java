package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budgebars.rotelle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class HomeGym extends AppCompatActivity {

    Button btnContinue,btnDone;
    TextView workout_Names;
    TextView current_points;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private Uri mainImageURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnContinue = findViewById(R.id.btnContinue);
        btnDone = findViewById(R.id.btnDone);
        current_points= findViewById(R.id.current_points);
        workout_Names = findViewById(R.id.finished_workout);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences preferences = getSharedPreferences("workout", getApplicationContext().MODE_PRIVATE);
        String workout_done = preferences.getString("names","");
        final int difficulty = preferences.getInt("difficulty",0);

        final String currentUserId = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore.collection("Users").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    String name = task.getResult().getString("name");
                    String image = task.getResult().getString("image");
                    String age = task.getResult().getString("age");
                    String gender = task.getResult().getString("gender");
                    String user_points = task.getResult().getString("points");
                    int updated_points = Integer.parseInt(user_points);

                    if(difficulty ==0)
                        updated_points = updated_points +10;
                    else if (difficulty ==1)
                        updated_points = updated_points +20;
                    else if (difficulty ==2)
                        updated_points = updated_points + 30;

                    user_points = Integer.toString(updated_points);
                    current_points.setText(user_points);
                    mainImageURI = Uri.parse(image);

                    storeFirestore(null,currentUserId,name,image,age,gender,user_points);


                } else {

                    //Firebase Exception

                }

            }
        });



        workout_Names.setText(workout_done);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeGym.this, ListHome.class);
                startActivity(intent);
                finish();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeGym.this,NewPostActivity.class);
                startActivity(intent);
                finish();
                //SharedPreferences.Editor editor = getSharedPreferences("workout", MODE_PRIVATE).edit();
                //editor.clear().commit();
            }
        });



    }



    private void storeFirestore(@NonNull Task<UploadTask.TaskSnapshot> task,String userid, String name, String image, String age, String gender,String user_points) {


        Map<String,String> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("age", age);
        userMap.put("gender", gender);
        userMap.put("points", user_points);
        userMap.put("image", mainImageURI.toString());


        firebaseFirestore.collection("Users").document(userid).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(HomeGym.this, "Points Updated ", Toast.LENGTH_LONG).show();

                } else {

                    String error = task.getException().getMessage();
                    Toast.makeText(HomeGym.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();

                }

                //setupProgress.setVisibility(View.INVISIBLE);

            }
        });


    }
}
