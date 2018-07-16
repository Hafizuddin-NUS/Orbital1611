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
    private NotificationFragment notificationFragment;
    private AccountFragment accountFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_home);

        //btnabs = findViewById(R.id.btnabs);
      //  btnstrength = findViewById(R.id.btnstrength);
        //btncardio = findViewById(R.id.btncardio);
        mainToolbar = findViewById(R.id.exercise_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Exercise Mode");
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
        notificationFragment = new NotificationFragment();
        accountFragment = new AccountFragment();

        initializeFragment();

        mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container2);

                switch (item.getItemId()) {

                    case R.id.action_abs:

                        replaceFragment(absFragment);
                        return true;

                    case R.id.action_str:

                        replaceFragment(accountFragment);
                        return true;

                    case R.id.action_cardio:

                        replaceFragment(notificationFragment);
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

            default:
                return false;


        }

    }

    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container2, absFragment);
        fragmentTransaction.add(R.id.main_container2, notificationFragment);
        fragmentTransaction.add(R.id.main_container2, accountFragment);

        fragmentTransaction.hide(notificationFragment);
        fragmentTransaction.hide(accountFragment);

        fragmentTransaction.commit();

    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == absFragment){

            fragmentTransaction.hide(accountFragment);
            fragmentTransaction.hide(notificationFragment);

        }

        if(fragment == accountFragment){

            fragmentTransaction.hide(absFragment);
            fragmentTransaction.hide(notificationFragment);

        }

        if(fragment == notificationFragment){

            fragmentTransaction.hide(absFragment);
            fragmentTransaction.hide(accountFragment);

        }
        fragmentTransaction.show(fragment);

        //fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }
}
