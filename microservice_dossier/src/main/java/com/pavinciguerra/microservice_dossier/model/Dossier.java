package com.pavinciguerra.microservice_dossier.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dossiers_medicaux")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "derniere_mise_a_jour")
    private LocalDateTime derniereMiseAJour;
}
