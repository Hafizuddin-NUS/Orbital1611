package com.budgebars.rotelle.gui;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class IpptFragment extends Fragment {

    IpptCalculator ipptCalculator;
    private EditText situps;
    private EditText pushups;
    private EditText runTiming;
    private EditText age;
    private EditText gender;
    private TextView finalPoints;
    private TextView previous_points;
    private Button btnCompute;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private Uri mainImageURI = null;

    private int pushupNum, situpNum, ageNum=0;
    private double runTime;
    private String name,image,age3,user_points,gender2,user_ippt;



    public IpptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ippt, container, false);

        situps = view.findViewById(R.id.input_situp);
        pushups = view.findViewById(R.id.input_pushup);
        runTiming = view.findViewById(R.id.input_24);
        age = view.findViewById(R.id.input_age);
        gender = view.findViewById(R.id.input_gender);
        btnCompute = view.findViewById(R.id.btn_compute);
        finalPoints =view.findViewById(R.id.ippt_score);
        ipptCalculator = new IpptCalculator();
        previous_points = view.findViewById(R.id.previous_points);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        final String currentUserId = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore.collection("Users").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    name = task.getResult().getString("name");
                    image = task.getResult().getString("image");
                    age3 = task.getResult().getString("age");
                    gender2 = task.getResult().getString("gender");
                    user_points = task.getResult().getString("points");
                    user_ippt = task.getResult().getString("ippt");

                    age.setText(age3);
                    gender.setText(gender2);
                    previous_points.setText(user_ippt);
                    //ageNum = Integer.parseInt(age3);


                } else {

                    //Firebase Exception

                }

            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                situpNum = Integer.parseInt(situps.getText().toString());
                pushupNum = Integer.parseInt(pushups.getText().toString());
                runTime = Double.parseDouble(runTiming.getText().toString());
                ageNum = Integer.parseInt(age.getText().toString());
                mainImageURI = Uri.parse(image);

                ipptCalculator.computeScore(pushupNum,situpNum,runTime,gender2,ageNum);
                finalPoints.setText(Integer.toString(ipptCalculator.getTotalScore()));
                user_ippt = Integer.toString(ipptCalculator.getTotalScore());
                storeFirestore(null,currentUserId,name,image,age3,gender2,user_points,user_ippt);

            }
        });


        return view;
    }

    private void storeFirestore(@NonNull Task<UploadTask.TaskSnapshot> task, String userid, String name, String image, String age, String gender, String user_points, String user_ippt) {


        Map<String,String> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("age", age);
        userMap.put("gender", gender);
        userMap.put("points", user_points);
        userMap.put("ippt", user_ippt);
        userMap.put("image", mainImageURI.toString());


        firebaseFirestore.collection("Users").document(userid).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    //Toast.makeText(HomeGym.this, "Points Updated ", Toast.LENGTH_LONG).show();

                } else {

                   // String error = task.getException().getMessage();
                    //Toast.makeText(HomeGym.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();

                }

                //setupProgress.setVisibility(View.INVISIBLE);

            }
        });


    }


}
