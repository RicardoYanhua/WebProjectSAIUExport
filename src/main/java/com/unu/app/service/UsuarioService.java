package com.unu.app.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Productor;
import com.unu.app.entity.Usuario;
import com.unu.app.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@Qualifier("usuarioService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 if (username == null || username.isEmpty()) {
		        throw new UsernameNotFoundException("Nombre de usuario no proporcionado");
		    }
		    Usuario user = usuarioDetailsRepository.findByUsername(username);
		    if (user == null) {
		        System.out.println("Usuario no encontrado: " + username);
		        throw new UsernameNotFoundException("Usuario no encontrado");
		    }
		    return new User(user.getUsuario(), user.getContrasenia(), user.getAuthorities());
	}
	
	public Usuario getUserByUsername(String username) {
		return usuarioDetailsRepository.findByUsername(username);
	}
	
	public Usuario getUserById(int id) {
		return usuarioDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}
	
	public void EliminarUsuario(int id) {
		if (usuarioDetailsRepository.existsById(id)) {
			usuarioDetailsRepository.deleteById(id);
		}
	}
	
	public Page<Usuario> ListarUauario(Pageable pageable) {
		Page<Usuario> listaClientes = usuarioDetailsRepository.findAll(PageRequest.of(pageable.getPageNumber(),20));
		return listaClientes;
	}
	
	
}