package com.example.student.dao;

import com.example.student.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAll();
    boolean create(Student student);
    void delete(String ma_sv);
    void update(String ma_sv, Student student);
}
