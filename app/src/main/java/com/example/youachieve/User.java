package com.example.youachieve;

public class User {
    private String name;
    private String surname;
    private int idResAvatar;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.idResAvatar = 0;
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

