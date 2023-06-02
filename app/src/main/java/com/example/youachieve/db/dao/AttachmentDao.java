package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.Attachment;
import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.ImageMiniature;
import com.example.youachieve.db.entity.User;

import java.util.List;

@Dao
public interface AttachmentDao {
    @Query("SELECT * FROM attachments ")
    List<Attachment> getAll();

    @Query("SELECT * FROM attachments " +
            "WHERE id = :attachmentId " +
            "LIMIT 1")
    Attachment getById(int attachmentId);

    @Query("SELECT * FROM attachments " +
            "ORDER BY id DESC " +
            "LIMIT 1")
    Attachment getLast();

    @Query("DELETE FROM attachments ")
    void deleteAll();

    @Insert
    void insert(Attachment attachment);

    @Update
    void update(Attachment attachment);

    @Delete
    void delete(Attachment attachment);
}