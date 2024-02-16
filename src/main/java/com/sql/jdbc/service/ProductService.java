package com.sql.jdbc.service;

import com.sql.jdbc.connectDB.ConnectDB;
import com.sql.jdbc.dao.ProductDAO;
import com.sql.jdbc.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends ConnectDB implements ProductDAO {

    public ProductService(){
        super();
    }
    @Override
    public void add(Product product) {
        logMessage("Продукт з id:"+product.getId()+" було добавлено в список купного");
        PreparedStatement preparedStatement = null;
        String queryUser = "INSERT INTO products (id,name,price)" +
                "VALUES(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(queryUser);
            preparedStatement.setInt(1,product.getId());
            preparedStatement.setString(2,product.getName());
            preparedStatement.setInt(3,product.getPrice());

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
    public List<Product> getAll() {
        logMessage("Отримано список продуктів з база даних");
        Statement statement = null;
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));

                products.add(product);
            }
        } catch (SQLException e) {
            logMessage(e.getMessage());
        } finally {
            try {
                if (!statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                logMessage(e.getMessage());
            }
        }
        showList(products);
        return products;
    }

    @Override
    public Product getById(int id) {
        logMessage("Отримано продукт по id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM products WHERE id=?";
        Product product = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
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
        return product;
    }

    @Override
    public void update(Product product, int id) {
        logMessage("Змінено дані про продукт під id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "UPDATE products SET name=?, price=? WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setInt(3,id);

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
    public void delete(int id) {
        logMessage("Видалено продукт під id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM products WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
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
