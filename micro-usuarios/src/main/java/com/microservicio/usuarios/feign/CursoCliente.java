package com.microservicio.usuarios.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-cursos")
public interface CursoCliente {

	@DeleteMapping("/curso-alumno/{id}")
	public void delCursoAlumnoById(@PathVariable Long id);
}
