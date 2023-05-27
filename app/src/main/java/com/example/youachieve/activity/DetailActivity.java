package com.example.youachieve.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.youachieve.R;
import com.example.youachieve.db.entity.Post;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private final ArrayList<Post> postList_ = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button buttonGotoDetail = (Button) findViewById(R.id.button_goto_website);
        buttonGotoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=FTu_ndnh-wc"));
                startActivity(intent);
            }
        });
    }
}