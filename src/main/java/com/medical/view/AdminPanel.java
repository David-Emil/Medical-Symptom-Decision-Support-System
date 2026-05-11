/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

/**
 *
 * @author David
 */
import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    public AdminPanel() {
        setLayout(new BorderLayout());

        JTabbedPane adminTabs = new JTabbedPane();

        adminTabs.addTab("Add Symptom", new AddSymptomPanel());
        adminTabs.addTab("Add Disease", new AddDiseasePanel());
        adminTabs.addTab("Link Disease", new LinkPanel());
        adminTabs.addTab("Manage Patients", new ManagePatientPanel());

        add(adminTabs, BorderLayout.CENTER);
    }
}
