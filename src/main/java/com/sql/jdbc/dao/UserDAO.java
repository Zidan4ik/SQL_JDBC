package com.sql.jdbc.dao;

import com.sql.jdbc.entity.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    List<User> getAll();
    User getById(int id);
    void update(User user,int id);
    void delete(int id);
}
