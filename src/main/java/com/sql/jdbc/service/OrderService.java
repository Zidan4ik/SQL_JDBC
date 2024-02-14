package com.sql.jdbc.service;

import com.sql.jdbc.connectDB.ConnectDB;
import com.sql.jdbc.dao.OrderDAO;
import com.sql.jdbc.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends ConnectDB implements OrderDAO {
    ShoppingCartService shoppingCartService = new ShoppingCartService();

    public OrderService() {
        super();
    }

    @Override
    public void add(int userId) {
        PreparedStatement preparedStatement = null;

        String querySaveOrders = "INSERT INTO orders(user_id,list,total_summa) " +
                "SELECT sc.id_user AS user_id,CONCAT(GROUP_CONCAT(p.name separator ',')) AS list, SUM(p.price) AS total_summa " +
                "FROM shopping_cart AS sc " +
                "JOIN products AS p " +
                "ON sc.id_product=p.id " +
                "WHERE sc.id_user=? " +
                "GROUP BY sc.id_user";

        try {
            preparedStatement = connection.prepareStatement(querySaveOrders);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

            shoppingCartService.deleteAllProductsByUser(userId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Order> getAllById(int userId) {
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM orders WHERE user_id=?";
        List<Order> orders = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setList(resultSet.getString(2));
                order.setTotalSumma(resultSet.getInt(3));

                orders.add(order);
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
        for (Order order: orders){
            System.out.println(order);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() {
        Statement statement = null;

        String query = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setList(resultSet.getString(2));
                order.setTotalSumma(resultSet.getInt(3));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Order order: orders){
            System.out.println(order);
        }
        return orders;
    }
}
