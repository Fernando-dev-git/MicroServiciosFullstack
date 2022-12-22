package com.microservice.curso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-respuestas/api/respuesta/")
public interface RespuestaFeing {
	
	@GetMapping("alumno/{idAlumno}/examen-respondidos")
	public Iterable<Long> idExamenConRespuestaXAlumno(@PathVariable Long idAlumno);

}
