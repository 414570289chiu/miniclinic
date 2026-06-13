package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tw.edu.fju.miniclinic.model.*;

@Controller
public class StatsController {
    @Autowired private DoctorRepository doctorRepo;
    @Autowired private PatientRepository patientRepo;
    @Autowired private AppointmentRepository apptRepo;

    @GetMapping("/stats")
    public String statsPage(Model model) {
        model.addAttribute("doctorCount", doctorRepo.count());
        model.addAttribute("patientCount", patientRepo.count());
        model.addAttribute("apptCount", apptRepo.count());
        model.addAttribute("deptStats", apptRepo.countAppointmentsByDepartment());
        return "stats";
    }
}