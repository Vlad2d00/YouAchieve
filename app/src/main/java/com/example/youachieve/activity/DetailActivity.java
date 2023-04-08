package com.example.youachieve.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.youachieve.PostDetailAdapter;
import com.example.youachieve.R;
import com.example.youachieve.data.DataBase;
import com.example.youachieve.data.Post;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private final ArrayList<Post> postList_ = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        postList_.add(DataBase.selectedPost);

        RecyclerView recyclerView = findViewById(R.id.postDetailList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PostDetailAdapter(postList_));

    }
}