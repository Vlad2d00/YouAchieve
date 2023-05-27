package com.example.youachieve.utils;

import com.example.youachieve.db.entity.File;
import com.example.youachieve.db.entity.Post;
import com.example.youachieve.db.entity.PostComment;
import com.example.youachieve.db.entity.User;

import java.util.ArrayList;

public class PostData {
    public Post post = null;
    public User userOwner = null;
    public File userOwnerImage = null;
    public ArrayList<File> files = new ArrayList<File>();
}
