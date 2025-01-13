package com.unu.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	
	
}