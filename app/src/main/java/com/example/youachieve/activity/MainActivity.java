package com.example.youachieve.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.youachieve.MessengerFragment;
import com.example.youachieve.NewsFragment;
import com.example.youachieve.ProjectsFragment;
import com.example.youachieve.R;
import com.example.youachieve.UserDetailFragment;
import com.example.youachieve.utils.MyConfig;
import com.example.youachieve.utils.MyData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private final NewsFragment newsFragment =               new NewsFragment();
    private final MessengerFragment messengerFragment =     new MessengerFragment();
//    private final TasksFragment tasksFragment =           new TasksFragment();
    private final ProjectsFragment projectsFragment =       new ProjectsFragment();
    private final UserDetailFragment userDetailFragment =   new UserDetailFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("YouAchieve", "MainActivity onCreate() called");
        setContentView(R.layout.activity_main);

        if (MyData.appContext == null)
            MyData.appContext = getApplicationContext();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(MyConfig.FRAGMENT_START_ID);

        ImageButton buttonGotoDetail = (ImageButton) findViewById(R.id.buttonSettings);
        buttonGotoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("YouAchieve", "MainActivity onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("YouAchieve", "MainActivity onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("YouAchieve", "MainActivity onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("YouAchieve", "MainActivity onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("YouAchieve", "MainActivity onDestroy() called");
    }

    private void replaceContent(Fragment fragment, int idResHeaderText) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragment).commit();
        TextView menuTitle = (TextView)findViewById(R.id.headerText);
        menuTitle.setText(idResHeaderText);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buttonNews:
                replaceContent(newsFragment, R.string.news);
                return true;

            case R.id.buttonMessenger:
                replaceContent(messengerFragment, R.string.messenger);
                return true;

            case R.id.buttonTasks:
                // Временно отключим (((
                replaceContent(projectsFragment, R.string.tasks);
                return true;

            case R.id.buttonProjects:
                replaceContent(projectsFragment, R.string.projects);
                return true;

            case R.id.buttonUser:
                replaceContent(userDetailFragment, R.string.user);
                return true;
        }
        return false;
    }

}