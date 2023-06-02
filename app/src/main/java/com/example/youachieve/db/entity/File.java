package com.example.youachieve.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "files")
public class File {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "url")
    @NonNull
    public String url;

    @ColumnInfo(name = "attachment_id")
    public int attachmentId = 0;

    @ColumnInfo(name = "name")
    public String name;

    public File(@NonNull String url) {
        this.url = url;

        String[] strings = url.split("/");
        int i = strings.length - 1;
        if (strings[i].length() == 0) {
            i -= 1;
        }
        if (i >= 0)
            this.name = strings[i];
        else
            this.name = null;
    }
}
