package com.microservice.app.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.microservice.generic.entity.models"})
public class MicroserviceExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceExamenApplication.class, args);
	}

}
