package com.unu.app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.app.entity.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{
}



