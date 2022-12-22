package com.microservicio.usuarios.services;

import java.util.List;

import com.microservice.commons.generic.services.GenericService;
import com.microservice.generic.entity.models.service.alumno.Alumno;

public interface AlumnoService extends GenericService<Alumno> {

	List<Alumno> findByNameOrPaterno(String term);
	
	Iterable<Alumno> findAllById(Iterable<Long> ids);

	public void delCursoAlumnoById(Long id);
}
