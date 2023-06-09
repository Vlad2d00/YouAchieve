package com.example.youachieve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youachieve.network.LoadPosts;
import com.example.youachieve.utils.MyData;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView_;
    private PostAdapter postAdapter_;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("YouAchieve", "NewsFragment onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Log.w("YouAchieve", "NewsFragment onCreateView() called");

        recyclerView_ = view.findViewById(R.id.postList);
        recyclerView_.setHasFixedSize(false);
        recyclerView_.setLayoutManager(new LinearLayoutManager(view.getContext()));
        postAdapter_ = new PostAdapter();
        recyclerView_.setAdapter(postAdapter_);

        initScrollListener();

        new LoadPosts(postAdapter_).execute();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("YouAchieve", "NewsFragment onDestroy() called");
    }

    private void initScrollListener() {
        recyclerView_.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
             }
             @Override
             public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);

                 LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                 if (linearLayoutManager != null && !postAdapter_.isLoading()) {
                     // Если долистали до конца списка и это не конец,
                     // то загрузим следующие его элементы
                     int pos = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                     if (pos == MyData.posts.size() - 1 && !MyData.is_end_posts) {
                         new LoadPosts(postAdapter_).execute();
                     }
                 }
             }
        });
    }

}