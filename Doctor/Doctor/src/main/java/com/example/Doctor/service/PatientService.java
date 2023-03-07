package com.example.Doctor.service;

import com.example.Doctor.Dao.DoctorRepo;
import com.example.Doctor.Dao.PatientRepo;
import com.example.Doctor.model.Doctor;
import com.example.Doctor.model.Patient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    DoctorRepo doctorRepo;
    public Patient setPatient(JSONObject jsonObject) {
        Patient patient=new Patient();
        patient.setPatientId(jsonObject.getInt("patientId"));
        patient.setPatientName(jsonObject.getString("patientName"));
        patient.setAge(jsonObject.getInt("age"));
        patient.setDiseaseType(jsonObject.getString("diseaseType"));
        patient.setPhoneNo(jsonObject.getString("phoneNo"));
        Doctor doctor=doctorRepo.findById(jsonObject.getInt("doctorId")).get();
        patient.setDoctorId(doctor);
        return patient;
    }

    public void savePatient(Patient patient1) {
        patientRepo.save(patient1);
    }

    public List<Patient> getPatient(Integer doctorId, Integer patientId) {
        List<Patient> patientList=new ArrayList<>();
        if(doctorId==null&& patientId==null){
            return patientRepo.findAll();
        } else if (doctorId==null) {
            patientList.add(patientRepo.findById(patientId).get());
        } else if (patientId==null) {
            Doctor doctor=doctorRepo.findById(doctorId).get();
        }
        return patientList;
    }
}
