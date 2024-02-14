package com.sql.jdbc.entity;

public class User {
    private int id;
    private String name;
    private String surname;
    private String city;
    public User() {
    }
    public User(String name, String surname, String city) {
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public User(int id, String name, String surname, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", city=" + city;
    }
}
