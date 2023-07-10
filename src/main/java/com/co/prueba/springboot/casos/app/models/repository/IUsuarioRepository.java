package com.co.prueba.springboot.casos.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.prueba.springboot.casos.app.models.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByToken(String token);
}
