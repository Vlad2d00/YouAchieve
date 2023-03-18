package com.example.youachieve;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessengerFragment extends Fragment {
    public MessengerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("YouAchieve", "MessengerFragment onCreateView() called");
        return inflater.inflate(R.layout.fragment_messenger, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.w("YouAchieve", "MessengerFragment onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("YouAchieve", "MessengerFragment onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.w("YouAchieve", "MessengerFragment onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.w("YouAchieve", "MessengerFragment onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("YouAchieve", "MessengerFragment onDestroy() called");
    }


}