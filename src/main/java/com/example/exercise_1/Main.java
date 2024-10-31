package com.example.exercise_1;

import com.example.common.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        Connection connection = JdbcConnection.getInstance().getConnection();;
        String query = "create table SinhVien(\n" +
                "ma_sv varchar(50) primary key,\n" +
                "ho_ten varchar(50),\n" +
                "class_name varchar(50),\n" +
                "gpa float)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
