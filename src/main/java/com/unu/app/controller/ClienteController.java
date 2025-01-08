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
import com.unu.app.entity.Ubicacion.Departamento;
import com.unu.app.entity.Ubicacion.Distrito;
import com.unu.app.entity.Ubicacion.GeoClientService;
import com.unu.app.entity.Ubicacion.Provincia;
import com.unu.app.service.ClienteService;
import com.unu.app.service.CompraService;

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
@RequestMapping("/Clientes")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;
	
	@Autowired
	@Qualifier("compraService")
	private CompraService compraService;
	
	@GetMapping("/NuevoCliente")
	public ModelAndView NuevoCliente(){
		ModelAndView modelAndView = new ModelAndView("/Clientes/InsertarCliente");
		modelAndView.addObject("Cliente", new Cliente());
		return modelAndView;
	}
	
	@GetMapping("/ObtenerCliente/{id}")
	public ModelAndView EditarCliente(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Clientes/EditarCliente");
		Cliente ObtenerCliente = clienteService.ObtenerCliente(id);
		modelAndView.addObject("ClienteEditar", ObtenerCliente);   
		return modelAndView;
	}
	
	@GetMapping("/VerDatosCliente/{id}")
	public ModelAndView VerDatosCliente(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Clientes/VerDatosCliente");
		Cliente ObtenerCliente = clienteService.ObtenerCliente(id);
		modelAndView.addObject("DatosCliente", ObtenerCliente); 
		modelAndView.addObject("ListaCompras",compraService.getListaCompraByIdCliente(ObtenerCliente));
		
		return modelAndView;
	}
	
	@PostMapping("/ActualizarCliente/{id}")
	public String ActualizarCliente(@PathVariable(name = "id") int id, @ModelAttribute(name = "ClienteEditar") Cliente cliente) {
		clienteService.ActualizarCliente(cliente);
		return "redirect:/Clientes/ListaClientes";
	}
	
	@PostMapping("/InsertarCliente")
	public String InsertarNuevoCliente(@ModelAttribute(name = "Cliente") Cliente cliente) {
		clienteService.InsertarCliente(cliente);
		return "redirect:/Clientes/ListaClientes";
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

	@PostMapping("/EliminarCliente/{id}")
	public String EliminarCliente(@PathVariable(name = "id") int id) {
		clienteService.EliminarCliente(id);
		return "redirect:/Clientes/ListaClientes";
	}
	
	 
}