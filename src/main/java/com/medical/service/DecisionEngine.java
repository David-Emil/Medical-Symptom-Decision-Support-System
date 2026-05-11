/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medical.service;

/**
 *
 * @author David
 */
import com.medical.dao.DiseaseDAO;
import com.medical.dao.DiseaseSymptomDAO;
import com.medical.model.Disease;
import com.medical.model.DiseaseSymptom;
import com.medical.model.SymptomInput;

import java.util.*;

public class DecisionEngine {

    private DiseaseDAO diseaseDAO = new DiseaseDAO();
    private DiseaseSymptomDAO relationDAO = new DiseaseSymptomDAO();

    public Map<Disease, Double> diagnose(SymptomInput input) {

        List<Disease> diseases = diseaseDAO.getAllDiseases();
        List<DiseaseSymptom> relations = relationDAO.getAllRelations();

        Map<Integer, Double> scores = new HashMap<>();

        // Initialize scores
        for (Disease d : diseases) {
            scores.put(d.getId(), 0.0);
        }

        // Calculate scores
        for (DiseaseSymptom rel : relations) {
            if (input.hasSymptom(rel.getSymptomId())) {
                double current = scores.get(rel.getDiseaseId());
                current += rel.getWeight();
                scores.put(rel.getDiseaseId(), current);
            }
        }

        // Map Disease → Score
        Map<Disease, Double> result = new HashMap<>();

        for (Disease d : diseases) {
            result.put(d, scores.get(d.getId()));
        }

        return sortByScoreDescending(result);
    }

    private Map<Disease, Double> sortByScoreDescending(Map<Disease, Double> map) {
        List<Map.Entry<Disease, Double>> list = new ArrayList<>(map.entrySet());

        list.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        Map<Disease, Double> sorted = new LinkedHashMap<>();
        for (Map.Entry<Disease, Double> entry : list) {
            sorted.put(entry.getKey(), entry.getValue());
        }

        return sorted;
    }
}