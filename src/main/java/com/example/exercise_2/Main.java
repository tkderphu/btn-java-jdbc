package com.example.exercise_2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Exercise2 extends JFrame {
    private JTextField txtMaSV, txtHoTen, txtClassName, txtGPA;
    private JTable table;
    private DefaultTableModel tableModel;

    public Exercise2() {
        setTitle("Quản lý sinh viên");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Mã SV:"));
        txtMaSV = new JTextField();
        inputPanel.add(txtMaSV);

        inputPanel.add(new JLabel("Họ Tên:"));
        txtHoTen = new JTextField();
        inputPanel.add(txtHoTen);

        inputPanel.add(new JLabel("Lớp:"));
        txtClassName = new JTextField();
        inputPanel.add(txtClassName);

        inputPanel.add(new JLabel("GPA:"));
        txtGPA = new JTextField();
        inputPanel.add(txtGPA);

        tableModel = new DefaultTableModel(new Object[]{"Mã SV", "Họ Tên", "Lớp", "GPA"}, 0);
        table = new JTable(tableModel);

        JPanel buttonPanel = new JPanel();
        JButton btnHienThi = new JButton("Hiển thị");
        JButton btnThem = new JButton("Thêm");
        JButton btnCapNhat = new JButton("Cập Nhật");
        JButton btnXoa = new JButton("Xoá");
        JButton btnReset = new JButton("Reset");

        buttonPanel.add(btnHienThi);
        buttonPanel.add(btnThem);
        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exercise2().setVisible(true));
    }
}
