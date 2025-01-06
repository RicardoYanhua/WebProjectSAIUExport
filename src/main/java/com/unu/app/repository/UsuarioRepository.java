package com.unu.app.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.app.entity.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{

	@Query("SELECT u FROM Usuario u WHERE ( u.usuario = :usuario ) AND ( u.contrasenia = :contrasenia )")
	public Usuario EncontrarUusario(@Param("usuario") String username, @Param("contrasenia") String password);

	
}



