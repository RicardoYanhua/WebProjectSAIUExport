package com.unu.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unu.app.entity.Cliente;

public interface ClienteService {
	public abstract Page BuscarCliente(Pageable pageable, String busqueda, String Filter);
	public abstract Cliente InsertarCliente(Cliente cliente);
	public abstract void EliminarCliente(int id);
	public abstract void ActualizarCliente(Cliente cliente);
	public abstract Cliente ObtenerCliente(int id);
	public abstract List<Cliente> getListClientesNameAndId();
	

}
