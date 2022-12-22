package com.microservice.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.commons.generic.services.GenericServiceImpl;
import com.microservice.curso.client.AlumnoFeing;
import com.microservice.curso.client.RespuestaFeing;
import com.microservice.curso.model.entity.Curso;
import com.microservice.curso.repository.CursosRepository;
import com.microservice.generic.entity.models.service.alumno.Alumno;

@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, CursosRepository> implements CursoService {

	@Autowired 
	private RespuestaFeing respuestaClient;
	
	@Autowired
	private AlumnoFeing clientAlumno;
	
	@Transactional(readOnly = true)
	@Override
	public Curso findCursoByAlumnoId(Long idAlumno) {
		return repository.findCursoByAlumnoId(idAlumno);
	}

	@Override
	public Iterable<Long> idExamenConRespuestaXAlumno(Long idAlumno) {
		return respuestaClient.idExamenConRespuestaXAlumno(idAlumno);
	}

	@Override
	public Iterable<Alumno> alumnosPorCursos(Iterable<Long> ids) {
		return clientAlumno.alumnosPorCursos(ids);
	}

	@Override
	@Transactional
	public void delCursoAlumnoById(Long id) {
		repository.delCursoAlumnoById(id);
	}


}
