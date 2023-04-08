package com.example.youachieve.data;

import android.graphics.Bitmap;

public class User {
    private String name;
    private String surname;
    private int idResAvatar;
    private String imageName;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.idResAvatar = 0;
        this.imageName = "";
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public User(String name, String surname, int idResAvatar) {
        this.name = name;
        this.surname = surname;
        this.idResAvatar = idResAvatar;
    }

    public int getIdResAvatar() {
        return idResAvatar;
    }

    public void setIdResAvatar(int idResAvatar) {
        this.idResAvatar = idResAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

