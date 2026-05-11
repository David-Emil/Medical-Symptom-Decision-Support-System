/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.model;

/**
 *
 * @author David
 */
import java.util.Set;

public class SymptomInput {
    private Set<Integer> selectedSymptomIds;

    public SymptomInput(Set<Integer> selectedSymptomIds) {
        this.selectedSymptomIds = selectedSymptomIds;
    }

    public boolean hasSymptom(int symptomId) {
        return selectedSymptomIds.contains(symptomId);
    }

    public Set<Integer> getSelectedSymptomIds() {
        return selectedSymptomIds;
    }
}
