package co.edu.ue.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import static co.edu.ue.util.Tools.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name="Autentificacion del Usuario", description = "En este controlador se valida que el token del usuario para poder hacer uso de los endpoints de cursos")
@CrossOrigin(origins = "*")
@RestController
public class AuthController {

	AuthenticationManager authManager;

	public AuthController(AuthenticationManager authManager) {
		super();
		this.authManager = authManager;
	}
	
	@Operation(
			summary = "Inicio de sesion",
			description = "Al iniciar sesion, se debe copiar el token y pegarlo en el boton de Authorize"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"), 
			@ApiResponse(responseCode = "401",description = "Credenciales erroneas o inexistentes", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@PostMapping(value = "login", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> login(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
		
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(user, pwd));
			System.out.println("Controller Usuario autenticado: " + authentication.getName());
	        System.out.println("Controller Autoridades: " + authentication.getAuthorities());
			return new ResponseEntity<>(getToken(authentication), HttpStatus.OK);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("Error de autenticacion: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		
	}

	private String getToken(Authentication authentication) {
	    List<String> authorities = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.toList());
 
	    System.out.println("*****************Controller***********************");
	    System.out.println("Autoridades: " + authorities);
	    System.out.println("****************************************");
	    String token = Jwts.builder()
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setSubject(authentication.getName())
	        .claim("authorities", authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
	        .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
	        .compact();
	    return token;
	}
}
