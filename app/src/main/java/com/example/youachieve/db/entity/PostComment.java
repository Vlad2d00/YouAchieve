package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "post_comments", foreignKeys = {
        @ForeignKey(entity = Post.class,
                parentColumns = "id",
                childColumns = "post_id"),
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_owner_id"),
})
public class PostComment {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "text")
    @NonNull
    public String text;

    @ColumnInfo(name = "datetime_create")
    @NonNull
    public String datetimeCreate;

    @ColumnInfo(name = "post_id")
    public int postId;

    @ColumnInfo(name = "user_owner_id")
    public int userOwnerId;

    public PostComment(int id, int postId, int userOwnerId, @NonNull String text, @NonNull String datetimeCreate) {
        this.id = id;
        this.postId = postId;
        this.userOwnerId = userOwnerId;
        this.text = text;
        this.datetimeCreate = datetimeCreate;
    }
}
