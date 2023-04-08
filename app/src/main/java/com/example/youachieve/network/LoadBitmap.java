package com.example.youachieve.network;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.youachieve.R;

import java.net.HttpURLConnection;
import java.net.URL;


public class LoadBitmap extends AsyncTask<String, Integer, Void> {
    private final String requestUrl_;
    private Bitmap bitmap_;

    public LoadBitmap(String requestUrl, Bitmap bitmap) {
        super();
        this.requestUrl_ = requestUrl;
        this.bitmap_ = bitmap;
    }

    @Override
    protected Void doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;

        Log.d("YouAchieve", "Loading Bitmap...");
        try {
            url = new URL(requestUrl_);
            urlConnection = (HttpURLConnection) url.openConnection();
            bitmap_ = BitmapFactory.decodeStream(urlConnection.getInputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        Log.d("YouAchieve", "Bitmap loaded!");
        return null;
    }

}
