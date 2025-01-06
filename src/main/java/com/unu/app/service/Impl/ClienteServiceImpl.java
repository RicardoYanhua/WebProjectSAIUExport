package com.unu.app.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Cliente;
import com.unu.app.entity.Ubicacion.GeoClientService;
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
	
	@Autowired
    private GeoClientService dataService;

    public String getNombreDepartamentoById(String id) throws IOException {
    	return dataService.getDepartamentoNameById(id);
    }
    public String getNombreProvinciaById(String id) throws IOException {
    	return dataService.getProvinciaNameById(id);
    }
    public String getNombreDistritoById(String id) throws IOException {
    	return dataService.getDistritoNameById(id);
    }
	

	@Override
	public Page<Cliente> BuscarCliente(Pageable pageable, String busqueda, String Filter) {
		
		Page<Cliente> listaClientes = null;
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
		
		listaClientes.getContent().forEach(cliente -> {
	        String nombreDepartamento = "Desconocido";
	        String nombreProvincia = "Desconocido";
	        String nombreDistrito = "Desconocido";
				try {
					nombreDepartamento = getNombreDepartamentoById(cliente.getId_departamento());
					nombreProvincia = getNombreProvinciaById(cliente.getId_provincia());
			        nombreDistrito = getNombreDistritoById(cliente.getId_distrito());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        cliente.setNombreDepartamento(nombreDepartamento);
	        cliente.setNombreProvincia(nombreProvincia);
	        cliente.setNombreDistrito(nombreDistrito);
	    });
		
		return listaClientes;
	}

	@Override
	public Cliente InsertarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	@Override
	public void ActualizarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente ObtenerCliente(int id) {
	    return clienteRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}
	@Override
	public List<Cliente> getListClientesNameAndId() {
		return clienteRepository.getListClientesNameAndId();
	}

}
