package com.pavinciguerra.microservice_rdv.service;

import com.pavinciguerra.microservice_rdv.repository.RdvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;

@RequiredArgsConstructor
@Service
public class RdvService {

    @Autowired
    private final RdvRepository repo;

    // TODO : finir le service local

}
