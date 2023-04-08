package com.example.youachieve;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youachieve.network.LoadImage;

public class MessengerFragment extends Fragment {
    public MessengerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("YouAchieve", "MessengerFragment onCreateView() called");
        View view = inflater.inflate(R.layout.fragment_messenger, container, false);

        new LoadImage(
                "https://mobimg.b-cdn.net/v3/fetch/b6/b67444cbf58152bc6e666615cb08f0f5.jpeg",
                view.findViewById(R.id.messengerImage)
        ).execute();

        return view;
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