package com.example.exercise_2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Frame extends JFrame {
    private JTextField txtMaSV, txtHoTen, txtClassName, txtGPA;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnHienThi, btnThem, btnCapNhat, btnXoa, btnReset;

    public Frame() {
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
        btnHienThi = new JButton("Hiển thị");
        btnThem = new JButton("Thêm");
        btnCapNhat = new JButton("Cập Nhật");
        btnXoa = new JButton("Xoá");
        btnReset = new JButton("Reset");

        buttonPanel.add(btnHienThi);
        buttonPanel.add(btnThem);
        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public JButton getBtnCapNhat() {
        return btnCapNhat;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public JButton getBtnHienThi() {
        return btnHienThi;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getTxtMaSV() {
        return txtMaSV;
    }

    public JTextField getTxtHoTen() {
        return txtHoTen;
    }

    public JTextField getTxtClassName() {
        return txtClassName;
    }

    public JTextField getTxtGPA() {
        return txtGPA;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame().setVisible(true));
    }
}
