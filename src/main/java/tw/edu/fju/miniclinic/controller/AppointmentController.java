package tw.edu.fju.miniclinic.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tw.edu.fju.miniclinic.model.*;
import java.time.LocalDate;

@Controller
public class AppointmentController {

    @Autowired private DoctorRepository doctorRepo;
    @Autowired private PatientRepository patientRepo;
    @Autowired private AppointmentRepository appointmentRepo;

    @GetMapping("/appointment/new")
    public String showForm(Model model) {
        model.addAttribute("appointmentForm", new AppointmentForm());
        model.addAttribute("doctors", doctorRepo.findAll());
        return "appointment-new";
    }

    @PostMapping("/appointment/new")
    public String processForm(@Valid @ModelAttribute("appointmentForm") AppointmentForm form,
                              BindingResult result,
                              Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("doctors", doctorRepo.findAll());
            return "appointment-new"; // 有錯就直接退回掛號畫面
        }

        Patient patient = patientRepo.findById(form.getChartNo()).orElse(null);
        Doctor doctor = doctorRepo.findById(form.getDoctorId()).orElse(null);

        if (patient == null || doctor == null) {
            model.addAttribute("errorMessage", "查無此病歷號或醫師資料錯誤");
            model.addAttribute("appointmentForm", form);
            model.addAttribute("doctors", doctorRepo.findAll());
            return "appointment-new";
        }

        Appointment appt = new Appointment();
        appt.setPatient(patient);
        appt.setDoctor(doctor);
        appt.setApptDate(LocalDate.parse(form.getApptDate()));
        appt.setTimeSlot(form.getTimeSlot());
        appt.setStatus("BOOKED");

        Appointment savedAppt = appointmentRepo.save(appt);

        model.addAttribute("form", form);
        model.addAttribute("doctorName", doctor.getName());
        model.addAttribute("deptName", doctor.getDepartment());
        model.addAttribute("savedAppt", savedAppt);

        return "appointment-result";
    }

    @GetMapping("/appointments")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepo.findAll());
        return "appointments";
    }
}