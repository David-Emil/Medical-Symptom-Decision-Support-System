/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author David
 */
import com.medical.dao.SymptomDAO;
import com.medical.model.Symptom;

public class TestSymptom {

    public static void main(String[] args) {
        SymptomDAO dao = new SymptomDAO();

        for (Symptom s : dao.getAllSymptoms()) {
            System.out.println(s.getId() + " - " + s.getName());
        }
    }
}
