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

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private JTextField nameField, ageField;
    private JComboBox<String> genderBox;

    public PatientPanel() {
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        add(genderBox);

        JButton saveBtn = new JButton("Save Patient");
        add(saveBtn);

        saveBtn.addActionListener(e -> savePatient());
    }

    private void savePatient() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderBox.getSelectedItem().toString();

        Patient p = new Patient(name, age, gender);
        new PatientDAO().addPatient(p);

        JOptionPane.showMessageDialog(this, "Patient Saved ✅");
    }
}
