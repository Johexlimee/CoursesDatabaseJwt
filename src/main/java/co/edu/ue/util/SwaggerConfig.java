package co.edu.ue.util;

import io.swagger.v3.oas.models.Components; 
import io.swagger.v3.oas.models.OpenAPI; 
import io.swagger.v3.oas.models.info.Info; 
import io.swagger.v3.oas.models.security.SecurityRequirement; 
import io.swagger.v3.oas.models.security.SecurityScheme; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
	private SecurityScheme createAPIKeyScheme() {
	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
	        .bearerFormat("JWT")
	        .scheme("bearer");
	}
	
	@Bean 
	public OpenAPI openAPI() { 
		return new OpenAPI() 
				.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication")) 
				.components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())) 
				.info(new Info().title("Courses Database")
						 .version("10-CousesDatabaseJWT")
						 .description("En el siguiente proyecto se van a usar las APIs creadas junto a la profesora Kellys\n\n"
						 		+ "<strong>Ayudaaaaaaaa no se que mas poner</strong> Mi github: [Johexlimee](https://github.com/Johexlimee)")
						 .termsOfService("http://swagger.io/terms/")); 
		}
}
