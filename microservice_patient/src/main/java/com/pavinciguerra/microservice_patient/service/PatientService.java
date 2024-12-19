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

    public Optional getPatientById(Long id) {
        return this.repo.findById(id);
    }

    public Patient savePatient(Patient patient) {
        return (Patient) this.repo.save(patient);
    }

    public Optional<Patient> updatePatient(Long id, Patient patientParam) {
        Optional<Patient> patientOpt = this.repo.findById(id);

        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            patient.setNom(patientParam.getNom());
            patient.setPrenom(patientParam.getPrenom());
            patient.setDateNaissance(patientParam.getDateNaissance());
            patient.setEmail(patientParam.getEmail());
            patient.setTelephone(patientParam.getTelephone());
            patient.setAdresse(patientParam.getAdresse());

            return Optional.of(this.repo.save(patient));
        }

        return Optional.empty();
    }

    public void deletePatient(Long id) {
        Optional<Patient> patientOpt = this.repo.findById(id);
        if(patientOpt.isPresent()) {
            this.repo.delete(patientOpt);
        }
    }
}
