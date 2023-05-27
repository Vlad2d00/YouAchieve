package com.example.youachieve.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.youachieve.db.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users ")
    List<User> getAll();

    @Query("SELECT * FROM users " +
            "WHERE id = :userId " +
            "LIMIT 1")
    User getById(int userId);

    @Query("SELECT * FROM users " +
            "WHERE username = :username " +
            "LIMIT 1")
    User getByUsername(String username);

    @Query("DELETE FROM users ")
    void deleteAll();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}