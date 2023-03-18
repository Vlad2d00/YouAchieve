package com.example.youachieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    NewsFragment newsFragment =             new NewsFragment();
    MessengerFragment messengerFragment =   new MessengerFragment();
    TasksFragment tasksFragment =           new TasksFragment();
    ProjectsFragment projectsFragment =     new ProjectsFragment();
    UserFragment userFragment =             new UserFragment();
    PostDetailFragment postDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("YouAchieve", "MainActivity onCreate() called");
        setContentView(R.layout.activity_main);

        if (!DataBase.isInit())
            DataBase.initData();

        postDetailFragment = new PostDetailFragment(DataBase.postList.get(0));

        this.bottomNavigationView = findViewById(R.id.navigationView);
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        this.bottomNavigationView.setSelectedItemId(R.id.buttonTasks);

        setListenerButtons();
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

    private void setListenerButtons() {
        // Настройки
        ImageButton buttonGotoDetail = (ImageButton) findViewById(R.id.buttonSettings);
        buttonGotoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });
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
                replaceContent(tasksFragment, R.string.tasks);
                return true;

            case R.id.buttonProjects:
                replaceContent(projectsFragment, R.string.projects);
                return true;

            case R.id.buttonUser:
                replaceContent(postDetailFragment, R.string.user);
                return true;
        }
        return false;
    }

}