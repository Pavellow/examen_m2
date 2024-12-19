package com.pavinciguerra.microservice_praticien.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Praticien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String specialite;
    private String email;
    private String telephone;
    private String numeroIdentification;  // Numéro RPPS ou équivalent
}