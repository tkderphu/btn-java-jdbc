package com.example.student.dao;

import com.example.common.JdbcConnection;
import com.example.student.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao{
    private final Connection connection = JdbcConnection.getInstance().getConnection();
    @Override
    public List<Student> getAll() {
        String sql = "select * from SinhVien";
        try {
            List<Student> students = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                students.add(new Student(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getFloat(4)
                ));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(Student student) {
        String sql = "insert into SinhVien (ma_sv, ho_ten, class_name, gpa) values (?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getClassId());
            statement.setFloat(4, student.getGpa());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String ma_sv) {
        String sql = "delete from SinhVien where ma_sv = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ma_sv);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String ma_sv, Student student) {
        String sql = "UPDATE sinhVien SET ma_sv = ?, ho_ten =?, class_name = ?, gpa = ? WHERE ma_sv = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getClassId());
            statement.setFloat(4, student.getGpa());
            statement.setString(5, ma_sv);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
