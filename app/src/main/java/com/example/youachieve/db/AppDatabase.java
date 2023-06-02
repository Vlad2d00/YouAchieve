package com.example.youachieve.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.youachieve.db.dao.AttachmentDao;
import com.example.youachieve.db.dao.AuthenticationDao;
import com.example.youachieve.db.dao.FileDao;
import com.example.youachieve.db.dao.ImageDao;
import com.example.youachieve.db.dao.ImageMiniatureDao;
import com.example.youachieve.db.dao.PostCommentDao;
import com.example.youachieve.db.dao.PostDao;
import com.example.youachieve.db.dao.UserDao;
import com.example.youachieve.db.entity.Attachment;
import com.example.youachieve.db.entity.Authentication;
import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.Image;
import com.example.youachieve.db.entity.ImageMiniature;
import com.example.youachieve.db.entity.Post;
import com.example.youachieve.db.entity.PostComment;
import com.example.youachieve.db.entity.User;

@Database(entities = {
        Attachment.class,
        Authentication.class,
        File.class,
        Image.class,
        ImageMiniature.class,
        PostComment.class,
        Post.class,
        User.class,
}, version = 15)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AttachmentDao attachmentDao();
    public abstract AuthenticationDao authenticationDao();
    public abstract FileDao fileDao();
    public abstract ImageDao imageDao();
    public abstract ImageMiniatureDao imageMiniatureDao();
    public abstract PostCommentDao postCommentDao();
    public abstract PostDao postDao();
    public abstract UserDao userDao();
}