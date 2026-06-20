package com.student;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "Ragavi@2005");

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}