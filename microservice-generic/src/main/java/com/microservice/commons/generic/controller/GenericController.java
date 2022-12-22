/**
 * 
 */
package com.microservice.commons.generic.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.commons.generic.services.GenericService;

/*
 * Maverick
 */
//@CrossOrigin({"http://localhost:4200"})
public class GenericController <E, S extends GenericService<E>>{
  
  @Autowired
  protected S service;
  
  @GetMapping
  public ResponseEntity<?> allAlumnos() throws Exception{
    return ResponseEntity.ok().body(service.findAll());
  }
 
  @GetMapping("/pagina")
  public ResponseEntity<?> allEntityByPage(Pageable pageable) throws Exception{
    return ResponseEntity.ok().body(service.findAll(pageable));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<?> alumnoById(@PathVariable Long id) throws Exception{
    return ResponseEntity.ok(service.findById(id));
  }
  
  @PostMapping()
  public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result) throws Exception{
	  if(result.hasErrors()){
		  return this.error_msj(result);
	  }
    return ResponseEntity.status(HttpStatus.OK).body(service.save(entity));
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
  
  protected ResponseEntity<?> error_msj(BindingResult result){
	  Map<String, Object> errores = new HashMap<>();
	  result.getFieldErrors().forEach(error ->{
		  errores.put(error.getField(), error.getDefaultMessage());
	  });
	  return ResponseEntity.badRequest().body(errores);
	  
	  
  }

}