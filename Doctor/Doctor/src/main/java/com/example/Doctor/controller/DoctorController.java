package com.example.Doctor.controller;

import com.example.Doctor.model.Doctor;
import com.example.Doctor.service.DoctorService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("/doctor")
    public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor){
        JSONObject json=new JSONObject(doctor);
        List<String> validationList=validDoctor(json);

        if(validationList.isEmpty()){
            Doctor doctor1=setDoctor(json);
            doctorService.saveDoctor(doctor1);
            return new ResponseEntity<>("Doctor save", HttpStatus.CREATED);
        }else{
            String[] errorName= Arrays.copyOf(validationList.toArray(),validationList.size(), String[].class);
            return new ResponseEntity<>("Please pass these mandatory parameters"+Arrays.toString(errorName),HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = "/doctor")
    public List<Doctor> getDoctor(){
        return doctorService.getDoctor();
    }
    @GetMapping(value = "/doctor/{doctorId}")
    public List<Doctor> getDoctorById(@Nullable@RequestParam String doctor){
        return doctorService.getDoctorById((doctor));
    }
    private Doctor setDoctor(JSONObject json) {
        Doctor doctor=new Doctor();
        int doctorId=(int) json.get("doctorId");
        doctor.setDoctorId(doctorId);
        doctor.setDoctorName(json.getString("doctorName"));
        doctor.setSpecialization(json.getString("specialization"));
        if(json.has("experience")){
            String exp= json.getString("experience");
            doctor.setExperience(exp);
        }
        return doctor;
    }

    private List<String> validDoctor(JSONObject json) {
        List<String> errorList=new ArrayList<>();
        if(!json.has("doctorId")){
            errorList.add("doctorId");
        }
        if(!json.has("doctorName")){
            errorList.add("doctorName");
        }
        if(!json.has("specialization")){
            errorList.add("specialization");
        }
        return errorList;
    }
}
