package com.pavinciguerra.microservice_rdv.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Value;
import com.google.api.services.calendar.Calendar;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pavinciguerra.microservice_rdv.model.Rdv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.client.json.JsonFactory;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class GoogleCalendarService {

    @Value("${google.calendar.api.key}")
    private String cleApi;

    private Calendar googleCalendar;
    private static final String OBJET_RDV = "RDV MEDICAL";
    private static final int DUREE_RDV = 30; // minutes
    private static final String ID_CALENDRIER = "primary";

    public GoogleCalendarService(Calendar googleCalendar) {
        this.googleCalendar = new Calendar.Builder(
                new NetHttpTransport(),
                new GsonFactory(),
                request -> {
                    // bearer de merde
                    request.getHeaders().set("Authorization", "Bearer " + cleApi);
                })
                .setApplicationName("Medical Office App")
                .build();

    }

    @HystrixCommand(fallbackMethod = "handleCalendarFailure")
    @Retryable
    public String createCalendarEvent(Rdv rdv) {
        try {
            Event event = this.createEventFromRdv(rdv);
            event = googleCalendar.events()
                    .insert(ID_CALENDRIER, event)
                    .execute();

            log.info("Événement créé avec succès. ID: " + event.getId());
            return event.getId();
        } catch (IOException e) {
            log.error("Erreur lors de la création de l'événement dans Google Calendar", e);
            throw new RuntimeException("Erreur lors de la création de l'événement", e);
        }
    }

    private Event createEventFromRdv(Rdv rdv) {
        LocalDateTime dateDebut = rdv.getRdvDateTime();
        return new Event()
                .setSummary(OBJET_RDV)
                .setDescription(this.formatDescription(rdv))
                .setStart(this.createEventDateTime(dateDebut))
                .setEnd(this.createEventDateTime(dateDebut.plusMinutes(DUREE_RDV)));
    }

    private String formatDescription(Rdv rdv) {
        return String.format("RDV patient: %d, praticien: %d",
                rdv.getIdPatient(),
                rdv.getIdPraticien());
    }

    private EventDateTime createEventDateTime(LocalDateTime dateTime) {
        return new EventDateTime().setDateTime(new DateTime(dateTime.toString()));
    }

    // TODO : finir ce callback de con
    public String handleCalendarFailure() {
        return "a";
    }
}
