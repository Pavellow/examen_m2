package com.pavinciguerra.microservice_rdv.repository;

import com.pavinciguerra.microservice_rdv.model.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Long> {
    List<Rdv> findByIdPatient(Long idPatient);
    List<Rdv> findByIdPraticien(Long idPraticien);
}
