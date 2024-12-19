package com.pavinciguerra.microservice_rdv.controller;

import com.pavinciguerra.microservice_rdv.model.Rdv;
import com.pavinciguerra.microservice_rdv.service.RdvService;
import com.pavinciguerra.microservice_rdv.service.GoogleCalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rdv")
@RequiredArgsConstructor
@Tag(name = "RDV Controller", description = "API de gestion des rendez-vous")
public class RdvController {

    private final RdvService rdvService;
    private final GoogleCalendarService googleCalendarService;

    @PostMapping
    @Operation(summary = "Créer un nouveau rendez-vous et l'ajouter au calendrier Google")
    @ApiResponse(responseCode = "201", description = "Rendez-vous créé avec succès")
    public ResponseEntity<Rdv> creerRdv(@RequestBody Rdv rdv) {
        Rdv nouveauRdv = rdvService.creerRdv(rdv);

        String eventId = googleCalendarService.createCalendarEvent(nouveauRdv);
        nouveauRdv.setExternalCalendarEventId(eventId);

        return new ResponseEntity<>(rdvService.updateRdv(nouveauRdv.getId(), nouveauRdv), HttpStatus.CREATED);
    }

    @GetMapping("/{id]")
    @Operation(summary = "obtient rdv avec id")
    @ApiResponse(responseCode = "200", description = "rdv trouvé") // je le fais plus àça me fait chier
    @ApiResponse(responseCode = "404", description = "pas trouvé")
    public ResponseEntity<Rdv> getRdv(@PathVariable Long id) {
        return ResponseEntity.ok(rdvService.getRdvById(id));
    }

    @GetMapping
    @Operation(summary = "récupère tous les rdv")
    public ResponseEntity<List<Rdv>> getAllRdv() {
        return ResponseEntity.ok(rdvService.getAllRdv());
    }

    @GetMapping
    @Operation(summary = "récupérer tous les rdv d'un patient")
    public ResponseEntity<List<Rdv>> getRdvByPatient(@PathVariable Long idPatient) {
        return ResponseEntity.ok(rdvService.getRdvByPatient(idPatient));
    }

    // j'en peux plus c'est la même cjhose à chaque ofis
    // TODO : se renseigner pour avoir une possibilté de déclarrer des routes avec une méthode commune (même si c'est pas maintenable)

    @GetMapping("/praticien/{idPraticien}")
    @Operation(summary = "récupérer tous les rdv d'un praticien")
    public ResponseEntity<List<Rdv>> getRdvByPraticien(@PathVariable Long idPraticien) {
        return ResponseEntity.ok(rdvService.getRdvByPraticien(idPraticien));
    }

    @PutMapping("/{id}")
    @Operation(summary = "mettre à jour un rdv local et sur google calendar")
    public ResponseEntity<Rdv> updateRdv(@PathVariable Long id, @RequestBody Rdv rdv) {
        Rdv updatedRdv = rdvService.updateRdv(id, rdv);
        if (updatedRdv.getExternalCalendarEventId() != null) {
            googleCalendarService.updateCalendarEvent(updatedRdv.getExternalCalendarEventId(), updatedRdv);
        } else {
            String eventId = googleCalendarService.createCalendarEvent(updatedRdv);
            updatedRdv.setExternalCalendarEventId(eventId);
            updatedRdv = rdvService.updateRdv(id, updatedRdv);
        }

        return ResponseEntity.ok(updatedRdv);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "del un rdv et son event sur google calendar")
    public ResponseEntity<Void> deleteRdv(@PathVariable Long id) {
        Rdv rdv = rdvService.getRdvById(id);
        if (rdv.getExternalCalendarEventId() != null) {
            googleCalendarService.deleteCalendarEvent(rdv.getExternalCalendarEventId());
        }

        rdvService.deleteRdv(id);
        return ResponseEntity.noContent().build();
    }
}