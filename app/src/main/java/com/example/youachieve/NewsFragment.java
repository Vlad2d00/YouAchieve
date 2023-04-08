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

public class NewsFragment extends Fragment {
    RecyclerView recyclerView;
    PostAdapter postsAdapter;
    boolean isLoading = false;


    public NewsFragment() {}

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

        recyclerView = view.findViewById(R.id.postList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        postsAdapter = new PostAdapter(DataBase.postList, getParentFragmentManager().beginTransaction());
        recyclerView.setAdapter(postsAdapter);

        initScrollListener();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("YouAchieve", "NewsFragment onDestroy() called");
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
             }
             @Override
             public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);

                 LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                 if (!isLoading) {
                     if (linearLayoutManager != null && linearLayoutManager.
                             findLastCompletelyVisibleItemPosition() == DataBase.postList.size() - 1)
                     {
                         // Пока не будем генерировать новые посты
                         // loadMorePosts();
                         isLoading = true;
                     }
                 }
             }
        });
    }

    private void loadMorePosts() {
        DataBase.postList.add(DataBase.generatePost());
        postsAdapter.notifyItemInserted(DataBase.postList.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                DataBase.postList.remove(DataBase.postList.size() - 1);
                int scrollPosition = DataBase.postList.size();
                postsAdapter.notifyItemRemoved(scrollPosition);

                DataBase.loadPosts(MyConfig.COUNT_LOAD_POSTS);
                postsAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 500);
    }
}