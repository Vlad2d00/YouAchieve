package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.Attachment;
import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.Post;
import com.example.youachieve.db.entity.User;

import java.util.List;

@Dao
public interface PostDao {
    @Query("SELECT * FROM posts ")
    List<Post> getAll();

    @Query("SELECT * FROM posts " +
            "WHERE id = :postId " +
            "LIMIT 1")
    Post getById(int postId);

    @Query("DELETE FROM posts ")
    void deleteAll();

    @Insert
    void insert(Post post);

    @Update
    void update(Post post);

    @Delete
    void delete(Post post);
}