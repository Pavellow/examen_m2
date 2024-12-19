package com.pavinciguerra.microservice_praticien.service;

import com.pavinciguerra.microservice_praticien.model.Praticien;
import com.pavinciguerra.microservice_praticien.repository.PraticienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PraticienService {

    @Autowired
    private PraticienRepository praticienRepository;

    public List<Praticien> findAll() {
        return praticienRepository.findAll();
    }

    public Optional<Praticien> findById(Long id) {
        return praticienRepository.findById(id);
    }

    public Praticien save(Praticien praticien) {
        return praticienRepository.save(praticien);
    }

    public Optional<Praticien> update(Long id, Praticien praticien) {
        if (praticienRepository.existsById(id)) {
            praticien.setId(id);
            return Optional.of(praticienRepository.save(praticien));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (praticienRepository.existsById(id)) {
            praticienRepository.deleteById(id);
            return true;
        }
        return false;
    }
}