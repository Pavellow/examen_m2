package com.pavinciguerra.microservice_praticien.controller;

import com.pavinciguerra.microservice_praticien.model.Praticien;
import com.pavinciguerra.microservice_praticien.service.PraticienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/praticiens")
@Tag(name = "Praticien", description = "API de gestion des praticiens")
public class PraticienController {

    @Autowired
    private PraticienService praticienService;

    @GetMapping
    @Operation(summary = "Liste tous les praticiens")
    public List<Praticien> getAllPraticiens() {
        return praticienService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère un praticien par son ID")
    public ResponseEntity<Praticien> getPraticienById(@PathVariable Long id) {
        return praticienService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crée un nouveau praticien")
    public Praticien createPraticien(@RequestBody Praticien praticien) {
        return praticienService.save(praticien);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un praticien")
    public ResponseEntity<Praticien> updatePraticien(@PathVariable Long id, @RequestBody Praticien praticien) {
        return praticienService.update(id, praticien)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime un praticien")
    public ResponseEntity<Void> deletePraticien(@PathVariable Long id) {
        if (praticienService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}