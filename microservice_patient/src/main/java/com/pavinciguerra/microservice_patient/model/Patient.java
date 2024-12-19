package com.pavinciguerra.microservice_patient.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private LocalDate dateNaissance;

    @Column(unique = true)
    private String email;

    private String telephone;

    private String adresse;
}
