package com.microservice.app.examen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.app.examen.repository.AsignaturaRepository;
import com.microservice.app.examen.repository.ExamenRepository;
import com.microservice.commons.generic.services.GenericServiceImpl;
import com.microservice.generic.entity.models.Asignatura;
import com.microservice.generic.entity.models.Examen;


@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen, ExamenRepository> implements ExamenService{

	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	@Override
	public Iterable<Asignatura> findAllAsignaturas() {
		return asignaturaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamensIdsConRespuestasByPreguntasId(Iterable<Long> preguntaIds) {
		return repository.findExamensIdsConRespuestasByPreguntasId(preguntaIds);
	}
	
	
}
