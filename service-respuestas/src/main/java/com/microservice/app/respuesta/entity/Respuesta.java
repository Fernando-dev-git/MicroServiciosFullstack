package com.microservice.app.respuesta.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.microservice.generic.entity.models.Pregunta;
import com.microservice.generic.entity.models.service.alumno.Alumno;

import lombok.Data;


@Document(collection = "respuestas")
@Data
public class Respuesta {

	@Id
	private Long id;
	
	private String respuesta;

	//@Transient
	private Alumno alumno;

	private Long alumnoId;
	
	//@Transient
	private Pregunta pregunta;
	
	private Long preguntaId;
}
