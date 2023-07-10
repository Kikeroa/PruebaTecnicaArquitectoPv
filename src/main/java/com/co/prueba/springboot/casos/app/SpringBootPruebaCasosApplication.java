package com.co.prueba.springboot.casos.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@Api(tags = "Auth API", description = "API para autenticación y manejo de tokens")
public class SpringBootPruebaCasosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPruebaCasosApplication.class, args);
	}
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.co.prueba.springboot.casos.app.controllers"))
                .paths(PathSelectors.any())
                .build();
    }
}
