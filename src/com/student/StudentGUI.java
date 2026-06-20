package com.student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentGUI extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtDepartment;
    private JTextField txtGender;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtYear;

    private JLabel lblTotalStudents;

    private JTable table;
    private DefaultTableModel model;

    public StudentGUI() {

        setTitle("Student Management Dashboard");
        setSize(1100,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading =
                new JLabel("STUDENT MANAGEMENT DASHBOARD");

        heading.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24));
        heading.setBounds(280, 20, 500,35);
        add(heading);

        lblTotalStudents = new JLabel("Total Students : 0");

        lblTotalStudents.setFont(  new Font("Arial",Font.BOLD,18));
        lblTotalStudents.setBounds( 50, 80,250,30);
        add(lblTotalStudents);
        JLabel lblId = new JLabel("Student ID");
        lblId.setBounds(50,140,120,25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(180,140,220,25);
        add(txtId);

        JLabel lblName = new JLabel("Student Name");
        lblName.setBounds(50,180,120,25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(180,180,220,25);
        add(txtName);

        JLabel lblDept = new JLabel("Department");
        lblDept.setBounds(50,220,120,25);
        add(lblDept);

        txtDepartment = new JTextField();
        txtDepartment.setBounds(180,220,220,25);
        add(txtDepartment);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(50,260,120,25);
        add(lblGender);

        txtGender = new JTextField();
        txtGender.setBounds(180,260,220,25);
        add(txtGender);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50,300,120,25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(180,300,220,25);
        add(txtEmail);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50,340,120,25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(180,340,220,25);
        add(txtPhone);

        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(50,380,120,25);
        add(lblYear);

        txtYear = new JTextField();
        txtYear.setBounds(180,380,220,25);
        add(txtYear);

        JButton btnAdd = new JButton("Add");
        JButton btnSearch = new JButton("Search");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        btnAdd.setBounds(520,150,130,35);
        btnSearch.setBounds(700,150,130,35);
        btnUpdate.setBounds(520,220,130,35);
        btnDelete.setBounds(700,220,130,35);
        btnClear.setBounds(610,290,130,35);

        add(btnAdd);
        add(btnSearch);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Gender");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Year");
        model.addColumn("Attendance");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds( 40, 470, 1000,180);
        add(scrollPane);

        btnAdd.addActionListener(e -> addStudent());
        btnSearch.addActionListener(e -> searchStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());
        loadStudents();
        setVisible(true);
    }

    private void addStudent() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement( "INSERT INTO students VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1,Integer.parseInt(txtId.getText()));
            ps.setString(2,txtName.getText());
            ps.setString(3,txtDepartment.getText());
            ps.setString(4,txtGender.getText());
            ps.setString(5,txtEmail.getText());
            ps.setString(6,txtPhone.getText());
            ps.setInt(7,Integer.parseInt(txtYear.getText()));
            ps.setString(8,"Absent");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Student Added Successfully");
            loadStudents();
            clearFields();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void searchStudent() {

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =con.prepareStatement( "SELECT * FROM students WHERE id=?");
            ps.setInt(1,Integer.parseInt(txtId.getText()));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                txtName.setText(rs.getString("name"));
                txtDepartment.setText(rs.getString("department"));
                txtGender.setText(rs.getString("gender"));
                txtEmail.setText(rs.getString("email"));
                txtPhone.setText(rs.getString("phone"));
                txtYear.setText(String.valueOf(rs.getInt("year_of_study")));
                JOptionPane.showMessageDialog( this,"Student Found");
            } else {
                JOptionPane.showMessageDialog( this,"Student Not Found");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void updateStudent() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement( "UPDATE students SET name=?, department=?, gender=?, email=?, phone=?, year_of_study=? WHERE id=?");

            ps.setString(1,txtName.getText());
            ps.setString(2,txtDepartment.getText());
            ps.setString(3,txtGender.getText());
            ps.setString(4,txtEmail.getText());
            ps.setString(5,txtPhone.getText());
            ps.setInt(6,Integer.parseInt(txtYear.getText()));
            ps.setInt(7,Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog( this, "Student Updated Successfully");
            loadStudents();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent() {

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?");
            ps.setInt(1,Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Student Deleted Successfully");
            loadStudents();
            clearFields();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtGender.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtYear.setText("");
    }

    private void loadStudents() {
        try {
            model.setRowCount(0);
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery("SELECT * FROM students");
            int count = 0;
            while(rs.next()) {
                model.addRow(new Object[]{

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("year_of_study"),
                        rs.getString("attendance")
                });
                count++;
            }
            lblTotalStudents.setText(
                    "Total Students : " + count);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}