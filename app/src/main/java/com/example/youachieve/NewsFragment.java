package com.example.youachieve;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youachieve.data.DataBase;
import com.example.youachieve.data.MyConfig;
import com.example.youachieve.network.LoadPosts;

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
        postAdapter_ = new PostAdapter(DataBase.postList, getParentFragmentManager().beginTransaction());
        recyclerView_.setAdapter(postAdapter_);

        initScrollListener();

        DataBase.postList.clear();
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
                 if (!postAdapter_.isLoading()) {
                     // Если долистали до конца списка, то загрузим следующие его элементы
                     if (linearLayoutManager != null && linearLayoutManager.
                             findLastCompletelyVisibleItemPosition() == DataBase.postList.size() - 1)
                     {
                         // Загрузим новые посты
                         new LoadPosts(postAdapter_).execute();
                     }
                 }
             }
        });
    }

}