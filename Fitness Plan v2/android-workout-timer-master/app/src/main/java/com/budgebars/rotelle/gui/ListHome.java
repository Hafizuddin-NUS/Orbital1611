package com.budgebars.rotelle.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Button;


import com.budgebars.rotelle.R;

public class ListHome extends AppCompatActivity {

    Button btnabs,btnstrength, btncardio;
    private Toolbar mainToolbar;
    private BottomNavigationView mainbottomNav;
    private AbsFragment absFragment;
    private CardioFragment cardioFragment;
    private StrFragment strFragment;
    private int mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_home);

        //btnabs = findViewById(R.id.btnabs);
      //  btnstrength = findViewById(R.id.btnstrength);
        //btncardio = findViewById(R.id.btncardio);
        mode = getIntent().getIntExtra("mode",1);
        mainToolbar = findViewById(R.id.exercise_toolbar);
        setSupportActionBar(mainToolbar);
        if(mode ==1)
            getSupportActionBar().setTitle("Exercise Mode - Home");
        else
            getSupportActionBar().setTitle("Exercise Mode - Gym");
/*
        btnabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, RecommendedList.class);
                intent.putExtra("exercise", 1);
                startActivity(intent);
            }
        });
        btnstrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, RecommendedList.class);
                intent.putExtra("exercise", 2);
                startActivity(intent);
            }
        });
        btncardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListHome.this, RecommendedList.class);
                intent.putExtra("exercise", 3);
                startActivity(intent);
            }
        }); */

        mainbottomNav = findViewById(R.id.exercise_btm_bar);

        // FRAGMENTS
        absFragment = new AbsFragment();
        cardioFragment = new CardioFragment();
        strFragment = new StrFragment();

        initializeFragment();

        mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container2);

                switch (item.getItemId()) {

                    case R.id.action_abs:

                        if(mode ==1)
                            getSupportActionBar().setTitle("Exercise Mode - Home");
                        else
                            getSupportActionBar().setTitle("Exercise Mode - Gym");
                        replaceFragment(absFragment);
                        return true;

                    case R.id.action_str:

                        if(mode ==1)
                            getSupportActionBar().setTitle("Exercise Mode - Home");
                        else
                            getSupportActionBar().setTitle("Exercise Mode - Gym");
                        replaceFragment(strFragment);
                        return true;

                    case R.id.action_cardio:

                        if(mode ==1)
                            getSupportActionBar().setTitle("Exercise Mode - Home");
                        else
                            getSupportActionBar().setTitle("Exercise Mode - Gym");
                        replaceFragment(cardioFragment);
                        return true;

                    default:
                        return false;


                }

            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.exercise_menu_top, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_home2:
                Intent intent = new Intent(ListHome.this, startActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_exercise_settings2:

                Intent exerciseIntent = new Intent(ListHome.this, SettingPage.class);
                startActivity(exerciseIntent);
                return true;

            case R.id.gym_menu:

                Intent gymIntent = new Intent(ListHome.this, ListHome.class);
                gymIntent.putExtra("mode",2);
                startActivity(gymIntent);
                finish();
                return true;

            case R.id.home_menu2:

                Intent homeIntent = new Intent(ListHome.this, ListHome.class);
                homeIntent.putExtra("mode",1);
                startActivity(homeIntent);
                finish();
                return true;

            default:
                return false;


        }

    }

    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container2, absFragment);
        fragmentTransaction.add(R.id.main_container2, cardioFragment);
        fragmentTransaction.add(R.id.main_container2, strFragment);

        fragmentTransaction.hide(cardioFragment);
        fragmentTransaction.hide(strFragment);

        fragmentTransaction.commit();

    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == absFragment){

            fragmentTransaction.hide(strFragment);
            fragmentTransaction.hide(cardioFragment);

        }

        if(fragment == strFragment){

            fragmentTransaction.hide(absFragment);
            fragmentTransaction.hide(cardioFragment);

        }

        if(fragment == cardioFragment){

            fragmentTransaction.hide(absFragment);
            fragmentTransaction.hide(strFragment);

        }
        fragmentTransaction.show(fragment);

        //fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }

    public int getMode() {
        return mode;
    }

}
