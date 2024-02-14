package com.sql.jdbc.dao;

import com.sql.jdbc.entity.UserDetail;

import java.util.List;

public interface UserDetailsDAO {
    void add(UserDetail userDetail);
    List<UserDetail> getAll();
    UserDetail getById(int id);
    void update(UserDetail userDetail,int id);
    void delete(int id);
}
