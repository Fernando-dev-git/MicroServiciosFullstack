package com.microservice.app.examen.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.app.examen.service.ExamenService;
import com.microservice.commons.generic.controller.GenericController;
import com.microservice.generic.entity.models.Examen;

@RestController
public class ExamenController extends GenericController<Examen, ExamenService> {

	@GetMapping("/respondidos/preguntas")
	public ResponseEntity<?> examenesIdsByPreguntasIdsAnwser(@RequestParam List<Long> preguntaIds){
		return ResponseEntity.ok().body(service.findExamensIdsConRespuestasByPreguntasId(preguntaIds));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> editExamen(@Valid @RequestBody Examen examen,BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.error_msj(result);
		}
		Optional<Examen> temp = service.findById(id);
		if (!temp.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDB = temp.get();
		examenDB.setNombre(examen.getNombre());

		examenDB.getPreguntas().stream().filter(pdb -> !examen.getPreguntas().contains(pdb))
				.forEach(examenDB::delPregunta);
		examenDB.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
	}

	@GetMapping("filtrar/{nombre}")
	public ResponseEntity<?> filtrarExamen(@PathVariable String nombre) {
		return ResponseEntity.ok(service.findByNombre(nombre));
	}
	
	@GetMapping("asignaturas")
	public ResponseEntity<?> findAsignaturas() {
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
	
	
}
