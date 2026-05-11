/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author David
 */
import com.medical.model.SymptomInput;
import com.medical.model.Disease;
import com.medical.service.DecisionEngine;

import java.util.*;

public class TestDecision {

    public static void main(String[] args) {

        // Example: Fever + Cough + Fatigue
        Set<Integer> selectedSymptoms = new HashSet<>();
        selectedSymptoms.add(1); // Fever
        selectedSymptoms.add(2); // Cough
        selectedSymptoms.add(3); // Fatigue

        SymptomInput input = new SymptomInput(selectedSymptoms);

        DecisionEngine engine = new DecisionEngine();
        Map<Disease, Double> result = engine.diagnose(input);

        int count = 0;
        for (Map.Entry<Disease, Double> entry : result.entrySet()) {
            System.out.println(entry.getKey().getName() + " → Score: " + entry.getValue());

            count++;
            if (count == 3) break; // Top 3 only
        }
    }
}
