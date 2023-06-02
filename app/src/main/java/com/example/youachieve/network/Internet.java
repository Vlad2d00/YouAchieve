package com.example.youachieve.network;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.youachieve.utils.MyData;


public class Internet {
    static private boolean state_ = false;

    static public void setState(boolean state) {
        state_ = state;
    }

    static public boolean isInternetAvailable() {
        if (!state_)
            return false;

        ConnectivityManager cm = (ConnectivityManager) MyData.appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
