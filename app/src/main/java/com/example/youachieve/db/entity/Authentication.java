package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "authentications", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),
})
public class Authentication {
    @PrimaryKey
    @ColumnInfo(name = "token")
    @NonNull
    public String token;

    @ColumnInfo(name = "user_id")
    public int userId;

    public Authentication(@NonNull String token, int userId) {
        this.token = token;
        this.userId = userId;
    }
}
