package com.unu.app.service;

import java.util.List;

import com.unu.app.entity.Usuario;

public interface UsuarioService {
	public abstract List<Usuario> ListaUsuarios();
	public abstract Usuario InsertarUsuario(Usuario usuario);
	public abstract void EliminarUsuario(int id);

}
