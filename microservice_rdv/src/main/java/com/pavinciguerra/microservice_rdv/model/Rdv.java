package com.pavinciguerra.microservice_rdv.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idPatient;

    @NotNull
    private Long idPraticien;

    @NotNull
    private LocalDateTime rdvDateTime;

    private String calendrierEventId;
    private String status;
}
