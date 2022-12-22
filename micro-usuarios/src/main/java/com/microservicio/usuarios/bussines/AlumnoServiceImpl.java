/**
 * 
 */
package com.microservicio.usuarios.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.commons.generic.services.GenericServiceImpl;
import com.microservice.generic.entity.models.service.alumno.Alumno;
import com.microservicio.usuarios.feign.CursoCliente;
import com.microservicio.usuarios.repository.AlumnoRepository;
import com.microservicio.usuarios.services.AlumnoService;

/**
 * @author fosanchez
 *
 */
@Service
public class AlumnoServiceImpl extends GenericServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Autowired
	private CursoCliente clientCurso;

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNameOrPaterno(String term) {
		return repository.findByNameOrPaterno(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public void delCursoAlumnoById(Long cursoAlumnoId) {
		clientCurso.delCursoAlumnoById(cursoAlumnoId);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
		this.delCursoAlumnoById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return repository.findAllByOrderByIdAsc();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Alumno> findAll(Pageable pageable) {
		return repository.findAllByOrderByIdAsc(pageable);
	}


}
