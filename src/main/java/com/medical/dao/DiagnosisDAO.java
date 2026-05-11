/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.dao;

/**
 *
 * @author David
 */
import com.medical.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DiagnosisDAO {

    public void saveResult(int patientId, int diseaseId, double score) {

        String query = """
            INSERT INTO DiagnosisResults
            (PatientId, DiseaseId, Score)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, patientId);
            stmt.setInt(2, diseaseId);
            stmt.setDouble(3, score);

            int rows = stmt.executeUpdate();

            System.out.println(rows + " diagnosis saved.");

        } catch (Exception e) {
            System.out.println("SAVE FAILED ❌");
            e.printStackTrace();
        }
    }
}