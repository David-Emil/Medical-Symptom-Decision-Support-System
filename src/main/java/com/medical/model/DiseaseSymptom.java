/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model;

/**
 *
 * @author David
 */
public class DiseaseSymptom {
    private int diseaseId;
    private int symptomId;
    private int weight;

    public DiseaseSymptom(int diseaseId, int symptomId, int weight) {
        this.diseaseId = diseaseId;
        this.symptomId = symptomId;
        this.weight = weight;
    }

    public int getDiseaseId() { return diseaseId; }
    public int getSymptomId() { return symptomId; }
    public int getWeight() { return weight; }
}
