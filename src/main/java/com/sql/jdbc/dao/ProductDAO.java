package com.sql.jdbc.dao;

import com.sql.jdbc.entity.Product;

import java.util.List;

public interface ProductDAO {
    void add(Product product);
    List<Product> getAll();
    Product getById(int id);
    void update(Product product,int id);
    void delete(int id);
}
