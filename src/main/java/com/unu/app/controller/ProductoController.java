package com.unu.app.controller;


import java.io.IOException;

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
import org.springframework.web.servlet.ModelAndView;
import com.unu.app.entity.Producto;
import com.unu.app.service.ProductoService;

@Controller
@RequestMapping("/Dashboard/Productos")
public class ProductoController {
	
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;
	
	@GetMapping("/ListaProductos")
	public ModelAndView ListaClientes(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("/Productos/ListaProducto");
		Page paginaProductos = productoService.ListaProducto(pageable);
		modelAndView.addObject("listaProductos", paginaProductos.getContent());
		modelAndView.addObject("page", paginaProductos);
		return modelAndView;
	}
	
	@GetMapping("/NuevoProducto")
	public ModelAndView NuevoCliente()throws IOException {
		ModelAndView modelAndView = new ModelAndView("/Productos/InsertarProducto");
		modelAndView.addObject("Producto", new Producto());
		return modelAndView;
	}
	@PostMapping("/InsertarProducto")
	public String InsertarNuevoCliente(@ModelAttribute(name = "Producto") Producto producto) {
		productoService.InsertarProducto(producto);
		return "redirect:/Dashboard/Productos/ListaProductos";
		
	}
	
	@GetMapping("/ObtenerProducto/{id}")
	public ModelAndView EditarCliente(@PathVariable(name = "id") int id)throws IOException {
		ModelAndView modelAndView = new ModelAndView("/Productos/EditarProducto");
		Producto ObtenerProducto = productoService.ObtenerProducto(id);
		modelAndView.addObject("ProductoEditar", ObtenerProducto);   
		return modelAndView;
	}
	
	@PostMapping("/ActualizarProducto/{id}")
	public String ActualizarCliente(@PathVariable(name = "id") int id, @ModelAttribute(name = "ClienteEditar") Producto producto) {
		productoService.ActualizarProducto(producto);
		return "redirect:/Dashboard/Productos/ListaProductos";
		
	}
	
	@PostMapping("/ElimnarProducto/{id}")
	public String EliminarCliente(@PathVariable(name = "id") int id) {
		productoService.EliminarProducto(id);
		return "redirect:/Dashboard/Productos/ListaProductos";
	}

	
	 
}