package com.pavinciguerra.microservice_rdv.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pavinciguerra.microservice_rdv.model.Rdv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@Slf4j
public class GoogleCalendarService {
    private final Calendar googleCalendar;

    @HystrixCommand(fallbackMethod = "handleCalendarFailure")
    @Retryable
    public String createCalendarEvent(Rdv rdv) {}
}
