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
import org.springframework.web.servlet.ModelAndView;

import com.unu.app.entity.Cliente;
import com.unu.app.entity.Producto;
import com.unu.app.service.ClienteService;
import com.unu.app.service.ProductoService;

@Controller
@RequestMapping("/Dashboard/Ventas")
public class VentaController {
	
	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;
	
	public List<Cliente> getClientesNameAndId()throws IOException {
			return clienteService.getListClientesNameAndId();
	}
	
	@GetMapping("/ListaVentas")
	public ModelAndView ListaClientes() {
		ModelAndView modelAndView = new ModelAndView("/Ventas/ListaVentas");
		return modelAndView;
	}
	
	@GetMapping("/RealizarVenta")
	public ModelAndView RealizarVenta() throws IOException {
		ModelAndView modelAndView = new ModelAndView("/Ventas/RealizarVenta");
		modelAndView.addObject("ListaClientes" , (List<Cliente>) getClientesNameAndId());
		return modelAndView;
	}
	
	

	
	 
}