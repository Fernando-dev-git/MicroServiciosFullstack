package com.microservice.app.examen.service;

import java.util.List;

import com.microservice.commons.generic.services.GenericService;
import com.microservice.generic.entity.models.Asignatura;
import com.microservice.generic.entity.models.Examen;

public interface ExamenService extends GenericService<Examen> {
	
	
	List<Examen> findByNombre(String nombre);
	
	Iterable<Asignatura> findAllAsignaturas();
	
	public Iterable<Long> findExamensIdsConRespuestasByPreguntasId(Iterable<Long> preguntaIds);

}
