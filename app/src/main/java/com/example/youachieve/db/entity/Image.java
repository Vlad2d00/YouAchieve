package com.example.youachieve.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "images", foreignKeys = {
        @ForeignKey(entity = File.class,
                parentColumns = "id",
                childColumns = "file_id"),
        @ForeignKey(entity = ImageMiniature.class,
                parentColumns = "file_id",
                childColumns = "miniature_id"),
})
public class Image {
    @PrimaryKey
    @ColumnInfo(name = "file_id")
    public int fileId = 0;

    @ColumnInfo(name = "miniature_id")
    public int miniatureId = 0;

    public Image() {

    }
}
