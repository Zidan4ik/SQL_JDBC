package com.sql.jdbc.service;

import com.sql.jdbc.connectDB.ConnectDB;
import com.sql.jdbc.dao.UserDetailsDAO;
import com.sql.jdbc.entity.UserDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsService extends ConnectDB implements UserDetailsDAO {

    public UserDetailsService() {
        super();
    }

    @Override
    public void add(UserDetail userDetail) {
        logMessage("Детальна інформація користувача id:"+userDetail.getId()+" було добавлено в база даних");
        PreparedStatement preparedStatement = null;

        String queryUserDetails = "INSERT INTO user_details(id,age,phone,job) VALUES(?,?,?,?)";
        String queryUser = "UPDATE users SET user_details_id=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(queryUserDetails);

            preparedStatement.setInt(1, userDetail.getId());
            preparedStatement.setInt(2, userDetail.getAge());
            preparedStatement.setString(3, userDetail.getPhone());
            preparedStatement.setString(4, userDetail.getJob());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(queryUser);
            preparedStatement.setInt(1, userDetail.getId());
            preparedStatement.setInt(2, userDetail.getId());
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
    public List<UserDetail> getAll() {
        logMessage("Отримано детальну інформацію всіх користувачів з бази даних");
        Statement statement = null;
        String query = "SELECT * FROM user_details";
        List<UserDetail> usersDetails = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                UserDetail userDetail = new UserDetail();
                userDetail.setId(resultSet.getInt("id"));
                userDetail.setAge(resultSet.getInt("age"));
                userDetail.setPhone(resultSet.getString("phone"));
                userDetail.setJob(resultSet.getString("job"));

                usersDetails.add(userDetail);
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
        showList(usersDetails);
        return usersDetails;
    }

    @Override
    public UserDetail getById(int id) {
        logMessage("Отримано інформацію про користувача з бази даних id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM user_details WHERE id=?";
        UserDetail userDetail = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDetail = new UserDetail();
                userDetail.setId(resultSet.getInt("id"));
                userDetail.setAge(resultSet.getInt("age"));
                userDetail.setPhone(resultSet.getString("phone"));
                userDetail.setJob(resultSet.getString("job"));
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
        return userDetail;
    }

    @Override
    public void update(UserDetail userDetail, int id) {
        logMessage("Оновлено дані про детальну інформацію користувача в базі даних id:"+id);
        PreparedStatement preparedStatement = null;

        String query = "UPDATE user_details SET age=?,phone=?,job=? WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userDetail.getAge());
            preparedStatement.setString(2, userDetail.getPhone());
            preparedStatement.setString(3, userDetail.getJob());
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
        logMessage("Видалено детальну інформацію про користувача з бази даних id:"+id);
        PreparedStatement preparedStatement = null;

        String queryDeleteValues = "DELETE FROM user_details WHERE id=?";
        String queryDeleteKey = "UPDATE users " +
                "SET users.user_details_id = null " +
                "WHERE users.id = (SELECT id FROM (SELECT id from users WHERE users.user_details_id=?)AS t1)";

        try {
            preparedStatement = connection.prepareStatement(queryDeleteKey);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(queryDeleteValues);

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
