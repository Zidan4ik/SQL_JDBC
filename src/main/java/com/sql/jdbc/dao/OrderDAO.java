package com.sql.jdbc.dao;

import com.sql.jdbc.entity.Order;

import java.util.List;

public interface OrderDAO {
    void add(int userId);
    List<Order> getAllById(int userId);
    List<Order> getAll();
}
