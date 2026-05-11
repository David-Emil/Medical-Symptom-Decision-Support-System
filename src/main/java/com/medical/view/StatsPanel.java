/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

import com.medical.util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StatsPanel extends JPanel {

    private JTextArea statsArea;

    public StatsPanel() {
        setLayout(new BorderLayout());

        statsArea = new JTextArea();
        statsArea.setFont(new Font("Arial", Font.BOLD, 16));
        statsArea.setEditable(false);

        add(new JScrollPane(statsArea), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh Stats");
        add(refreshBtn, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> loadStats());

        loadStats();
    }

    private void loadStats() {

        StringBuilder sb = new StringBuilder();

        try (Connection conn = DBConnection.getConnection()) {

            // Total patients
            sb.append("Total Patients: ").append(getCount(conn, "Patients")).append("\n\n");

            // Total diagnoses
            sb.append("Total Diagnoses: ").append(getCount(conn, "DiagnosisResults")).append("\n\n");

            // Most common diseases
            sb.append("Top Diseases:\n");
            String diseaseQuery = """
                SELECT TOP 3 d.Name, COUNT(*) as count
                FROM DiagnosisResults r
                JOIN Diseases d ON r.DiseaseId = d.Id
                GROUP BY d.Name
                ORDER BY count DESC
            """;

            PreparedStatement stmt = conn.prepareStatement(diseaseQuery);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sb.append("- ").append(rs.getString("Name"))
                  .append(" (").append(rs.getInt("count")).append(")\n");
            }

            statsArea.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getCount(Connection conn, String table) throws Exception {
        String q = "SELECT COUNT(*) FROM " + table;
        PreparedStatement stmt = conn.prepareStatement(q);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
