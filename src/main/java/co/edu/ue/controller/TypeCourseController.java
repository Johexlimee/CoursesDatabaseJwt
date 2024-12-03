package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.TypeCourse;
import co.edu.ue.service.ITypeCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Tag(name = "Tipos de Cursos", description = "En este controlador se manipulan los datos de todos los tipos de cursos")
@RequestMapping("api-types")
@Validated
public class TypeCourseController {
	
	@Autowired
	ITypeCourseService service;
	
	@Operation(
			summary = "Consulta todos los tipos de curso",
			description = "Obtiene todos los tipos de curso registrados en la base de datos"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "202",description = "Solitud recibida", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@GetMapping("types-all")
	public ResponseEntity<List<TypeCourse>> getAllTypes() {
		List<TypeCourse> list = service.listAllTypes();
		return new ResponseEntity<List<TypeCourse>>(list, HttpStatus.ACCEPTED);
	}
	
	@Operation(
			summary = "Consulta un unico tipo de curso",
			description = "Consulta un unico tipo de curso buscandolo por ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "202",description = "Solitud recibida", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@GetMapping(value="types-id")
	public ResponseEntity<TypeCourse> getTypeById(@Valid @RequestParam int id) {
		return new ResponseEntity<TypeCourse>(service.searchByIdType(id),HttpStatus.OK);
	}
	
	@Operation(
			summary = "Crea un tipo de curso",
			description = "Agrega un nuevo tipo de curso a la base de datos"	
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "409",description = "Se genero un conflicto", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@PostMapping(value="type")
	public ResponseEntity<Void> postType(@Valid @RequestBody TypeCourse tCourse) {
		if (service.postByType(tCourse)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	@Operation(
			summary = "Actualiza un tipo de curso",
			description = "Actualiza los datos de un tipo de curso por su ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@PutMapping(value="type-id")
	public ResponseEntity<TypeCourse> updateType(@Valid @RequestBody TypeCourse tCourse, @RequestParam int id) {
		return new ResponseEntity<TypeCourse> (service.updateByType(tCourse, id),HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Elimina un tipo de curso",
			description = "Cambia el estado de un tipo de curso de verdadero a falso"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@DeleteMapping(value="type-id")
	public ResponseEntity<TypeCourse>  deleteType(@Valid @RequestBody TypeCourse tCourse, @RequestParam int id) {
		return new ResponseEntity<TypeCourse> (service.deleteByIdType(tCourse, id),HttpStatus.OK);
	}

}
