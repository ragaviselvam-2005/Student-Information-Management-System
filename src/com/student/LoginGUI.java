package com.student;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginGUI extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginGUI() {

        setTitle("Student Management System - Login");
        setSize(450,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("STUDENT LOGIN");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(120,30,250,30);
        add(heading);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(50,100,100,25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(170,100,180,25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50,150,100,25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170,150,180,25);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(160,210,100,30);
        add(btnLogin);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "SELECT * FROM users WHERE username=? AND pass_word=?");

            ps.setString(1, txtUsername.getText());
            ps.setString(2, String.valueOf(txtPassword.getPassword()));

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful");

                new StudentGUI();
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new LoginGUI();
    }
}