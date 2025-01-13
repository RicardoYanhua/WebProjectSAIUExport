package com.unu.app.controller;

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
import com.unu.app.entity.Semilla;
import com.unu.app.service.SemillaService;

@Controller
@RequestMapping("/Semilla")
public class SemillaController {
	@Autowired
	@Qualifier("semillaService")
	private SemillaService semillaService;
	
	@GetMapping("/ListaSemilla")
	public ModelAndView ListaSemilla(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("/Semilla/ListaSemilla");
		Page paginaProductos = semillaService.ListaSemilla(pageable);
		modelAndView.addObject("ListaSemilla", paginaProductos.getContent());
		modelAndView.addObject("page", paginaProductos);
		return modelAndView;
	}
	
	@GetMapping("/NuevoSemilla")
	public ModelAndView NuevoSemilla(){
		ModelAndView modelAndView = new ModelAndView("/Semilla/InsertarSemilla");
		modelAndView.addObject("Semilla", new Semilla());
		return modelAndView;
	}
	@PostMapping("/InsertarSemilla")
	public String InsertarSemilla(@ModelAttribute(name = "Semilla") Semilla semilla) {
		semillaService.InsertarSemilla(semilla);
		return "redirect:/Semilla/ListaSemilla";
		
	}
	
	@GetMapping("/ObtenerSemilla/{id}")
	public ModelAndView ObtenerSemilla(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Semilla/EditarSemilla");
		Semilla ObtenerProducto = semillaService.ObtenerSemilla(id);
		modelAndView.addObject("EditarSemilla", ObtenerProducto);   
		return modelAndView;
	}
	
	@PostMapping("/ActualizarSemilla/{id}")
	public String ActualizarSemilla(@PathVariable(name = "id") int id, @ModelAttribute(name = "EditarSemilla") Semilla semilla) {
		semillaService.ActualizarSemilla(semilla);
		return "redirect:/Semilla/ListaSemilla";
		
	}
	
	@PostMapping("/EliminarSemilla/{id}")
	public String EliminarSemilla(@PathVariable(name = "id") int id) {
		semillaService.EliminarSemilla(id);
		return "redirect:/Semilla/ListaSemilla";
	}

	
	 
}