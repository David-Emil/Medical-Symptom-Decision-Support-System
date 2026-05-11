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

import javax.swing.*;
import java.awt.*;

public class AddSymptomPanel extends JPanel {

    public AddSymptomPanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();

        add(new JLabel("Symptom Name:"));
        add(nameField);

        add(new JLabel("Description:"));
        add(descField);

        JButton saveBtn = new JButton("Add Symptom");
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            new SymptomDAO().addSymptom(
                    nameField.getText(),
                    descField.getText()
            );

            JOptionPane.showMessageDialog(this, "Symptom Added ✅");
        });
    }
}
