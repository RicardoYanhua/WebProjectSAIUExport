package com.unu.app.service;

import java.util.List;

import com.unu.app.entity.Cliente;
import com.unu.app.entity.Usuario;

public interface UsuarioService {
	public abstract List<Usuario> ListaUsuarios();
	public abstract Usuario InsertarUsuario(Usuario usuario);
	public abstract void EliminarUsuario(int id);
	public abstract void ActualizarUsuario(Usuario usuario);
	public abstract Usuario ObtenerUsuario(String ususario, String contrasenia);
	public abstract Usuario ObtenerUsuario(int id);

}
