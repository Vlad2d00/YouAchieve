package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", foreignKeys = {
    @ForeignKey(entity = Image.class,
            parentColumns = "file_id",
            childColumns = "image_id"),
})
public class User {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "username")
    @NonNull
    public String username;

    @ColumnInfo(name = "first_name")
    @NonNull
    public String firstName;

    @ColumnInfo(name = "last_name")
    @NonNull
    public String lastName;

    @ColumnInfo(name = "description")
    @NonNull
    public String description = "";

    @ColumnInfo(name = "image_id")
    public int imageId = 0;

    public User(int id, @NonNull String username, @NonNull String firstName, @NonNull String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
