package com.co.prueba.springboot.casos.app.services;

import com.co.prueba.springboot.casos.app.models.entity.Usuario;

public interface IAuthoService {

	boolean login(String username, String password);
	
	void validarToken(String token);
	
	String obtenerDispositivosRegistrados(String token);
	
	Usuario findByToken(String token);
	
	String consumirServicioLoginSOAP (String username, String password);
	
	boolean validarTokenJWT(String token);
	
	String consumirServicioDispositivosRegistradosSOAP(String token);
	
}
