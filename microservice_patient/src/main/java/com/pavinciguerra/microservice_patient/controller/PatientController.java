package com.pavinciguerra.microservice_patient.controller;

import com.pavinciguerra.microservice_patient.model.Patient;
import com.pavinciguerra.microservice_patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.OnOpen;
import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des patients.
 * Fournit les endpoints CRUD pour l'entité Patient.
 */
@RestController
@RequestMapping("/api/patient")
@Tag(name = "Patient", description = "API gestion patient")
@RequiredArgsConstructor // Préférable à @Data pour un contrôleur
public class PatientController {

    private final PatientService service;

    /**
     * Récupère la liste complète des patients.
     * @return Liste de tous les patients enregistrés
     */
    @GetMapping
    @Operation(summary = "Récupère tous les patients")
    public List<Patient> getAllPatients() {
        return service.getAll();
    }

    /**
     * Récupère un patient spécifique par son identifiant.
     * @param id Identifiant unique du patient
     * @return Le patient trouvé ou une réponse 404 si non trouvé
     */
    @GetMapping("/{id}")
    @Operation(summary = "Récupère un patient selon son ID")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return service.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau patient dans la base de données.
     * @param patient Les données du patient à créer
     * @return Le patient créé avec son ID généré
     */
    @PostMapping
    @Operation(summary = "Crée un patient dans la BDD")
    public Patient createPatient(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    /**
     * Met à jour les informations d'un patient existant.
     * @param id Identifiant du patient à modifier
     * @param patient Nouvelles données du patient
     * @return Le patient mis à jour ou une réponse vide si non trouvé
     */
    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un patient dans la BDD")
    public ResponseEntity<Optional<Patient>> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(service.updatePatient(id, patient));
    }

    /**
     * Supprime un patient de la base de données.
     * @param id Identifiant du patient à supprimer
     * @return Réponse vide avec statut 200 si succès
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime un patient de la BDD")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}