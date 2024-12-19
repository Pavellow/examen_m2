package com.pavinciguerra.microservice_rdv.service;

import com.pavinciguerra.microservice_rdv.model.Rdv;
import com.pavinciguerra.microservice_rdv.repository.RdvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RdvService {

    @Autowired
    private final RdvRepository repo;

    public Rdv creerRdv(Rdv rdv) {
        return this.repo.save(rdv);
    }

    public Rdv getRdvById(Long id) {
        return this.repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RDV non trouvé avec l'id: " + id));
    }

    public List<Rdv> getAllRdv() {
        return this.repo.findAll();
    }

    public List<Rdv> getRdvByPatient(Long idPatient) {
        return this.repo.findByIdPatient(idPatient);
    }

    public List<Rdv> getRdvByPraticien(Long idPraticien) {
        return this.repo.findByIdPraticien(idPraticien);
    }

    public Rdv updateRdv(Long id, Rdv rdvDetails) {
        Rdv rdv = getRdvById(id);
        rdv.setIdPatient(rdvDetails.getIdPatient());
        rdv.setIdPraticien(rdvDetails.getIdPraticien());
        rdv.setRdvDateTime(rdvDetails.getRdvDateTime());

        return this.repo.save(rdv);
    }

    public void deleteRdv(Long id) {
        if (!this.repo.existsById(id)) {
            throw new EntityNotFoundException("RDV non trouvé avec l'id: " + id);
        }
        this.repo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return this.repo.existsById(id);
    }
}