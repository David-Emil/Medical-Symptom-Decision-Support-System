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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManagePatientPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JTextField nameField, ageField;
    private JComboBox<String> genderBox;

    public ManagePatientPanel() {
        setLayout(new BorderLayout());

        // 🔝 Top Form
        JPanel form = new JPanel(new GridLayout(2, 4, 10, 10));

        nameField = new JTextField();
        ageField = new JTextField();
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete Selected");

        form.add(new JLabel("Name"));
        form.add(new JLabel("Age"));
        form.add(new JLabel("Gender"));
        form.add(new JLabel(""));

        form.add(nameField);
        form.add(ageField);
        form.add(genderBox);
        form.add(addBtn);

        add(form, BorderLayout.NORTH);

        // 📊 Table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Gender"}, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(deleteBtn, BorderLayout.SOUTH);

        loadPatients();

        // ➕ Add
        addBtn.addActionListener(e -> {
            Patient p = new Patient(
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    genderBox.getSelectedItem().toString()
            );

            new PatientDAO().addPatient(p);
            loadPatients();
        });

        // ❌ Delete
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row first!");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            new PatientDAO().deletePatient(id);
            loadPatients();
        });
    }

    private void loadPatients() {
        model.setRowCount(0);

        List<Patient> list = new PatientDAO().getAllPatients();

        for (Patient p : list) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getAge(),
                    p.getGender()
            });
        }
    }
}
