package com.example.Doctor.controller;

import com.example.Doctor.model.Patient;
import com.example.Doctor.service.PatientService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/patient")
    public String savePatient(@RequestBody Patient patient){
        JSONObject jsonObject=new JSONObject(patient);
        Patient patient1=patientService.setPatient(jsonObject);
        patientService.savePatient(patient1);
        return "Saved";

    }
    @GetMapping("/patient")
    public List<Patient> getPatient(@Nullable@RequestParam Integer doctorId,@Nullable@RequestParam Integer patientId){
        return patientService.getPatient(doctorId,patientId);
    }
}
