package com.example.youachieve.network;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.youachieve.PostAdapter;
import com.example.youachieve.data.DataBase;
import com.example.youachieve.data.MyConfig;
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
    PostAdapter postAdapter_;

    public LoadPosts(PostAdapter postAdapter) {
        super();
        postAdapter_ = postAdapter;
    }

    @Override
    protected void onPreExecute() {
        Log.d("YouAchieve", "LoadPosts onPreExecute() called");
    }

    @Override
    protected Void doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(MyConfig.URL_GET_POSTS);
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

                Log.d("YouAchieve", "LoadPosts response " + response);

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
                        user.setImageName(jsonUser.getString("image_name"));
                    }

                    Date date = new Date(year, month, day, hour, minute, second);
                    Post post = new Post(user, text, date, TypePost.valueOf(0));
                    DataBase.postList.add(post);
                }
            }
            else {
                Log.d("YouAchieve", "ERROR: LoadPosts (code " + String.valueOf(code) + ')');
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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onPostExecute(Void oVoid) {
        Log.d("YouAchieve", "LoadPosts onPostExecute() called");
        postAdapter_.updatePosts();
    }
}
