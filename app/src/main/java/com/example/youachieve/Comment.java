package com.example.youachieve;

import java.util.Date;

public class Comment {
    private User user;
    private String text;
    private Date date;
    private int likeCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Comment(User user, String text, Date date) {
        this.user = user;
        this.text = text;
        this.date = date;

        this.likeCount = 0;
    }
}
