package com.unu.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Cliente;
import com.unu.app.repository.ClienteRepository;
import com.unu.app.service.ClienteService;

@Service("clienteService")

public class ClienteServiceImpl implements ClienteService {

	@Autowired
	@Qualifier("clienteRepository")
	private ClienteRepository clienteRepository;

	@Override
	public void EliminarCliente(int id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		}
	}

	@Override
	public Page ListaClientes(Pageable pageable) {
		Page<Cliente> clientes = clienteRepository.findAll(PageRequest.of(pageable.getPageNumber(), 12));
		return clientes;
	}

	@Override
	public Page BuscarCliente(Pageable pageable, String busqueda, String Filter) {
		
		Page listaClientes = null;
		switch (Filter) {
		case "Todo":
			listaClientes = clienteRepository.buscarByAll(PageRequest.of(pageable.getPageNumber(), 9), busqueda);
			break;
		case "DNI":
			listaClientes = clienteRepository.BuscarClienteByDNI(PageRequest.of(pageable.getPageNumber(), 9), busqueda);
			break;
		case "Nombre":
			listaClientes = clienteRepository.BuscarClienteByNombre(PageRequest.of(pageable.getPageNumber(), 9), busqueda);
			break;
		case "Apellido":
			listaClientes = clienteRepository.BuscarClienteByApellido(PageRequest.of(pageable.getPageNumber(), 9), busqueda);
			break;
		case "Correo":
			listaClientes = clienteRepository.BuscarClienteByCorreo(PageRequest.of(pageable.getPageNumber(), 9), busqueda);
			break;
		case "Telefono":
			listaClientes = clienteRepository.BuscarClienteByTelefono(PageRequest.of(pageable.getPageNumber(), 9), busqueda);
			break;
		default:
			listaClientes = clienteRepository.findAll(PageRequest.of(pageable.getPageNumber(), 12));
		}
		return listaClientes;
	}

	@Override
	public Cliente InsertarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

}
