package com.example.youachieve.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.youachieve.R;
import com.example.youachieve.db.FileStorage;
import com.example.youachieve.utils.MyData;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoadImage extends AsyncTask<String, Integer, Bitmap> {
    private static final int dataSize_ = 10 * 1024 * 1024;

    private final String requestUrl_;
    private final String fileName_;
    @SuppressLint("StaticFieldLeak")
    private final ImageView imageView_;
    @SuppressLint("StaticFieldLeak")
    private final Context context_;

    public LoadImage(String requestUrl, String fileName, ImageView imageView, Context context) {
        super();
        requestUrl_ = requestUrl;
        imageView_ = imageView;
        fileName_ = fileName;
        context_ = context;
    }

    @Override
    protected void onPreExecute() {
        Log.d("YouAchieve", "LoadImage onPostExecute() called");
        super.onPreExecute();
        // Выставляем картинку загрузки
        imageView_.setImageResource(R.drawable.loading);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        Bitmap bitmap = null;

        byte[] data = new byte[dataSize_];
        int count = FileStorage.readFile(fileName_, context_, data);

        if (count >= 0) {
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        else {
            if (!Internet.isInternetAvailable()) {
                return null;
            }

            try {
                url = new URL(requestUrl_);
                urlConnection = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());

                if (bitmap!= null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    data = stream.toByteArray();
                    FileStorage.writeFile(fileName_, context_, data);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d("YouAchieve", "LoadImage onPostExecute() called");
        if (bitmap != null) {
            imageView_.setImageBitmap(bitmap);
        }
        else {
            imageView_.setImageResource(R.drawable.download_error);
        }
    }
}
