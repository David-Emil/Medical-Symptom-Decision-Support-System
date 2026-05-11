/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author David
 */
import com.medical.dao.DiseaseDAO;
import com.medical.model.Disease;

public class TestDisease {
    public static void main(String[] args) {
        DiseaseDAO dao = new DiseaseDAO();

        for (Disease d : dao.getAllDiseases()) {
            System.out.println(d.getName() + " - " + d.getSeverityLevel());
        }
    }
}
