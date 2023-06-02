package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.Image;
import com.example.youachieve.db.entity.ImageMiniature;

import java.util.List;

@Dao
public interface ImageMiniatureDao {
    @Query("SELECT * FROM images_miniature ")
    List<ImageMiniature> getAll();

    @Query("SELECT * FROM images_miniature " +
            "WHERE file_id = :file_id " +
            "LIMIT 1")
    ImageMiniature getById(int file_id);

    @Query("SELECT * FROM images_miniature " +
            "ORDER BY file_id DESC " +
            "LIMIT 1")
    ImageMiniature getLast();

    @Query("DELETE FROM images_miniature ")
    void deleteAll();

    @Insert
    void insert(ImageMiniature imageMiniature);

    @Update
    void update(ImageMiniature imageMiniature);

    @Delete
    void delete(ImageMiniature imageMiniature);
}
