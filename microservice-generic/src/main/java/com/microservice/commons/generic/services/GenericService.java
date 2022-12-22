package com.microservice.commons.generic.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<E>{
	
	Iterable<E> findAll();
	Page<E> findAll(Pageable pageable);
	Optional<E> findById(Long id);
	E save(E entity);
	void deleteById(Long id);
	
}
