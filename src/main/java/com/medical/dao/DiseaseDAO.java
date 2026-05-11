/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.dao;

/**
 *
 * @author David
 */
import com.medical.model.Disease;
import com.medical.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseDAO {

    public List<Disease> getAllDiseases() {
        List<Disease> diseases = new ArrayList<>();

        String query = "SELECT * FROM Diseases";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disease d = new Disease(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("SeverityLevel")
                );
                diseases.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diseases;
    }
    public void addDisease(String name, String desc, String severity) {
    String query = "INSERT INTO Diseases (Name, Description, SeverityLevel) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, name);
        stmt.setString(2, desc);
        stmt.setString(3, severity);

        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
