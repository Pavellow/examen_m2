package com.pavinciguerra.microservice_praticien.repository;

import com.pavinciguerra.microservice_praticien.model.Praticien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraticienRepository extends JpaRepository<Praticien, Long> {
    Praticien findByNumeroIdentification(String numeroIdentification);
}