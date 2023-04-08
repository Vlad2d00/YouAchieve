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


public class LoadImage extends AsyncTask<String, Integer, Void> {
    private final String requestUrl_;
    @SuppressLint("StaticFieldLeak")
    private final ImageView imageView_;
    private Bitmap bitmap_;

    public LoadImage(String requestUrl, ImageView imageView) {
        super();
        this.requestUrl_ = requestUrl;
        this.imageView_ = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        imageView_.setImageResource(R.drawable.loading);
    }

    @Override
    protected Void doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;

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
        return null;
    }

    @Override
    protected void onPostExecute(Void oVoid) {
        imageView_.setImageBitmap(bitmap_);
    }
}
