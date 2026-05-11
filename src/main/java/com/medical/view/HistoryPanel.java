/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

/**
 *
 * @author David
 */
import com.medical.dao.PatientDAO;
import com.medical.model.Patient;
import com.medical.util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryPanel extends JPanel {

    private JComboBox<String> patientBox;
    private Map<String, Integer> patientMap = new HashMap<>();
    private JTable table;

    public HistoryPanel() {

        setLayout(new BorderLayout());

        // 🔝 TOP PANEL (Filter Section)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Filter History"));

        patientBox = new JComboBox<>();
        topPanel.add(new JLabel("Select Patient: "));
        topPanel.add(patientBox);

        JButton loadBtn = new JButton("Load History");
        JButton refreshBtn = new JButton("Refresh");

        topPanel.add(loadBtn);
        topPanel.add(refreshBtn);

        add(topPanel, BorderLayout.NORTH);

        // 📊 TABLE
        table = new JTable();
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 🔄 Load patients initially
        loadPatients();

        // 🎯 Load history button action
        loadBtn.addActionListener(e -> {
            String selected = (String) patientBox.getSelectedItem();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient first!");
                return;
            }

            int patientId = patientMap.get(selected);
            loadData(patientId);
        });

        // 🔄 Refresh patients list
        refreshBtn.addActionListener(e -> loadPatients());
    }

    // 🗄️ Load patients into dropdown
    private void loadPatients() {
        patientBox.removeAllItems();
        patientMap.clear();

        List<Patient> patients = new PatientDAO().getAllPatients();

        for (Patient p : patients) {
            String label = p.getName() + " (ID: " + p.getId() + ")";
            patientBox.addItem(label);
            patientMap.put(label, p.getId());
        }
    }

    // 📊 Load filtered history
    private void loadData(int patientId) {

        String query = """
        SELECT p.Name AS PatientName, d.Name AS DiseaseName, r.Score, r.CreatedAt
        FROM DiagnosisResults r
        JOIN Patients p ON r.PatientId = p.Id
        JOIN Diseases d ON r.DiseaseId = d.Id
        WHERE p.Id = ?
        ORDER BY r.CreatedAt DESC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"Patient", "Disease", "Score", "Date"}, 0
            );

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("PatientName"),
                        rs.getString("DiseaseName"),
                        rs.getDouble("Score"),
                        rs.getTimestamp("CreatedAt")
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}