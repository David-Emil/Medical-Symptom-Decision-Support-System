/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

/**
 *
 * @author David
 */
import com.medical.dao.*;
import com.medical.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LinkPanel extends JPanel {

    private JComboBox<String> diseaseBox;
    private JComboBox<String> symptomBox;

    private Map<String, Integer> diseaseMap = new HashMap<>();
    private Map<String, Integer> symptomMap = new HashMap<>();

    public LinkPanel() {

        setLayout(new GridLayout(5, 2, 10, 10));

        diseaseBox = new JComboBox<>();
        symptomBox = new JComboBox<>();

        JTextField weightField = new JTextField();

        loadData();

        add(new JLabel("Disease:"));
        add(diseaseBox);

        add(new JLabel("Symptom:"));
        add(symptomBox);

        add(new JLabel("Weight (1-5):"));
        add(weightField);

        JButton linkBtn = new JButton("Link");
        add(linkBtn);

        linkBtn.addActionListener(e -> {
            int diseaseId = diseaseMap.get(diseaseBox.getSelectedItem());
            int symptomId = symptomMap.get(symptomBox.getSelectedItem());
            int weight = Integer.parseInt(weightField.getText());

            new DiseaseSymptomDAO().addRelation(diseaseId, symptomId, weight);

            JOptionPane.showMessageDialog(this, "Linked Successfully ✅");
        });
    }

    private void loadData() {

        for (Disease d : new DiseaseDAO().getAllDiseases()) {
            diseaseBox.addItem(d.getName());
            diseaseMap.put(d.getName(), d.getId());
        }

        for (Symptom s : new SymptomDAO().getAllSymptoms()) {
            symptomBox.addItem(s.getName());
            symptomMap.put(s.getName(), s.getId());
        }
    }
}
