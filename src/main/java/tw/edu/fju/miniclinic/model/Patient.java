package tw.edu.fju.miniclinic.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "chart_no", length = 20)
    private String chartNo;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "phone", length = 20)
    private String phone;

    @JdbcTypeCode(Types.VARCHAR)
    @Convert(converter = LocalDateConverter.class)
    @Column(name = "birth_date", columnDefinition = "TEXT")
    private LocalDate birthDate;

    public Patient() {} // JPA 必須的無參建構子

    public Patient(String chartNo, String name, String gender, String phone, LocalDate birthDate) {
        this.chartNo = chartNo;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    // --- Getters & Setters ---
    public String getChartNo() { return chartNo; }
    public void setChartNo(String chartNo) { this.chartNo = chartNo; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}