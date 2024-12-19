package com.pavinciguerra.microservice_patient.controller;

import com.pavinciguerra.microservice_patient.model.Patient;
import com.pavinciguerra.microservice_patient.service.PatientService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService service;

    @GetMapping
    public List<Patient> getAllPatients() {
        return this.service.getAll();
    }

    // TODO : trouver un refactor à cette méthode de merde
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return this.service.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return this.service.savePatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Patient>> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(this.service.updatePatient(id, patient));
    }

}
