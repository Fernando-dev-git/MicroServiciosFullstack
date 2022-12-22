/**
 * 
 */
package com.microservicio.usuarios.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservice.generic.entity.models.service.alumno.Alumno;

/**
 * @author fosanchez
 *
 */
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long>{
	
	@Query("select a from Alumno a where upper(a.nombre) like upper(concat('%', ?1, '%')) or upper(a.apaterno) like upper(concat('%', ?1, '%'))")
	List<Alumno> findByNameOrPaterno(String term);
  
	public Iterable<Alumno> findAllByOrderByIdAsc();
	
	public Page<Alumno> findAllByOrderByIdAsc(Pageable pageable);

}
