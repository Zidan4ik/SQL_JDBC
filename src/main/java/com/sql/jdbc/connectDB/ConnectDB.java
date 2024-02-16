package com.sql.jdbc.connectDB;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectDB {
    protected static Connection connection;

    public ConnectDB() {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/task";
            String username = "roma";
            String password = "root";
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logMessage(String message) {
        Logger logger = LogManager.getLogger(this.getClass());
        logger.info(message);
    }

    public void showList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
