/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.dao;

/**
 *
 * @author David
 */
import com.medical.model.DiseaseSymptom;
import com.medical.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseSymptomDAO {

    public List<DiseaseSymptom> getAllRelations() {
        List<DiseaseSymptom> list = new ArrayList<>();

        String query = "SELECT * FROM DiseaseSymptoms";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DiseaseSymptom ds = new DiseaseSymptom(
                        rs.getInt("DiseaseId"),
                        rs.getInt("SymptomId"),
                        rs.getInt("Weight")
                );
                list.add(ds);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public void addRelation(int diseaseId, int symptomId, int weight) {
    String query = "INSERT INTO DiseaseSymptoms (DiseaseId, SymptomId, Weight) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, diseaseId);
        stmt.setInt(2, symptomId);
        stmt.setInt(3, weight);

        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
