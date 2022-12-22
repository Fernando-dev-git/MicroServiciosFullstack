package com.microservice.curso.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservice.curso.model.entity.Curso;

public interface CursosRepository extends PagingAndSortingRepository<Curso, Long> {
	
	@Query("select c from Curso c join fetch c.cursoAlumnos a where a.alumnoId=?1")
	Curso findCursoByAlumnoId(Long idAlumno);
	
	@Modifying
	@Query("delete from CursoAlumno c where c.alumnoId=?1")
	public void delCursoAlumnoById(Long id);

}
