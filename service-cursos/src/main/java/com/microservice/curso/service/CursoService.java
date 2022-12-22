package com.microservice.curso.service;

import com.microservice.commons.generic.services.GenericService;
import com.microservice.curso.model.entity.Curso;
import com.microservice.generic.entity.models.service.alumno.Alumno;

public interface CursoService extends GenericService<Curso>{
	
	Curso findCursoByAlumnoId(Long idAlumno);
	
	public Iterable<Long> idExamenConRespuestaXAlumno(Long idAlumno);
	
	public Iterable<Alumno> alumnosPorCursos(Iterable<Long> ids);
	
	public void delCursoAlumnoById(Long id);

}
