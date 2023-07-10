package com.co.prueba.springboot.casos.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.springboot.casos.app.dto.LoginRequest;
import com.co.prueba.springboot.casos.app.dto.TokenRequest;
import com.co.prueba.springboot.casos.app.services.IAuthoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private IAuthoService authoService;

	@PostMapping("/login")
	@ApiOperation("Realizar inicio de sesión")
	public String login(@RequestBody LoginRequest request) {
		boolean success = authoService.login(request.getUsername(), request.getPassword());
		if (success) {
			return "Login successful";
		} else {
			return "Login failed";
		}
	}
	
	@PostMapping("/validateToken")
	@ApiOperation("Validar un token")
	public String validateToken(@RequestBody TokenRequest request) {
		boolean valid = authoService.validarTokenJWT(request.getToken());
		if (valid) {
			return "Token is valid";
		} else {
			return "Token is invalid";
		}
	}
}
