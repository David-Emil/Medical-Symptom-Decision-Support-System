/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.dao;

import com.medical.model.Symptom;
import com.medical.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SymptomDAO {

    public List<Symptom> getAllSymptoms() {
        List<Symptom> symptoms = new ArrayList<>();

        String query = "SELECT * FROM Symptoms";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Symptom symptom = new Symptom(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
                symptoms.add(symptom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return symptoms;
    }
    public void addSymptom(String name, String description) {
    String query = "INSERT INTO Symptoms (Name, Description) VALUES (?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, name);
        stmt.setString(2, description);
        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
