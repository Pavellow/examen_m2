package com.pavinciguerra.microservice_praticien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableEurekaClient
@EnableRetry
@EnableCircuitBreaker
public class MicroservicePraticienApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicePraticienApplication.class, args);
	}
}