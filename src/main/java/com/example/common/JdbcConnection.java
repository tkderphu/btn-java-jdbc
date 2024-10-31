package com.example.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
    private static JdbcConnection jdbcConnection;
    private Connection connection;
    private JdbcConnection() {
        String url = "jdbc:mysql://localhost:3306/bai_tap_nhom_java";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JdbcConnection getInstance() {
        if(jdbcConnection == null) {
            jdbcConnection = new JdbcConnection();
        }
        return jdbcConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
