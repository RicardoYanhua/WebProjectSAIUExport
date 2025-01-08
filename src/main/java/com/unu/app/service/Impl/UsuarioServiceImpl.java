package com.unu.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Cliente;
import com.unu.app.entity.Usuario;
import com.unu.app.repository.UsuarioRepository;
import com.unu.app.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	@Qualifier("usuarioRepository")
	private  UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> ListaUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario InsertarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void EliminarUsuario(int id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		}
	}

	@Override
	public Usuario ObtenerUsuario(String ususario, String contrasenia) {
		return usuarioRepository.EncontrarUusario(ususario, contrasenia);
	}

	@Override
	public Usuario ObtenerUsuario(int id) {
	    return usuarioRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("UsuARIO no encontrado con ID: " + id));
	}
	
	@Override
	public void ActualizarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
