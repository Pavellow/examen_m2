package com.pavinciguerra.microservice_patient.repository;

import com.pavinciguerra.microservice_patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository {

    // on peut ajouter des méthodes scpéficifques si on a
    // vraiment besoin (exemple : grosses jointures ou
    // requêtes très spécifiques
}
