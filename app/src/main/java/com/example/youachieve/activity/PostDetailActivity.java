package com.example.youachieve.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.youachieve.PostDetailAdapter;
import com.example.youachieve.PostDetailFragment;
import com.example.youachieve.R;
import com.example.youachieve.utils.MyData;
import com.example.youachieve.utils.PostData;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {
    private ArrayList<PostData> posts_ = new ArrayList<PostData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        // Парсим переданный параметр
        Intent intent = getIntent();
        int posId = intent.getIntExtra("postId", 0);
        PostData postData = null;
        for (PostData el : MyData.posts) {
            if (el.post.id == posId) {
                postData = el;
                break;
            }
        }

        PostDetailFragment fragment = new PostDetailFragment();
        fragment.setPost(postData);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPostDetail, fragment).commit();

//        RecyclerView recyclerView = findViewById(R.id.postDetailList);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new PostDetailAdapter(posts_));

    }
}