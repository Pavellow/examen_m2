package com.pavinciguerra.microservice_rdv.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.pavinciguerra.microservice_rdv.repository.RdvRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;

@Service
public class RdvService {
    private final RdvRepository repo;

    // là c'est la merde
    private final Calendar googleCalendar;

    public RdvService(RdvRepository repos) throws Exception {
        this.repo = repos;
        this.googleCalendar = initGoogleCalendar();
    }

    private Calendar initGoogleCalendar() throws Exception {
        // Configuration Google Calendar
        // Note: Dans un environnement réel, utilisez la configuration appropriée
        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                null)
                .setApplicationName("Medical-Appointments")
                .build();
    }


}
