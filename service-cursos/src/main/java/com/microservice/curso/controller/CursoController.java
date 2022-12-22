package com.microservice.curso.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.commons.generic.controller.GenericController;
import com.microservice.curso.model.entity.Curso;
import com.microservice.curso.model.entity.CursoAlumno;
import com.microservice.curso.service.CursoService;
import com.microservice.generic.entity.models.Examen;
import com.microservice.generic.entity.models.service.alumno.Alumno;

@RestController
public class CursoController extends GenericController<Curso, CursoService> {

	@Value("${config.balanceador.test:default}")
	private String balancerTest;

	@GetMapping
	@Override
	public ResponseEntity<?> allAlumnos() {
		List<Curso> cursos = ((List<Curso>) service.findAll()).stream().map(c -> {
			c.getCursoAlumnos().forEach(ca -> {
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				c.addAlumno(alumno);
			});
			return c;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(cursos);
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> alumnoById(@PathVariable Long id) throws Exception {
		Optional<Curso> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = o.get();
		if (!curso.getCursoAlumnos().isEmpty()) {
			List<Long> ids = curso.getCursoAlumnos().stream().map(e -> e.getAlumnoId()).collect(Collectors.toList());
			curso.setAlumnos((List<Alumno>) service.alumnosPorCursos(ids));
		}
		return ResponseEntity.ok().body(curso);
	}

	@GetMapping("/pagina")
	@Override
	public ResponseEntity<?> allEntityByPage(Pageable pageable) throws Exception {
		Page<Curso> cursos = service.findAll(pageable).map(curso -> {
			curso.getCursoAlumnos().forEach(ca ->{
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				curso.addAlumno(alumno);
			});
			return curso;
		});
		return ResponseEntity.ok().body(cursos);
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result) {
		if (result.hasErrors()) {
			return this.error_msj(result);
		}
		Optional<Curso> obd = service.findById(curso.getId());
		if (!obd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		obd.get().setCurso(curso.getCurso());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obd.get()));
	}

	@PutMapping("{idCurso}/add-alumnos")
	public ResponseEntity<?> addAlumnoInCurso(@RequestBody List<Alumno> alumnos, @PathVariable Long idCurso) {
		Optional<Curso> bd = service.findById(idCurso);
		if (!bd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = bd.get();
		alumnos.forEach(a -> {
			CursoAlumno ca = new CursoAlumno();
			ca.setAlumnoId(a.getId());
			ca.setCurso(curso);
			curso.addCursoAlumno(ca);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
	}

	@DeleteMapping("{idCurso}/alumno")
	public ResponseEntity<?> removeAlumnoInCurso(@RequestBody Alumno alumno, @PathVariable Long idCurso) {
		Optional<Curso> bd = service.findById(idCurso);
		if (!bd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = bd.get();
		CursoAlumno cursoAlumno = new CursoAlumno();
		cursoAlumno.setAlumnoId(alumno.getId());
		curso.removeCursoAlumno(cursoAlumno);
		return ResponseEntity.status(HttpStatus.OK).body(service.save(curso));
	}

	@GetMapping("alumno/{id}")
	ResponseEntity<?> findCursoByIdAlumno(@PathVariable long id) {
		Curso curso = service.findCursoByAlumnoId(id);
		if (curso != null) {
			List<Long> examensIds = (List<Long>) service.idExamenConRespuestaXAlumno(id);

			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if (examensIds.contains(examen.getId())) {
					examen.setRespondido(Boolean.TRUE);
				}
				return examen;
			}).collect(Collectors.toList());
			curso.setExamenes(examenes);
		}
		return ResponseEntity.ok(curso);
	}

	/* Method theExamen */
	@PutMapping("{idCurso}/agregar/examen")
	public ResponseEntity<?> addExamen(@RequestBody List<Examen> examenes, @PathVariable Long idCurso) {
		Optional<Curso> bd = service.findById(idCurso);
		if (!bd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = bd.get();
		examenes.forEach(curso::addExamen);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));
	}

	@DeleteMapping("{idCurso}/remove/examen")
	public ResponseEntity<?> removeExamen(@RequestBody Examen examen, @PathVariable Long idCurso) {
		Optional<Curso> bd = service.findById(idCurso);
		if (!bd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = bd.get();
		curso.removeExamen(examen);
		return ResponseEntity.status(HttpStatus.OK).body(service.save(curso));
	}

	@DeleteMapping("/curso-alumno/{id}")
	public ResponseEntity<?> delCursoAlumnoById(@PathVariable Long id){
		service.delCursoAlumnoById(id);
		return ResponseEntity.noContent().build();
	}

}
