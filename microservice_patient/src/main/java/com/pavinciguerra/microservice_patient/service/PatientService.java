package com.pavinciguerra.microservice_patient.service;

import com.pavinciguerra.microservice_patient.model.Patient;
import com.pavinciguerra.microservice_patient.repository.PatientRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class PatientService {

    private final PatientRepository repo;

    public List<Patient> getAll() {
        return this.repo.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return this.repo.findById(id);
    }

    public Patient savePatient(Patient patient) {
        return this.repo.save(patient);
    }
}
