package com.example.youachieve.network;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.youachieve.data.DataBase;
import com.example.youachieve.data.Post;
import com.example.youachieve.data.TypePost;
import com.example.youachieve.data.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


public class LoadPosts extends AsyncTask<String, Integer, Void> {
    private final String requestUrl_ = "https://youachieve.eu.pythonanywhere.com/posts/";
    @SuppressLint("StaticFieldLeak")
    private final TextView textView_;

    public LoadPosts(TextView textView) {
        super();
        this.textView_ = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("YouAchieve", "AsyncLoadData onPreExecute() called");
        textView_.setText("Загрузка...");
    }

    @Override
    protected Void doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(requestUrl_);
            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setDoOutput(true);
//            urlConnection.setDoInput(true);
//            urlConnection.setRequestMethod("POST");

            int code = urlConnection.getResponseCode();
            if (code == 200) {
                StringBuilder result = new StringBuilder();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = bufferedReader.readLine()) != null)
                    result.append(line);
                in.close();
                String response = result.toString();

                Log.d("YouAchieve", "================================");
                Log.d("YouAchieve", response);
                Log.d("YouAchieve", "================================");

                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArrayPost = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArrayPost.length(); i++) {
                    JSONObject jsonPost = jsonArrayPost.getJSONObject(i);
                    int year = jsonPost.getInt("year");
                    int month = jsonPost.getInt("month");
                    int day = jsonPost.getInt("day");
                    int hour = jsonPost.getInt("hour");
                    int minute = jsonPost.getInt("minute");
                    int second =jsonPost.getInt("second");
                    String text = jsonPost.getString("text");

                    JSONObject jsonUser = jsonPost.getJSONObject("user");
                    String first_name = jsonUser.getString("first_name");
                    String last_name = jsonUser.getString("last_name");

                    User user = new User(first_name, last_name);
                    if (jsonUser.has("image_name")) {
                        String image_name = jsonUser.getString("image_name");
                        user.setImageName(image_name);
                        Log.d("YouAchieve", "image_name = " + image_name);
                    }
                    else
                        Log.d("YouAchieve", "image_name = none");

                    Date date = new Date(year, month, day, hour, minute, second);
                    Post post = new Post(user, text, date, TypePost.valueOf(0));
                    DataBase.postList.add(post);
                }
            }
            else {
                Log.d("YouAchieve", "================================");
                Log.d("YouAchieve", "ERROR: code " + String.valueOf(code));
                Log.d("YouAchieve", "================================");
            }
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
        Log.d("YouAchieve", "AsyncLoadData onPostExecute() called");
        textView_.setText("Готово!");
    }
}
