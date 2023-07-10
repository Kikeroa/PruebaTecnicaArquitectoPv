package com.co.prueba.springboot.casos.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Service;

import com.co.prueba.springboot.casos.app.models.entity.Usuario;
import com.co.prueba.springboot.casos.app.models.repository.IUsuarioRepository;

@Service
public class AuthServiceImpl implements IAuthoService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public boolean login(String username, String password) {
		// Lógica para consumir el servicio SOAP de login y obtener el token
		
		String token = consumirServicioLoginSOAP(username, password);
		
		// Crear un nuevo usuario si no existe
		
		Usuario usuario = usuarioRepository.findByToken(token);
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNombre(username);
            usuario.setToken(token);
            usuarioRepository.save(usuario);
        }
		
		return false;
	}

	@Override
	public void validarToken(String token) {
        boolean tokenValido = validarTokenJWT(token);
        if (!tokenValido) {
            throw new InvalidCsrfTokenException(null, "Token inválido");
        }
		
	}

	@Override
	public String obtenerDispositivosRegistrados(String token) {
		
		String dispositivosRegistrados = consumirServicioDispositivosRegistradosSOAP(token);

		return dispositivosRegistrados;
	}

	@Override
	public Usuario findByToken(String token) {

		return usuarioRepository.findByToken(token);
	}

	@Override
	public String consumirServicioLoginSOAP(String username, String password) {
		String token = "TOKEN_GENERADO";
		return token;
	}

	@Override
	public boolean validarTokenJWT(String token) {
		// Lógica para validar el token JWT
		return true;
	}

	@Override
	public String consumirServicioDispositivosRegistradosSOAP(String token) {
		
		String dispositivosRegistrados = "DISPOSITIVOS_REGISTRADOS";
		return dispositivosRegistrados;
	}
}
