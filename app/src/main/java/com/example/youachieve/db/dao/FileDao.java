package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.Attachment;
import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.Post;

import java.util.List;

@Dao
public interface FileDao {
    @Query("SELECT * FROM files ")
    List<File> getAll();

    @Query("SELECT * FROM files " +
            "WHERE id = :id " +
            "LIMIT 1")
    File getById(int id);

    @Query("SELECT * FROM files " +
            "ORDER BY id DESC " +
            "LIMIT 1")
    File getLast();

    @Query("SELECT * FROM files " +
            "WHERE attachment_id = :attachmentId ")
    List<File> filterByAttachmentId(int attachmentId);

    @Query("SELECT * FROM files, posts " +
            "WHERE files.attachment_id = posts.attachment_id " +
            "AND files.attachment_id > 0 ")
    List<File> filterByPosts();

    @Query("DELETE FROM files ")
    void deleteAll();

    @Insert
    void insert(File file);

    @Update
    void update(File file);

    @Delete
    void delete(File file);
}