package com.example.youachieve;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youachieve.utils.PostData;

import java.util.ArrayList;

public class PostDetailFragment extends Fragment {
    private ArrayList<PostData> posts_;

    public PostDetailFragment() {
        posts_ = new ArrayList<PostData>();
    }

    public void setPost(PostData postData) {
        posts_.clear();
        posts_.add(postData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.postDetailList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new PostDetailAdapter(posts_));

        return view;
    }
}