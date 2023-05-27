package com.example.youachieve;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youachieve.db.entity.User;

import java.util.ArrayList;


public class UserDetailFragment extends Fragment {
    private final ArrayList<User> users_;

    public UserDetailFragment() {
        users_ = new ArrayList<User>();
    }

    public void setUser(User user) {
        users_.clear();
        users_.add(user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.userList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new UserDetailAdapter(users_));

        return view;
    }

}