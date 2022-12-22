package com.microservice.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.microservice.generic.entity.models",
			 "com.microservice.curso.model.entity"})
@EnableFeignClients
public class ServiceCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCursosApplication.class, args);
	}

}
