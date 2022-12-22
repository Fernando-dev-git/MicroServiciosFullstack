package com.microservice.curso.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.generic.entity.models.Examen;
import com.microservice.generic.entity.models.service.alumno.Alumno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@AllArgsConstructor
@Getter
@Setter
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String curso;
	
	@Transient
	private List<Alumno> alumnos;
	
	@JsonIgnoreProperties(value = {"curso"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", 
		cascade = CascadeType.ALL, 
		orphanRemoval = true)
	private List<CursoAlumno> cursoAlumnos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	public Curso() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
		this.cursoAlumnos = new ArrayList<>();
	}
	
	@PrePersist
	private void fechaCreacion() {
		this.createAt = new Date();
	}
	
	public void addAlumno(Alumno alumno){
		this.alumnos.add(alumno);
	}
	
	public void delAlumno(Alumno alumno){
		this.alumnos.remove(alumno);
	}
	
	public void addExamen(Examen examen){
		this.examenes.add(examen);
	}
	
	public void removeExamen(Examen examen){
		this.examenes.remove(examen);
	}
	
	public void addCursoAlumno(CursoAlumno cursoalumno){
		this.cursoAlumnos.add(cursoalumno);
	}
	
	public void removeCursoAlumno(CursoAlumno cursoalumno){
		this.cursoAlumnos.remove(cursoalumno);
	}
	

}
