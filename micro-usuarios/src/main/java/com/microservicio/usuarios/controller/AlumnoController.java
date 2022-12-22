/**
 * 
 */
package com.microservicio.usuarios.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.commons.generic.controller.GenericController;
import com.microservice.generic.entity.models.service.alumno.Alumno;
import com.microservicio.usuarios.services.AlumnoService;

/**
 * @author fosanchez
 */
@RestController
public class AlumnoController extends GenericController<Alumno, AlumnoService> {
	
	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> alumnosPorCursos(@RequestParam List<Long> ids){
		return ResponseEntity.ok(service.findAllById(ids));
	}

	@PutMapping()
	public ResponseEntity<?> edit(@Valid @RequestBody Alumno alumno, BindingResult result) {
		if (result.hasErrors()) {
			return this.error_msj(result);
		}
		Optional<Alumno> alumnobd = service.findById(alumno.getId());
		if (!alumnobd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		alumnobd.get().setNombre(alumno.getNombre());
		alumnobd.get().setAmaterno(alumno.getAmaterno());
		alumnobd.get().setApaterno(alumno.getApaterno());
		alumnobd.get().setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(alumno));
	}

	@GetMapping("filtrar/{term}")
	public ResponseEntity<?> findByNameOrApaterno(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNameOrPaterno(term));
	}

	@PostMapping("crear/foto")
	public ResponseEntity<?> saveWhitPhoto(@Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws Exception {
		if (!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.save(alumno, result);
	}

	@PutMapping("foto/{idAlumno}")
	public ResponseEntity<?> editWhitPthoto(@PathVariable Long idAlumno, @Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {
		if (result.hasErrors()) {
			return this.error_msj(result);
		}
		Optional<Alumno> alumnobd = service.findById(idAlumno);
		if (!alumnobd.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		alumnobd.get().setNombre(alumno.getNombre());
		alumnobd.get().setAmaterno(alumno.getAmaterno());
		alumnobd.get().setApaterno(alumno.getApaterno());
		alumnobd.get().setEmail(alumno.getEmail());
		if (!archivo.isEmpty()) {
			alumnobd.get().setFoto(archivo.getBytes());
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.save(alumno));
	}

	@GetMapping("uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		Optional<Alumno> alumno = service.findById(id);
		if (!alumno.isPresent() || alumno.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource img = new ByteArrayResource(alumno.get().getFoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
	}
	
	
}