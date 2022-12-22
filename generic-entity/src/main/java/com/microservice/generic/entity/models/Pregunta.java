/**
 * 
 */
package com.microservice.generic.entity.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zerialkiller
 *
 */

@Entity
@Table(name = "preguntas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	@JsonIgnoreProperties(value = {"preguntas"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examen_id")
	private Examen examen;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@PrePersist
	public void fecha(){
		this.fechaCreacion = new Date();
	}
	
	@Override
	 public boolean equals(Object obj){
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Pregunta)) {
			return false;
		}
		Pregunta p = (Pregunta)obj;
		
		return this.id != null && this.id.equals(p.getId());
	} 
	

}
