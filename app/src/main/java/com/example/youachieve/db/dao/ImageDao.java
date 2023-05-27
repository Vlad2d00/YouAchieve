package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.Image;

import java.util.List;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM images ")
    List<Image> getAll();

    @Query("SELECT * FROM images " +
            "WHERE file_id = :file_id " +
            "LIMIT 1")
    Image getById(int file_id);

    @Query("DELETE FROM images ")
    void deleteAll();

    @Insert
    void insert(Image image);

    @Update
    void update(Image image);

    @Delete
    void delete(Image image);
}