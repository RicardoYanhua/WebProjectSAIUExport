package com.unu.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unu.app.entity.Cliente;

public interface ClienteService {
	public abstract Page ListaClientes(Pageable pageable);
	public abstract Page BuscarCliente(Pageable pageable, String busqueda, String Filter);
	public abstract Cliente InsertarCliente(Cliente cliente);
	public abstract void EliminarCliente(int id);
	

}
