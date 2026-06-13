package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tw.edu.fju.miniclinic.model.Doctor;
import tw.edu.fju.miniclinic.model.DoctorRepository;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorPageController {

    @Autowired
    private DoctorRepository doctorRepo;

    // 顯示醫師清單頁面（支援科別篩選）
    @GetMapping
    public String listDoctors(@RequestParam(required = false) String department, Model model) {
        List<Doctor> doctors;
        
        // 判斷是否有傳入科別參數進行篩選
        if (department == null || department.isBlank()) {
            doctors = doctorRepo.findAll(); // 查出所有醫師
        } else {
            doctors = doctorRepo.findByDepartment(department); // 依科別篩選
        }

        // 將資料放入 Model 傳遞給前端 Thymeleaf 網頁
        model.addAttribute("doctors", doctors);
        model.addAttribute("departments", doctorRepo.findAllDepartments());
        model.addAttribute("selectedDept", department);

        return "doctors"; // 渲染 templates/doctors.html
    }

    // 顯示單一醫師詳細頁面
    @GetMapping("/{doctorId}")
    public String doctorDetail(@PathVariable String doctorId, Model model) {
        return doctorRepo.findById(doctorId)
                .map(d -> {
                    model.addAttribute("doctor", d);
                    return "doctor-detail"; // 找到醫師，渲染 templates/doctor-detail.html
                })
                .orElse("redirect:/doctors"); // 找不到醫師，重導向回醫師清單
    }
}