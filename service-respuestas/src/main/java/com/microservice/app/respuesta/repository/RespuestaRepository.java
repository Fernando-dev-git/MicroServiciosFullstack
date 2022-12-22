package com.microservice.app.respuesta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.microservice.app.respuesta.entity.Respuesta;


public interface RespuestaRepository extends MongoRepository<Respuesta, String>{
	
	@Query("{ 'alumnoId': ?0, 'preguntaId': {$in: ?1} }")
	public Iterable<Respuesta> findRespuestaByAlumnoByPreguntasIds(Long alumnoId, Iterable<Long> preguntasIds);
	
	@Query("{ 'alumnoId': ?0 }")
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId);
	
	@Query(" {'alumnoId': ?0, 'pregunta.examen.id': ?1 } ")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen);
	
	@Query( value = "{ 'alumnoId': ?0 }", fields = " {'pregunta.examen.id': 1} ")
	public Iterable<Respuesta> findExamenIdsConRespuestaByAlumno(Long alumnoId);
	

	/*
	 @Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e"
			+ " where r.alumnoId = ?1 and e.id=?2") 
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long idAlumno, Long idExamen);
	*/
	
	/***
	 * @author zerialkiller
	 * @param Long 
	 * @return Iterable<Long>
	 */
	/*
	 @Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
	public Iterable<Long> findExamensIdsConRespuestasByAlumno(Long idAlumno);
	*/
	
}
