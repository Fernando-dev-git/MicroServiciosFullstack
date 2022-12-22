package com.microservice.curso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.generic.entity.models.service.alumno.Alumno;

@FeignClient(name = "service-usuarios")
public interface AlumnoFeing {
	
	@GetMapping("/alumnos-por-curso")
	public Iterable<Alumno> alumnosPorCursos(@RequestParam Iterable<Long> ids);
	

}
