package co.edu.ue.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static co.edu.ue.util.Tools.*;

public class AuthorizationFilterJWT extends BasicAuthenticationFilter {

	public AuthorizationFilterJWT(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(ENCABEZADO);
		System.out.println("Encabezado " + header);
		if (header == null || !header.startsWith(PREFIJO_TOKEN)) {
			chain.doFilter(request, response);
			return;
		} 
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		// Se agrego el security...
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(ENCABEZADO);
		if (token != null) {
			 try {
				// Se procesa el token y se recupera el usuario y los roles.
				Claims claims=Jwts.parserBuilder()
						.setSigningKey(CLAVE.getBytes())
						.build()
						.parseClaimsJws(token.replace(PREFIJO_TOKEN, ""))
						.getBody();
				String user = claims.getSubject();
				Date expiration = claims.getExpiration();
	
	             // Validar que el token no ha expirado
	            if (expiration.before(new Date())) {
	                 throw new JwtException("Token expirado");
	            }
				
				List<String> authorities=(List<String>) claims.get("authorities"); 
				System.out.println("**************" + user);
				System.out.println("Autoridades: " + authorities);
				
				if (user != null) {
					//creamos el objeto con la información del usuario (usuario y roles)
					return new UsernamePasswordAuthenticationToken(user, null,
			                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				}
			
			 } catch (JwtException e) {
	                // Manejo de errores de token
	                throw new RuntimeException("Token no válido: " + e.getMessage());
	         }
		}
		return null;
	}
}
