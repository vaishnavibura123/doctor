package com.example.Doctor.service;

import com.example.Doctor.Dao.DoctorRepo;
import com.example.Doctor.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;

    public Doctor saveDoctor(Doctor doctor1) {
        String doctorName=doctor1.getDoctorName();
        doctorName="Dr."+doctorName;
        doctor1.setDoctorName(doctorName);
        return doctorRepo.save(doctor1);

    }

    public List<Doctor> getDoctor() {
        return doctorRepo.findAll();

    }

    public List<Doctor> getDoctorById(String doctorId) {
        List<Doctor> doctorList;
        if(null!=doctorId){
            doctorList=new ArrayList<>();
            doctorList.add(doctorRepo.findById(Integer.valueOf(doctorId)).get());

        }
        else{
            doctorList=doctorRepo.findAll();
        }
        return doctorList;
    }
}
