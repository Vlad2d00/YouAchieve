package com.example.youachieve.network;
import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.youachieve.PostAdapter;
import com.example.youachieve.db.App;
import com.example.youachieve.db.AppDatabase;
import com.example.youachieve.db.entity.Attachment;
import com.example.youachieve.db.dao.AttachmentDao;
import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.dao.FileDao;
import com.example.youachieve.db.entity.Image;
import com.example.youachieve.db.dao.ImageDao;
import com.example.youachieve.db.dao.ImageMiniatureDao;
import com.example.youachieve.db.entity.Post;
import com.example.youachieve.db.dao.PostDao;
import com.example.youachieve.db.entity.User;
import com.example.youachieve.db.dao.UserDao;
import com.example.youachieve.utils.MyData;
import com.example.youachieve.utils.MyConfig;
import com.example.youachieve.utils.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class LoadPosts extends AsyncTask<String, Integer, Void> {
    PostAdapter postAdapter_;

    public LoadPosts(PostAdapter postAdapter)
    {
        super();
        postAdapter_ = postAdapter;
    }

    @Override
    protected void onPreExecute()
    {
        Log.d("YouAchieve", "LoadPosts onPreExecute() called");
    }

    @Override
    protected Void doInBackground(String... params)
    {
        if (!MyData.is_init_posts) {
            //loadFromDatabase();
            MyData.is_init_posts = true;
        }

        if (!MyData.is_end_posts) {
            loadFromServer();
        }

        return null;
    }

    private void loadFromServer()
    {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(MyConfig.URL_POSTS);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Параметры POST запроса
            String requestBody = "token=" + MyConfig.USER_TOKEN + "&" +
                    "count=" + String.valueOf(MyConfig.POSTS_LOAD_COUNT) + "&" +
                    "offset=" + String.valueOf(MyData.posts_load_count);
            try(OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream())) {
                writer.write(requestBody);
            }

            // Отправление POST запроса
            int code = urlConnection.getResponseCode();
            if (code == 200) {
                // Получили ответ
                StringBuilder result = new StringBuilder();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = bufferedReader.readLine()) != null)
                    result.append(line);
                in.close();
                String response = result.toString();

                Log.d("YouAchieve", "LoadPosts response " + response);

                // Обнулим данные, которые уже были отображены пользователю и заменим на свежие,
                // если это первая подгрузка постов
                if (MyData.posts_load_count == 0) {
                    //deleteAllPosts();
                    MyData.posts.clear();
                }

                int count = parsePosts(response);
                if (count != MyConfig.POSTS_LOAD_COUNT) {
                    // Пользователь подгрузил все посты, то есть постов больше не осталось
                    MyData.is_end_posts = true;
                }
                MyData.posts_load_count += count;
            }
            else {
                Log.d("YouAchieve", "ERROR: LoadPosts (code " + code + ')');
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

    private void deleteAllPosts()
    {
        AppDatabase db = Room.databaseBuilder(MyData.appContext,
                        AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration().build();

        PostDao postDao = db.postDao();
        AttachmentDao attachmentDao = db.attachmentDao();
        FileDao fileDao = db.fileDao();
        ImageDao imageDao = db.imageDao();
        ImageMiniatureDao imageMiniatureDao = db.imageMiniatureDao();

        // Удалим файлы постов с Shared Preferences, а потом из БД
        for (File file : fileDao.filterByPosts()) {
            imageMiniatureDao.delete(imageMiniatureDao.getById(file.id));
            imageDao.delete(imageDao.getById(file.id));

            attachmentDao.delete(attachmentDao.getById(file.attachmentId));

            fileDao.delete(file);
        }

        postDao.deleteAll();
        MyData.posts.clear();
    }

    private int parsePosts(String response) throws JSONException
    {
        AppDatabase db = Room.databaseBuilder(MyData.appContext,
                AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration().build();

        UserDao userDao = db.userDao();
        PostDao postDao = db.postDao();
        FileDao fileDao = db.fileDao();
        ImageDao imageDao = db.imageDao();
        AttachmentDao attachmentDao = db.attachmentDao();

        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArrayPost = jsonObject.getJSONArray("items");
        for (int i = 0; i < jsonArrayPost.length(); i++) {
            // Отдельный пост
            JSONObject jsonPost = jsonArrayPost.getJSONObject(i);
            int postId = jsonPost.getInt("post_id");
            int postTypeId = jsonPost.getInt("post_type");
            String text = jsonPost.getString("text");
            String datetimeCreate = jsonPost.getString("datetime_create").substring(0, 19);
            int likesCount = jsonPost.getInt("likes_count");
            int commentsCount = jsonPost.getInt("comments_count");
            int viewsCount = jsonPost.getInt("views_count");

            // Владелец поста
            JSONObject jsonUserOwner = jsonPost.getJSONObject("user_owner");
            int userId = jsonUserOwner.getInt("user_id");
            String username = jsonUserOwner.getString("username");
            String firstName = jsonUserOwner.getString("first_name");
            String lastName = jsonUserOwner.getString("last_name");

            String userImageUrl = null;
            if (jsonUserOwner.has("image_url")) {
                userImageUrl = jsonUserOwner.getString("image_url");
            }

            // Данные поста для отображения
            PostData postData = new PostData();

            // Создаем пользователя, если его нет
            User userOwner = userDao.getById(userId);
            if (userOwner == null) {
                userOwner = new User(userId, username, firstName, lastName);

                if (userImageUrl != null) {
                    File file = new File(userImageUrl);
                    //fileDao.insert(file);

                    Image image = new Image();
                    image.miniatureId = file.id;
                    //imageDao.insert(image);

                    userOwner.imageId = image.fileId;
                    postData.userOwnerImage = file;
                }
                //userDao.insert(userOwner);
            }
            postData.userOwner = userOwner;

            // Создаем пост
            Post post = new Post(postId, userOwner.id, postTypeId, text, datetimeCreate);
            post.likesCount = likesCount;
            post.commentsCount = commentsCount;
            post.viewsCount = viewsCount;

            // Вложения поста
            if (jsonPost.has("file_url_list")) {
                JSONArray jsonFileUrlList = jsonPost.getJSONArray("file_url_list");
                if (jsonFileUrlList.length() > 0) {
                    // Создаем вложение для файлов поста
                    Attachment attachment = new Attachment();
                    //attachmentDao.insert(attachment);
                    post.attachmentId = attachment.id;

                    // Создаем файлы поста
                    for (int j = 0; j < jsonFileUrlList.length(); j++) {
                        String fileUrl = jsonFileUrlList.getString(j);

                        File file = new File(fileUrl);
                        file.attachmentId = attachment.id;
                        //fileDao.insert(file);

                        postData.files.add(file);
                    }
                }
            }

            //postDao.insert(post);
            postData.post = post;
            MyData.posts.add(postData);
        }
        return jsonArrayPost.length();
    }

    private void loadFromDatabase()
    {
        // БД
        AppDatabase db = Room.databaseBuilder(MyData.appContext,
                        AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration().build();

        PostDao postDao = db.postDao();
        UserDao userDao = db.userDao();
        FileDao fileDao = db.fileDao();

        // Загрузка из БД
        List<Post> posts = postDao.getAll();
        for (Post post : posts) {
            PostData postData = new PostData();

            postData.post = post;
            postData.userOwner = userDao.getById(post.userOwnerId);
            postData.userOwnerImage = fileDao.getById(postData.userOwner.imageId);
            postData.files = (ArrayList<File>) fileDao.filterByAttachmentId(post.attachmentId);

            MyData.posts.add(postData);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onPostExecute(Void oVoid)
    {
        Log.d("YouAchieve", "LoadPosts onPostExecute() called");
        postAdapter_.updatePosts();
    }
}
