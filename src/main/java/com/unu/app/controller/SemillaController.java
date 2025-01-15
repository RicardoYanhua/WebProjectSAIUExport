package com.unu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unu.app.controller.security.PermisosService;
import com.unu.app.entity.Semilla;
import com.unu.app.service.ProductorService;
import com.unu.app.service.SemillaService;

@Controller
@RequestMapping("/Dashboard/Semilla")
public class SemillaController {
	
	@Autowired
	@Qualifier("semillaService")
	private SemillaService semillaService;
	
	@Autowired
	@Qualifier("productorService")
	private ProductorService productorService;
	
	@Autowired
    private PermisosService permisosService;

    @ModelAttribute
    @Qualifier("configPermisos")
    public void configurarPermisos(Model model) {
        permisosService.configurarPermisos(model);
    }
	
	@GetMapping("/ListaSemilla")
	public ModelAndView ListaSemilla(Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("/Semilla/ListaSemilla");
		Page paginaProductos = semillaService.ListaSemilla(pageable);
		modelAndView.addObject("ListaSemillas", paginaProductos.getContent());
		
		modelAndView.addObject("page", paginaProductos);
		return modelAndView;
	}
	
	@GetMapping("/NuevoSemilla")
	public ModelAndView NuevoSemilla(){
		ModelAndView modelAndView = new ModelAndView("/Semilla/InsertarSemilla");
		modelAndView.addObject("Semilla", new Semilla());
		modelAndView.addObject("ListaProductor", productorService.ObtenerListaProductor_Id_Nombre());
		return modelAndView;
	}
	
	public String getCodigo(String texto) {
	    if (texto == null || texto.isEmpty()) {
	        return "";
	    }
	    String[] partes = texto.split(" | ");
	    return partes.length > 0 ? partes[0].trim() : "error";  
	}
	
	@PostMapping("/InsertarSemilla")
	public String InsertarSemilla(
			@ModelAttribute(name = "Semilla") Semilla semilla,
			@ModelAttribute(name = "ProductorSeleccionado") String idProductor
			
			) {
		Semilla insertarSemilla = semilla;
		insertarSemilla.setId_productor(productorService.ObtenerProductor(Integer.parseInt(getCodigo(idProductor))));
		semillaService.InsertarSemilla(semilla);
		return "redirect:/Dashboard/Semilla/ListaSemilla";
	}
	
	@GetMapping("/ObtenerSemilla/{id}")
	public ModelAndView ObtenerSemilla(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Semilla/EditarSemilla");
		Semilla obtenreSemilla = semillaService.ObtenerSemilla(id);
		modelAndView.addObject("EditarSemilla", obtenreSemilla);   
		return modelAndView;
	}
	
	@PostMapping("/ActualizarSemilla/{id}")
	public String ActualizarSemilla(@PathVariable(name = "id") int id, @ModelAttribute(name = "EditarSemilla") Semilla semilla) {
		
		
		semillaService.ActualizarSemilla(semilla);
		return "redirect:/Dashboard/Semilla/ListaSemilla";
		
	}
	
	@PostMapping("/EliminarSemilla/{id}")
	public String EliminarSemilla(@PathVariable(name = "id") int id) {
		semillaService.EliminarSemilla(id);
		return "redirect:/Dashboard/Semilla/ListaSemilla";
	}

	
	 
}