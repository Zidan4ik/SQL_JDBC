package com.sql.jdbc.service;

import com.sql.jdbc.connectDB.ConnectDB;
import com.sql.jdbc.dao.ShoppingCartDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShoppingCartService extends ConnectDB implements ShoppingCartDAO {
    private ProductService productService = new ProductService();

    public  ShoppingCartService() {
        super();
    }

    @Override
    public void addProduct(int userId, int productId) {
        logMessage("Продукт id:"+productId+" був добавленний в кошик користувача id:"+userId);
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO shopping_cart(id_user,id_product) " +
                "VALUES(?,?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logMessage(e.getMessage());
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                logMessage(e.getMessage());
            }
        }
    }

    @Override
    public void deleteByProduct(int userId, int productId) {
        logMessage("Видалено продукт під id:"+productId+" в корзині користувача id:"+userId);
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM shopping_cart WHERE id_user=? AND id_product=? LIMIT 1";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logMessage(e.getMessage());
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                logMessage(e.getMessage());
            }
        }
    }

    public ArrayList<Integer> getAllProductsByUser(int userId) {
        logMessage("Отримано всі продукти з кошику користувача id:"+userId);
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM shopping_cart WHERE id_user=?";
        ArrayList<Integer> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(productService.getById(resultSet.getInt("id_product")));
                products.add(resultSet.getInt("id_product"));
            }
        } catch (SQLException e) {
            logMessage(e.getMessage());
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                logMessage(e.getMessage());
            }
        }
        showList(products);
        return products;
    }

    @Override
    public void deleteAllProductsByUser(int userId) {
        logMessage("Видалено всіх продуктів з корзини користувача id:"+userId);
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM shopping_cart WHERE id_user=?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logMessage(e.getMessage());
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                logMessage(e.getMessage());
            }
        }
    }
}
