/**
 * 
 */
package com.microservice.generic.entity.models.service.alumno;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author fosanchez
 *
 */
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apaterno;

	@NotEmpty
	private String amaterno;

	@NotEmpty
	@Email
	private String email;

	@Lob
	@JsonIgnore
	private byte[] foto;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fhCreacion;

	public Integer getFotoHashCode(){
		return (this.foto != null ) ? this.foto.hashCode() : null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno temp = (Alumno) obj;
		return this.id != null && this.id.equals(temp.getId());
	}

	@PrePersist
	private void fechaCreacion() {
		this.fhCreacion = new Date();
	}

}
