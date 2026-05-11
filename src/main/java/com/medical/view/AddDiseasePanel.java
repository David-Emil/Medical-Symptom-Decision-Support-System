/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

/**
 *
 * @author David
 */
import com.medical.dao.DiseaseDAO;

import javax.swing.*;
import java.awt.*;

public class AddDiseasePanel extends JPanel {

    public AddDiseasePanel() {
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();

        JComboBox<String> severityBox =
                new JComboBox<>(new String[]{"Low", "Medium", "High"});

        add(new JLabel("Disease Name:"));
        add(nameField);

        add(new JLabel("Description:"));
        add(descField);

        add(new JLabel("Severity:"));
        add(severityBox);

        JButton saveBtn = new JButton("Add Disease");
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            new DiseaseDAO().addDisease(
                    nameField.getText(),
                    descField.getText(),
                    severityBox.getSelectedItem().toString()
            );

            JOptionPane.showMessageDialog(this, "Disease Added ✅");
        });
    }
}