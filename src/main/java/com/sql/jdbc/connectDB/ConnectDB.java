package com.sql.jdbc.connectDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public void closeConnection(){
        try {
            if(connection!=null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
