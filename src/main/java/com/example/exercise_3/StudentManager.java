package com.example.exercise_3;

import com.example.common.JdbcConnection;
import com.example.exercise_2.Frame;
import com.example.student.dao.StudentDao;
import com.example.student.dao.StudentDaoImpl;
import com.example.student.model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

public class StudentManager {
    private Connection connection;
    private StudentDao studentDao;
    private Frame frame;
    private DefaultTableModel tableModel;

    public StudentManager() {
        connection = JdbcConnection.getInstance().getConnection();
        studentDao = new StudentDaoImpl();
        frame = new Frame();

        tableModel = frame.getTableModel();
        loadStudent();
        frame.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onTableRowClick();
            }
        });

        frame.setVisible(true);
        frame.getBtnCapNhat().addActionListener(e -> updateStudent());
        frame.getBtnXoa().addActionListener(e -> deleteStudent());
        frame.getBtnReset().addActionListener(e -> resetFields());
        frame.getBtnThem().addActionListener(e -> addStudent());
    }

    private void onTableRowClick() {
        int selectedRow = frame.getTable().getSelectedRow();

        if (selectedRow >= 0) {
            frame.getTxtMaSV().setText((String) tableModel.getValueAt(selectedRow, 0));
            frame.getTxtHoTen().setText((String) tableModel.getValueAt(selectedRow, 1));
            frame.getTxtClassName().setText((String) tableModel.getValueAt(selectedRow, 2));
            Object gpaValue = tableModel.getValueAt(selectedRow, 3);
            frame.getTxtGPA().setText(gpaValue != null ? gpaValue.toString() : "");
        }
    }

    private void addStudent() {
        String id = frame.getTxtMaSV().getText().trim();
        String name = frame.getTxtHoTen().getText().trim();
        String className = frame.getTxtClassName().getText().trim();
        String gpaTxt = frame.getTxtGPA().getText().trim();

        if (id.isEmpty() || name.isEmpty() || className.isEmpty() || gpaTxt.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Vui lòng điền đầy đủ thông tin", "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        float gpa;
        try {
            gpa = Float.parseFloat(gpaTxt);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "GPA phải là số thực",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(id, name, className, gpa);
        if (studentDao.create(student)) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Thêm sinh viên thành công!"
            );
            loadStudent();
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    "Mã sinh viên đã tồn tại!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void deleteStudent() {
        String id = frame.getTxtMaSV().getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Vui lòng chọn sinh viên cần xóa",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        studentDao.delete(id);
        JOptionPane.showMessageDialog(frame, "Xóa sinh viên thành công!");
        loadStudent();

    }

    private void updateStudent() {
        String id = frame.getTxtMaSV().getText().trim();
        String name = frame.getTxtHoTen().getText().trim();
        String className = frame.getTxtClassName().getText().trim();
        String gpaText = frame.getTxtGPA().getText().trim();

        if (id.isEmpty() || name.isEmpty() || className.isEmpty() || gpaText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Vui lòng điền đầy đủ thông tin!",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        float gpa;
        try {
            gpa = Float.parseFloat(gpaText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "GPA phải là một số thực!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Xoá sinh viên cũ, nếu tồn tại
        studentDao.delete(id);

        // Thêm sinh viên mới với thông tin đã cập nhật
        Student updatedStudent = new Student(id, name, className, gpa);
        if (studentDao.create(updatedStudent)) {
            JOptionPane.showMessageDialog(frame, "Cập nhật sinh viên thành công!");
            loadStudent();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Cập nhật không thành công!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        frame.getTxtMaSV().setText("");
        frame.getTxtHoTen().setText("");
        frame.getTxtClassName().setText("");
        frame.getTxtGPA().setText("");
    }

    private void loadStudent() {
        List<Student> students = studentDao.getAll();
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getClassId(),
                    student.getGpa()});
        }
    }

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

    }
}
