package com.microservice.app.respuesta.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.app.respuesta.entity.Respuesta;
import com.microservice.app.respuesta.feign.ExamenClient;
import com.microservice.app.respuesta.repository.RespuestaRepository;
import com.microservice.generic.entity.models.Examen;
import com.microservice.generic.entity.models.Pregunta;

@Service 
public class RespuestaServiceImpl implements RespuestaService{
	
	@Autowired 
	private RespuestaRepository repository;
	
	@Autowired
	private ExamenClient examenclient;

	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	@Override
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen) {
		/*
		Examen examen = examenclient.examenById(idExamen);
		List<Pregunta> preguntas = examen.getPreguntas();
		List<Long> preguntasId = preguntas.stream().map(p -> p.getId()).collect(Collectors.toList());
		List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByPreguntasIds(idAlumno, preguntasId);
		respuestas = respuestas.stream()
				.map(r -> {
					preguntas.forEach(p ->{
						if(p.getId()== r.getPreguntaId()) {
							r.setPregunta(p);
						}
					});
					return r;
				}).collect(Collectors.toList());
		return respuestas;
		*/
		List<Respuesta> respuestas = (List<Respuesta>) repository.findRespuestaByAlumnoByExamen(idAlumno, idExamen);
		return respuestas;
	}

	@Override
	public Iterable<Long> findExamensIdsConRespuestasByAlumno(Long idAlumno) {
	/*  List<Respuesta> respuestAlumno = (List<Respuesta>) repository.findByAlumnoId(idAlumno);
		List<Long> examenIds = Collections.emptyList();
		
		if(!respuestAlumno.isEmpty()) {
			List<Long> preguntaIds = respuestAlumno.stream().map(r -> r.getPreguntaId())
					.collect(Collectors.toList());
			examenIds = examenclient.examenesIdsByPreguntasIdsAnwser(preguntaIds);	
		}
		return examenIds;*/
		List<Respuesta> respuestAlumno = (List<Respuesta>) repository.findExamenIdsConRespuestaByAlumno(idAlumno);
		List<Long> examenIds = respuestAlumno.stream()
				.map(r -> r.getPregunta().getExamen().getId())
				.distinct()
				.collect(Collectors.toList());
		return examenIds;
	}

	@Override
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
		return repository.findByAlumnoId(alumnoId);
	}


}
