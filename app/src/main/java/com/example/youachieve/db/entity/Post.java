package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_owner_id"),
//        @ForeignKey(entity = Attachment.class,
//                parentColumns = "id",
//                childColumns = "attachment_id"),
})
public class Post {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "type_post_id")
    public int typePostId;

    @ColumnInfo(name = "text")
    @NonNull
    public String text;

    @ColumnInfo(name = "datetime_create")
    @NonNull
    public String datetimeCreate;

    @ColumnInfo(name = "views_count")
    public int viewsCount = 0;

    @ColumnInfo(name = "comments_count")
    public int commentsCount = 0;

    @ColumnInfo(name = "likes_count")
    public int likesCount = 0;

    @ColumnInfo(name = "user_owner_id")
    public int userOwnerId;

    @ColumnInfo(name = "attachment_id")
    public int attachmentId = 0;

    public Post(int id, int userOwnerId, int typePostId, @NonNull String text, @NonNull String datetimeCreate) {
        this.id = id;
        this.userOwnerId = userOwnerId;
        this.typePostId = typePostId;
        this.text = text;
        this.datetimeCreate = datetimeCreate;
    }
}
