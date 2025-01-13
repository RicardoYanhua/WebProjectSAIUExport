package com.unu.app.service.Impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.unu.app.entity.Productor;
import com.unu.app.entity.Ubicacion.GeoClientService;
import com.unu.app.repository.ProductorRepository;
import com.unu.app.service.ProductorService;

@Service("productorService")
public class ProductorServiceImpl implements ProductorService {

	@Autowired
	@Qualifier("productorRepository")
	private ProductorRepository productorRepository;

	@Override
	public void EliminarProductor(int id) {
		if (productorRepository.existsById(id)) {
			productorRepository.deleteById(id);
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
	public Page<Productor> BuscarProductor(Pageable pageable, String busqueda, String Filter) {

		Page<Productor> listaClientes = null;
		int size = 20;
		switch (Filter) {
		case "Todo":
			listaClientes = productorRepository.buscarByAll(PageRequest.of(pageable.getPageNumber(), size), busqueda);
			break;
		case "DNI":
			listaClientes = productorRepository.BuscarProductorByDNI(PageRequest.of(pageable.getPageNumber(), size),
					busqueda);
			break;
		case "Nombre":
			listaClientes = productorRepository.BuscarProductorByNombre(PageRequest.of(pageable.getPageNumber(), size),
					busqueda);
			break;
		case "Apellido":
			listaClientes = productorRepository.BuscarProductorByApellido(PageRequest.of(pageable.getPageNumber(), size),
					busqueda);
			break;
		case "Correo":
			listaClientes = productorRepository.BuscarProductorByCorreo(PageRequest.of(pageable.getPageNumber(), size),
					busqueda);
			break;
		case "Telefono":
			listaClientes = productorRepository.BuscarProductorByTelefono(PageRequest.of(pageable.getPageNumber(), size),
					busqueda);
			break;
		default:
			listaClientes = productorRepository.findAll(PageRequest.of(pageable.getPageNumber(),size));
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
	public Productor InsertarProductor(Productor cliente) {
		return productorRepository.save(cliente);
	}

	@Override
	public void ActualizarProductor(Productor cliente) {
		productorRepository.save(cliente);
	}

	@Override
	public Productor ObtenerProductor(int id) {
		return productorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}

	@Override
	public List<Productor> getListProductorNameAndId() {
		return productorRepository.getListClientesNameAndId();
	}

}
