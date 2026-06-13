package tw.edu.fju.miniclinic.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByApptDate(LocalDate apptDate);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatient(Patient patient);

    @Query("SELECT d.department, COUNT(a) FROM Appointment a JOIN a.doctor d GROUP BY d.department")
    List<Object[]> countAppointmentsByDepartment();
    
    // 🛠️ 本週新增：為了期末 API，讓 JPA 自動幫我們依照狀態計算總筆數
    long countByStatus(String status);
    
    List<Appointment> findByDoctorAndApptDate(Doctor doctor, LocalDate apptDate);
}