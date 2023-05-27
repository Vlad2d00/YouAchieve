package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "images_miniature", foreignKeys = {
        @ForeignKey(entity = File.class,
                parentColumns = "id",
                childColumns = "file_id"),
})
public class ImageMiniature {
    @PrimaryKey
    @ColumnInfo(name = "file_id")
    public int fileId;

    public ImageMiniature(int fileId) {
        this.fileId = fileId;
    }
}
