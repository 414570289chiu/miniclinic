package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.edu.fju.miniclinic.model.Patient;
import tw.edu.fju.miniclinic.model.PatientRepository;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepo;

    // 給瀏覽器看的 HTML 網頁
    @GetMapping("/patients")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientRepo.findAll());
        return "patients";
    }

    // 給 REST Client 測試用的 JSON API 端點
    @GetMapping("/api/patients")
    @ResponseBody
    public List<Patient> getPatientsApi() {
        return patientRepo.findAll();
    }
}