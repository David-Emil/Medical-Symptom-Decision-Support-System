/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model;

/**
 *
 * @author David
 */
public class Disease {
    private int id;
    private String name;
    private String description;
    private String severityLevel;

    public Disease(int id, String name, String description, String severityLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.severityLevel = severityLevel;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSeverityLevel() { return severityLevel; }
}
