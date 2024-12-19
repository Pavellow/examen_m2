package com.pavinciguerra.microservice_patient.controller;

import com.pavinciguerra.microservice_patient.model.Patient;
import com.pavinciguerra.microservice_patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.OnOpen;
import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/patient")
@Tag(name = "Patient", description = "API gestion patient")
public class PatientController {

    private final PatientService service;

    @GetMapping
    @Operation(summary = "Récupère tous les patients")
    public List<Patient> getAllPatients() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère un patient selon son ID")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return this.service.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crée un patient dans la BDD")
    public Patient createPatient(@RequestBody Patient patient) {
        return this.service.savePatient(patient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un patient dans la BDD")
    public ResponseEntity<Optional<Patient>> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(this.service.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime un patient de la BDD")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        this.service.deletePatient(id);
        return ResponseEntity.ok().build();
    }

}
