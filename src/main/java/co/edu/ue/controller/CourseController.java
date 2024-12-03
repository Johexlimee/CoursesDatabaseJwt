package co.edu.ue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ue.entity.Course;
import co.edu.ue.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name="Cursos", description = "En este controlador se manipulan los datos de los cursos")
//@ExternalDocumentation(description = "Mi Github", url = "https://github.com/Johexlimee")
@RequestMapping("api-courses")
@Validated
public class CourseController {

	@Autowired
	ICourseService service;
	
	@Operation(
			summary = "Consulta todos los cursos",
			description = "Obtiene todos los cursos registrados en la base de datos"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "202",description = "Solitud recibida", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@GetMapping(value="courses-all")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> list = service.listAllCourses();
		//1. Agregar al header nuevos datos
		int cantList = list.size();
		HttpHeaders headers = new HttpHeaders();
		headers.add("cant_elements", String.valueOf(cantList));
		headers.add("test", "hola");
		//2. armamos la respuesta de tipo ResponseEntity
		return new ResponseEntity<List<Course>>(list, headers, HttpStatus.ACCEPTED);
	}
	
	@Operation(
			summary = "Consulta un unico curso",
			description = "Consulta un unico curso buscandolo por ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "202",description = "Solitud recibida", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@GetMapping(value="courses-id")
	public ResponseEntity<Course> getByIdCourses(@Valid @RequestParam int id) {
		return new ResponseEntity<Course>(service.searchByIdCourse(id),HttpStatus.OK);
	}
	
	@Operation(
			summary = "Crea un curso",
			description = "Agrega un nuevo curso a la base de datos"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "409",description = "Se genero un conflicto", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@PostMapping(value="course")
	public ResponseEntity<Void> postCourse(@Valid @RequestBody Course course) {
		if (service.postByCourse(course)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	@Operation(
			summary = "Actualiza un curso",
			description = "Actualiza los datos de un curso por su ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@PutMapping(value="course-id")
	public ResponseEntity<Course>  updateCourse(@Valid @RequestBody Course course, @RequestParam int id) {
		return new ResponseEntity<Course> (service.updateByCourse(course, id),HttpStatus.CREATED);
	}
	
	/*@Operation(summary = "Este endpoint es usado para ELIMINAR un curso de la base de datos")
	@DeleteMapping(value="course-id")
	public ResponseEntity<String> deleteByIdCourse(@Valid @RequestParam int id) {
		service.deleteByIdCourse(id);
		return new ResponseEntity<String> ("Curso eliminado correctamente",HttpStatus.OK);
	}*/
	
	@Operation(
			summary = "Elimina un curso",
			description = "Cambia el estado de un curso de verdadero a falso"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@DeleteMapping(value="course-id")
	public ResponseEntity<Course>  deleteByIdCourse(@Valid @RequestBody Course course, @RequestParam int id) {
		return new ResponseEntity<Course> (service.deleteByIdCourse(course, id),HttpStatus.OK);
	}
}
