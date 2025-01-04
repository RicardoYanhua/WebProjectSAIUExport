package com.unu.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.unu.app.entity.Cliente;
import com.unu.app.entity.GeoClient.Departamento;
import com.unu.app.entity.GeoClient.Distrito;
import com.unu.app.entity.GeoClient.Provincia;
import com.unu.app.service.ClienteService;
import com.unu.app.service.GeoClientService;

@Controller
@RestController
@RequestMapping("/Ubicaciones")
class Ubicacion {
	
	@Autowired
	private GeoClientService dataService;
	@GetMapping("/getDepartamentos")
    public List<Departamento> getDepartamentos() throws IOException {
        return dataService.getDepartamentos();
    }
	
    @GetMapping("/getProvincias")
    public List<Provincia> getProvincias(@RequestParam String ID) throws IOException {
        return dataService.getProvinciasByDepartamentoId(ID);
    }
    
    @GetMapping("/getDistritos")
    public List<Distrito> getDistritos(@RequestParam String ID) throws IOException {
        return dataService.getDistritosByProvinciaId(ID);
    }
	
}

@Controller
@RequestMapping("/Dashboard/Clientes")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;

	
	@GetMapping("/NuevoCliente")
	public ModelAndView NuevoCliente()throws IOException {
		ModelAndView modelAndView = new ModelAndView("/Clientes/InsertarCliente");
		modelAndView.addObject("Cliente", new Cliente());
		return modelAndView;
	}
	
	@PostMapping("/InsertarCliente")
	public String InsertarNuevoCliente(@ModelAttribute(name = "Cliente") Cliente cliente) {
		clienteService.InsertarCliente(cliente);
		return "redirect:/Dashboard/Clientes/ListaClientes";
		
	}
	
	@GetMapping("/ListaClientes")
	public ModelAndView ListaClientes(
			@RequestParam(name = "InputBusqueda", required = false, defaultValue = "") String busqueda,
			@RequestParam(name = "BusquedaFiltro", required = false, defaultValue = "Todo") String filtro,
			Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("/Clientes/ListaClientes");
		Page paginaClientes = clienteService.BuscarCliente(pageable, busqueda, filtro);
		modelAndView.addObject("listaClientes", paginaClientes.getContent());
		modelAndView.addObject("page", paginaClientes);
		modelAndView.addObject("InputBusqueda", busqueda);
		modelAndView.addObject("BusquedaFiltro", filtro);
		return modelAndView;
	}

	@PostMapping("/ElimnarCliente/{id}")
	public String EliminarCliente(@PathVariable(name = "id") int id) {
		clienteService.EliminarCliente(id);
		return "redirect:/Dashboard/Clientes/ListaClientes";
	}
}