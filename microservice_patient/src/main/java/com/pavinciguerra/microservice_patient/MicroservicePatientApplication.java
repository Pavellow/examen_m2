package com.pavinciguerra.microservice_patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableRetry
public class MicroservicePatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePatientApplication.class, args);
	}

}
