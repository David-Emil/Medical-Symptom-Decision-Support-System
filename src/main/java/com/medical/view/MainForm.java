/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.view;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    public MainForm() {
        setTitle("Medical Decision System");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Diagnosis", new DiagnosisPanel());
        tabs.addTab("Patients", new PatientPanel());
        tabs.addTab("History", new HistoryPanel());
        tabs.addTab("Statistics", new StatsPanel());
        tabs.addTab("Admin", new AdminPanel());

        add(tabs, BorderLayout.CENTER);
    }
}