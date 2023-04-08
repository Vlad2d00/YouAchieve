package com.example.youachieve;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youachieve.data.Post;

import java.util.ArrayList;

public class PostDetailFragment extends Fragment {
    private final ArrayList<Post> postList = new ArrayList<Post>();

    public PostDetailFragment(Post post) {
        this.postList.add(post);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.postDetailList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new PostDetailAdapter(this.postList));

        return view;
    }
}