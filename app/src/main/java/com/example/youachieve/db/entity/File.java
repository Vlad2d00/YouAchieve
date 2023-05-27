package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "files", foreignKeys = {
//        @ForeignKey(entity = Attachment.class,
//                parentColumns = "id",
//                childColumns = "attachment_id"),
})
public class File {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "url")
    @NonNull
    public String url;

    @ColumnInfo(name = "attachment_id")
    public int attachmentId = 0;

    @ColumnInfo(name = "path")
    public String path = null;

    public File(@NonNull String url) {
        this.url = url;
    }
}
