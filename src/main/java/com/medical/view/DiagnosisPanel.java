/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

/**
 *
 * @author David
 */
import com.medical.dao.SymptomDAO;
import com.medical.model.*;
import com.medical.service.DecisionEngine;
import com.medical.model.Patient;
import com.medical.dao.PatientDAO;
import com.medical.dao.DiagnosisDAO;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DiagnosisPanel extends JPanel {

    private JPanel symptomPanel;
    private JPanel resultPanel;
    private List<JCheckBox> checkBoxes = new ArrayList<>();
    private JComboBox<String> patientBox;
    private Map<String, Integer> patientMap = new HashMap<>();
    
    private void clearDiagnosisScreen() {

    // 🔹 Uncheck all symptoms
    for (JCheckBox cb : checkBoxes) {
        cb.setSelected(false);
    }

    // 🔹 Clear results panel
    resultPanel.removeAll();

    // 🔹 Refresh UI
    resultPanel.revalidate();
    resultPanel.repaint();
};

    public DiagnosisPanel() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
topPanel.setBorder(BorderFactory.createTitledBorder("Patient"));

JButton refreshBtn = new JButton("Refresh");
refreshBtn.addActionListener(e -> clearDiagnosisScreen());
refreshBtn.setBackground(new Color(0, 123, 255));
refreshBtn.setForeground(Color.WHITE);
refreshBtn.setFocusPainted(false);

patientBox = new JComboBox<>();
topPanel.add(new JLabel("Select Patient: "));
topPanel.add(patientBox);
topPanel.add(refreshBtn);
loadPatients();


add(topPanel, BorderLayout.NORTH);
        // LEFT SIDE (Symptoms)
        JPanel left = new JPanel(new BorderLayout());
        left.setBorder(BorderFactory.createTitledBorder("Select Symptoms"));

        symptomPanel = new JPanel();
        symptomPanel.setLayout(new BoxLayout(symptomPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(symptomPanel);
        left.add(scroll, BorderLayout.CENTER);

        JButton analyzeBtn = new JButton("Analyze");
        analyzeBtn.setBackground(new Color(0, 123, 255));
        analyzeBtn.setForeground(Color.WHITE);
        analyzeBtn.setFocusPainted(false);

        left.add(analyzeBtn, BorderLayout.SOUTH);

        left.setPreferredSize(new Dimension(300, 0));

        // RIGHT SIDE (Results)
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JScrollPane resultScroll = new JScrollPane(resultPanel);

        // ADD BOTH
        add(left, BorderLayout.WEST);
        add(resultScroll, BorderLayout.CENTER);

        loadSymptoms();

        analyzeBtn.addActionListener(e -> analyze());
    }
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

    private void loadSymptoms() {
        SymptomDAO dao = new SymptomDAO();
        List<Symptom> symptoms = dao.getAllSymptoms();

        for (Symptom s : symptoms) {
            JCheckBox cb = new JCheckBox(s.getName());
            cb.putClientProperty("id", s.getId());

            checkBoxes.add(cb);
            symptomPanel.add(cb);
        }
    }

private void analyze() {

    // 🔹 Collect selected symptoms
    Set<Integer> selected = new HashSet<>();

    for (JCheckBox cb : checkBoxes) {
        if (cb.isSelected()) {
            selected.add((int) cb.getClientProperty("id"));
        }
    }

    // Validation
    if (selected.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Please select symptoms!");
        return;
    }

    // 🔹 Get selected patient
    String selectedPatient = (String) patientBox.getSelectedItem();

    if (selectedPatient == null) {
        JOptionPane.showMessageDialog(this,
                "Please select a patient!");
        return;
    }

    int patientId = patientMap.get(selectedPatient);

    // 🔹 Run decision engine
    SymptomInput input = new SymptomInput(selected);

    DecisionEngine engine = new DecisionEngine();

    Map<Disease, Double> results = engine.diagnose(input);

    // 🔹 Display results
    showResults(results);

    // 🔥 SAVE RESULTS
    DiagnosisDAO diagnosisDAO = new DiagnosisDAO();

    for (Map.Entry<Disease, Double> entry : results.entrySet()) {

        double score = entry.getValue();

        // Save only valid scores
        if (score > 0) {

            diagnosisDAO.saveResult(
                    patientId,
                    entry.getKey().getId(),
                    score
            );
        }
    }

    JOptionPane.showMessageDialog(this,
            "Diagnosis saved successfully ✅");
}

    private void showResults(Map<Disease, Double> results) {
        resultPanel.removeAll();

        double maxScore = results.values().stream().mapToDouble(Double::doubleValue).max().orElse(1);

        int count = 0;

        for (Map.Entry<Disease, Double> entry : results.entrySet()) {
            if (entry.getValue() <= 0) continue;

            Disease d = entry.getKey();
            double score = entry.getValue();

            double percent = (score / maxScore) * 100;

            JPanel card = createResultCard(d, percent);

            resultPanel.add(card);

            count++;
            if (count == 3) break;
        }

        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private JPanel createResultCard(Disease d, double percent) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Color based on severity
        Color color;
        switch (d.getSeverityLevel()) {
            case "High":
                color = new Color(220, 53, 69); // red
                break;
            case "Medium":
                color = new Color(255, 193, 7); // yellow
                break;
            default:
                color = new Color(40, 167, 69); // green
        }

        JPanel left = new JPanel();
        left.setBackground(color);
        left.setPreferredSize(new Dimension(10, 0));

        JLabel title = new JLabel(d.getName() + " (" + d.getSeverityLevel() + ")");
        title.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel percentLabel = new JLabel(String.format("Confidence: %.1f%%", percent));

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(title);
        center.add(percentLabel);

        card.add(left, BorderLayout.WEST);
        card.add(center, BorderLayout.CENTER);

        return card;
    }
}
