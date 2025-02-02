package mad.voll.api.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {
	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          .components(new Components()
	        		  .addSecuritySchemes("bearer-key",
	        				  new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	          .info(new Info()
                      .title("Voll.med API by: Danillo Evan")
                      .description("API Rest da aplicação Voll.med, contendo as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e "
                      		+ "cancelamento de consultas com suas validações. "
                      		+ "Login realizado com Spring Security utilizando autenticação JWT")
                      .contact(new Contact()
                              .name("Danillo Evan")
                              .email("nilloevan@gmail.com"))
                      .license(new License()
                              .name("Apache 2.0")
                              .url("http://voll.med/api/licenca")));
	}
}
