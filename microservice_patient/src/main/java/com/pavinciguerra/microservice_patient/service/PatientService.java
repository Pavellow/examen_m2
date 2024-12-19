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
        return (Patient) this.repo.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientParam) throws Exception {
        Patient patient = this.getPatientById(id).orElseThrow();
        patient.setNom(patientParam.getNom());
        patient.setPrenom(patientParam.getPrenom());
        patient.setDateNaissance(patientParam.getDateNaissance());
        patient.setEmail(patientParam.getEmail());
        patient.setTelephone(patientParam.getTelephone());
        patient.setAdresse(patientParam.getAdresse());

        return this.repo.save(patient);
    }
}
