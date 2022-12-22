package com.microservice.app.respuesta.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.generic.entity.models.Examen;

@FeignClient(name = "service-examenes")
public interface ExamenClient {
	
	@GetMapping("/{id}")
	public Examen examenById(@PathVariable Long id);
	
	
	@GetMapping("/respondidos/preguntas")
	public List<Long> examenesIdsByPreguntasIdsAnwser(@RequestParam List<Long> preguntaIds);

}
