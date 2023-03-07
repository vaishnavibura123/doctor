package com.example.Doctor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_patient")
public class Patient {
    @Id
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "disease_type")
    private String diseaseType;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone_no")
    private String phoneNo;
    @JoinColumn(name = "doctor_id")
    @ManyToOne
    private Doctor doctorId;
}
