package com.example.youachieve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.w("YouAchieve", "DetailActivity onCreate() called");

        Button buttonGotoWebsite = (Button) findViewById(R.id.button_goto_website);
        buttonGotoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("YouAchieve", "click button_goto_website");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=FTu_ndnh-wc"));
                startActivity(intent);
            }
        });

        Button buttonGotoDetail = (Button) findViewById(R.id.button_goto_main);
        buttonGotoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("YouAchieve", "click button_goto_main");
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("YouAchieve", "DetailActivity onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("YouAchieve", "DetailActivity onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("YouAchieve", "DetailActivity onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("YouAchieve", "DetailActivity onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("YouAchieve", "DetailActivity onDestroy() called");
    }

    public void jumpToMainActivity(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void jumpToWebsite(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/"));
        startActivity(intent);
    }
}