package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.Post;
import com.example.youachieve.db.entity.PostComment;

import java.util.List;

@Dao
public interface PostCommentDao {
    @Query("SELECT * FROM post_comments ")
    List<PostComment> getAll();

    @Query("SELECT * FROM post_comments " +
            "WHERE post_id = :postId ")
    List<PostComment> filterByPostId(int postId);

    @Query("DELETE FROM post_comments ")
    void deleteAll();

    @Insert
    void insert(PostComment postComment);

    @Update
    void update(PostComment postComment);

    @Delete
    void delete(PostComment postComment);
}