package com.example.youachieve;

import java.util.ArrayList;
import java.util.Date;

public class Message {
    private User user;
    private String text;
    private Date date;
    private ArrayList<Integer> idResImageList;

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

    public ArrayList<Integer> getIdResImageList() {
        return idResImageList;
    }

    public void setIdResImageList(ArrayList<Integer> idResImageList) {
        this.idResImageList = idResImageList;
    }

    public Message(User user, String text, Date date) {
        this.user = user;
        this.text = text;
        this.date = date;
        this.idResImageList = new ArrayList<Integer>(0);
    }

    public Message(User user, String text, Date date, ArrayList<Integer> idResImageList) {
        this.user = user;
        this.text = text;
        this.date = date;
        this.idResImageList = idResImageList;
    }
}
