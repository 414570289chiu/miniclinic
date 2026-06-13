package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import tw.edu.fju.miniclinic.model.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentApiController {
    @Autowired private AppointmentRepository apptRepo;
    @Autowired private DoctorRepository docRepo;

    @GetMapping("/count")
    public Map<String, Long> getCount() {
        return Map.of("count", apptRepo.count());
    }

    @GetMapping
    public List<Appointment> getAppointments(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String doctorId) {
        if (date != null && !date.isBlank()) {
            return apptRepo.findByApptDate(LocalDate.parse(date));
        } else if (doctorId != null && !doctorId.isBlank()) {
            return docRepo.findById(doctorId).map(doc -> apptRepo.findByDoctor(doc)).orElse(List.of());
        }
        return apptRepo.findAll();
    }
    @PutMapping("/{apptId}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long apptId,
            @RequestBody Map<String, String> payload,
            HttpSession session) {

        String myId = (String) session.getAttribute("loggedInDoctorId");
        if (myId == null) {
            return ResponseEntity.status(401).build();
        }

        Appointment appt = apptRepo.findById(apptId).orElse(null);
        if (appt == null) return ResponseEntity.notFound().build();

        if (!appt.getDoctor().getDoctorId().equals(myId)) {
            return ResponseEntity.status(403).build();
        }

        String status = payload.get("status");
        if (!List.of("BOOKED", "COMPLETED", "CANCELLED").contains(status)) {
            return ResponseEntity.badRequest().build();
        }

        appt.setStatus(status);
        return ResponseEntity.ok(apptRepo.save(appt));
    }
}