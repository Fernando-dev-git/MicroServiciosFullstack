package com.microservice.app.respuesta.service;

import com.microservice.app.respuesta.entity.Respuesta;

public interface RespuestaService {
	
	
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen);
	
	public Iterable<Long> findExamensIdsConRespuestasByAlumno(Long idAlumno);
	
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId);

}
