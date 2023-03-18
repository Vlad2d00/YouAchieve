package com.example.youachieve;

import java.util.ArrayList;
import java.util.Date;

public class Post extends Message {
    private TypePost type;
    private int likeCount;
    private ArrayList<Comment> commentList;
    private int viewCount;

    public TypePost getType() {
        return type;
    }

    public void setType(TypePost type) {
        this.type = type;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    public Post(User user, String text, Date date, TypePost type) {
        super(user, text, date);
        this.type = type;

        this.likeCount = 0;
        this.commentList = new ArrayList<Comment>(0);
        this.viewCount = 0;
    }
}
