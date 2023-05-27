package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.Authentication;
import com.example.youachieve.db.entity.PostComment;
import com.example.youachieve.db.entity.User;

@Dao
public interface AuthenticationDao {
    @Query("SELECT user_id FROM authentications " +
            "LIMIT 1")
    int getUserId();

    @Query("SELECT token FROM authentications " +
            "LIMIT 1")
    String getToken();

    @Query("DELETE FROM authentications ")
    void deleteAll();

    @Insert
    void insert(Authentication authentication);

    @Update
    void update(Authentication authentication);

    @Delete
    void delete(Authentication authentication);
}