/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=Medical_Symptom_Decision;encrypt=true;trustServerCertificate=true";
    
    private static final String USER = "sa";
    private static final String PASSWORD = "123456"; // or your actual password
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to DB ✅");
            return conn;
        } catch (Exception e) {
            System.out.println("Connection failed ❌");
            e.printStackTrace();
            return null;
        }
    }
}
