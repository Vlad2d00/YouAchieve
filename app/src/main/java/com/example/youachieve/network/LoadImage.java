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


public class LoadImage extends AsyncTask<String, Integer, Bitmap> {
    private final String requestUrl_;
    @SuppressLint("StaticFieldLeak")
    private final ImageView imageView_;

    public LoadImage(String requestUrl, ImageView imageView) {
        super();
        this.requestUrl_ = requestUrl;
        this.imageView_ = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Выставляем картинку загрузки
        imageView_.setImageResource(R.drawable.loading);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        Bitmap bitmap = null;

        try {
            url = new URL(requestUrl_);
            urlConnection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView_.setImageBitmap(bitmap);
    }
}
