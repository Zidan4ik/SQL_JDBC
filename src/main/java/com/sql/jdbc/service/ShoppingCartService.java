package com.sql.jdbc.service;

import com.sql.jdbc.connectDB.ConnectDB;
import com.sql.jdbc.dao.ShoppingCartDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShoppingCartService extends ConnectDB implements ShoppingCartDAO {
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    public ShoppingCartService() {
        super();
    }

    @Override
    public void addProduct(int userId, int productId) {
        PreparedStatement preparedStatement = null;

        String query = "INSERT INTO shopping_cart(id_user,id_product) " +
                "VALUES(?,?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteByProduct(int userId, int productId) {
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM shopping_cart WHERE id_user=? AND id_product=? LIMIT 1";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Integer> getAllProductsByUser(int userId) {
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM shopping_cart WHERE id_user=?";
        ArrayList<Integer> idProducts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(productService.getById(resultSet.getInt("id_product")));
                idProducts.add(resultSet.getInt("id_product"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return idProducts;
    }

    @Override
    public void deleteAllProductsByUser(int userId) {
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM shopping_cart WHERE id_user=?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
