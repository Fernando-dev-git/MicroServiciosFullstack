package com.microservice.app.examen.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservice.generic.entity.models.Asignatura;

public interface AsignaturaRepository extends PagingAndSortingRepository<Asignatura, Long>{
	

}
