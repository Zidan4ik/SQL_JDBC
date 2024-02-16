package com.sql.jdbc.service;

import com.sql.jdbc.connectDB.ConnectDB;
import com.sql.jdbc.dao.UserDAO;
import com.sql.jdbc.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService extends ConnectDB implements UserDAO {
    public UserService(){
        super();
    }
    @Override
    public void add(User user) {
        logMessage("Користувач з id:"+user.getId()+" був добавлений в базу даних");
        PreparedStatement preparedStatement = null;
        String queryUser = "INSERT INTO users (id,name,surname,city)" +
                "VALUES(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(queryUser);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getCity());

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
    public List<User> getAll() {
        logMessage("Отримано всіх користувачів з бази даних");
        Statement statement = null;
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setCity(resultSet.getString("city"));

                users.add(user);
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
        showList(users);
        return users;
    }

    @Override
    public User getById(int id) {
        logMessage("Отримано користувача з бази даних id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM users WHERE id=?";
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setCity(resultSet.getString("city"));
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
        return user;
    }

    @Override
    public void update(User user, int id) {
        logMessage("Оновлено дані про користувача в базі даних id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "UPDATE users SET name=?, surname=?, city=? WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setInt(4, id);

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
        logMessage("Видалено користувача з бази даних id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "DELETE FROM users WHERE id=?";

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
