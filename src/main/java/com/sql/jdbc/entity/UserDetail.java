package com.sql.jdbc.entity;

public class UserDetail {
    private int id;
    private int age;
    private String phone;
    private String job;

    public UserDetail() {
    }

    public UserDetail(int id, int age, String phone, String job) {
        this.id = id;
        this.age = age;
        this.phone = phone;
        this.job = job;
    }

    public UserDetail(int age, String phone, String job) {
        this.age = age;
        this.phone = phone;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", age=" + age +
                ", phone=" + phone +
                ", job=" + job;
    }
}
