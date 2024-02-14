package com.sql.jdbc.dao;

import java.util.List;

public interface ShoppingCartDAO {
    void addProduct(int userId, int productId);
    void deleteByProduct(int userId,int productId);
    List<Integer> getAllProductsByUser(int userId);
    void deleteAllProductsByUser(int userId);

}
