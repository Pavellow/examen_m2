package com.pavinciguerra.microservice_patient.service;

import com.pavinciguerra.microservice_patient.model.Patient;
import com.pavinciguerra.microservice_patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service métier pour la gestion des patients.
 * Implémente la logique métier et fait le lien entre le contrôleur et le repository.
 */
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repo;

    /**
     * Récupère tous les patients de la base de données.
     * @return Liste de tous les patients
     */
    public List<Patient> getAll() {
        return repo.findAll();
    }

    /**
     * Recherche un patient par son identifiant.
     * @param id Identifiant du patient
     * @return Optional contenant le patient si trouvé
     */
    public Optional<Patient> getPatientById(Long id) {
        return repo.findById(id);
    }

    /**
     * Enregistre un nouveau patient.
     * @param patient Patient à sauvegarder
     * @return Patient sauvegardé avec son ID généré
     */
    public Patient savePatient(Patient patient) {
        return repo.save(patient);
    }

    /**
     * Met à jour les informations d'un patient existant.
     * @param id Identifiant du patient à modifier
     * @param patientParam Nouvelles données du patient
     * @return Optional contenant le patient mis à jour si trouvé
     */
    public Optional<Patient> updatePatient(Long id, Patient patientParam) {
        // Recherche du patient existant
        Optional<Patient> patientOpt = repo.findById(id);

        if (patientOpt.isPresent()) {
            // Mise à jour des champs si le patient existe
            Patient patient = patientOpt.get();
            patient.setNom(patientParam.getNom());
            patient.setPrenom(patientParam.getPrenom());
            patient.setDateNaissance(patientParam.getDateNaissance());
            patient.setEmail(patientParam.getEmail());
            patient.setTelephone(patientParam.getTelephone());
            patient.setAdresse(patientParam.getAdresse());

            // Sauvegarde et retour du patient mis à jour
            return Optional.of(repo.save(patient));
        }

        return Optional.empty();
    }

    /**
     * Supprime un patient de la base de données.
     * @param id Identifiant du patient à supprimer
     */
    public void deletePatient(Long id) {
        // Utilisation de ifPresent pour une suppression conditionnelle
        repo.findById(id).ifPresent(repo::delete);
    }
}