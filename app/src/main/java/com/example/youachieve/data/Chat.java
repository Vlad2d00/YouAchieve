package com.example.youachieve.data;

import com.example.youachieve.R;

import java.util.ArrayList;

public class Chat {
    private String name;
    private int idResAvatar;
    private ArrayList<Integer> userList;

    public Chat(ArrayList<Integer> userList) {
        this.name = "";
        this.idResAvatar = 0;
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdResAvatar() {
        return idResAvatar;
    }

    public void setIdResAvatar(int idResAvatar) {
        this.idResAvatar = idResAvatar;
    }

    public ArrayList<Integer> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<Integer> userList) {
        this.userList = userList;
    }
}
