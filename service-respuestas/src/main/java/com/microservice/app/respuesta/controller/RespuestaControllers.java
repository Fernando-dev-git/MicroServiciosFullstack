package com.microservice.app.respuesta.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.app.respuesta.entity.Respuesta;
import com.microservice.app.respuesta.models.ConsultRespuestas;
import com.microservice.app.respuesta.service.RespuestaService;

@RestController
public class RespuestaControllers {

	@Autowired
	private RespuestaService service;
	
	@PostMapping
	public ResponseEntity<?> saveRespuestas(@RequestBody Iterable<Respuesta> respuestas){
		respuestas = ( (List<Respuesta>) respuestas).stream().map(r ->{
			r.setAlumnoId(r.getAlumno().getId());
			r.setPreguntaId(r.getPregunta().getId());
			return r;
		}).collect(Collectors.toList());
		Iterable<Respuesta> respuestaBD = service.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestaBD);
	}
	
	@GetMapping
	public ResponseEntity<?> respuestaByAlumnoByExamen(@RequestBody ConsultRespuestas alumno){
		return ResponseEntity.ok(service.findRespuestaByAlumnoByExamen(alumno.getIdAlumno(), alumno.getIdExamen()));
	}
	
	@GetMapping("alumno/{idAlumno}/examen-respondidos")
	public ResponseEntity<?> idExamenConRespuestaXAlumno(@PathVariable Long idAlumno){
		return ResponseEntity.ok(service.findExamensIdsConRespuestasByAlumno(idAlumno));
	}
}
