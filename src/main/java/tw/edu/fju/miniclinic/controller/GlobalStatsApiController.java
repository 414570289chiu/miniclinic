package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.edu.fju.miniclinic.model.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GlobalStatsApiController {

    @Autowired private DoctorRepository doctorRepo;
    @Autowired private PatientRepository patientRepo;
    @Autowired private AppointmentRepository apptRepo;

    // T01 驗證端點：讓評分機器人檢查你的伺服器有沒有活著
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    // T05, T06, T07 驗證端點：自動化審查開放數據
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getGlobalStats() {
        long totalDoctors = doctorRepo.count();
        long totalPatients = patientRepo.count();
        long totalAppointments = apptRepo.count();

        // 利用剛剛在 Repository 新增的方法來計算各狀態數量
        long bookedCount = apptRepo.countByStatus("BOOKED");
        long completedCount = apptRepo.countByStatus("COMPLETED");
        long cancelledCount = apptRepo.countByStatus("CANCELLED");

        Map<String, Object> byStatus = new LinkedHashMap<>();
        byStatus.put("BOOKED", bookedCount);
        byStatus.put("COMPLETED", completedCount);
        byStatus.put("CANCELLED", cancelledCount);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalDoctors", totalDoctors);
        result.put("totalPatients", totalPatients);
        result.put("totalAppointments", totalAppointments);
        result.put("byStatus", byStatus);

        return ResponseEntity.ok(result);
    }
}