package com.unu.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.unu.app.controller.security.LoginStatusListener;
import com.unu.app.controller.security.PermisosService;
import com.unu.app.entity.Productor;
import com.unu.app.entity.Usuario;
import com.unu.app.entity.Ubicacion.Departamento;
import com.unu.app.entity.Ubicacion.Distrito;
import com.unu.app.entity.Ubicacion.GeoClientService;
import com.unu.app.entity.Ubicacion.Provincia;
import com.unu.app.service.SemillaService;
import com.unu.app.service.UsuarioService;
import com.unu.app.service.CompraService;
import com.unu.app.service.ProductorService;

@Controller
@RestController
@RequestMapping("/Ubicacion")
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
@RequestMapping("/Dashboard/Productor")
public class ProductorController {

	@Autowired
	@Qualifier("productorService")
	private ProductorService productorService;
	
	@Autowired
	@Qualifier("compraService")
	private CompraService compraService;
	
	@Autowired
    private PermisosService permisosService;

    @ModelAttribute
    @Qualifier("configPermisos")
    public void configurarPermisos(Model model) {
        permisosService.configurarPermisos(model);
    }
	
	@GetMapping("/NuevoProductor")
	public ModelAndView NuevoProductor(){
		ModelAndView modelAndView = new ModelAndView("/Productor/InsertarProductor");
		modelAndView.addObject("Productor", new Productor());
		return modelAndView;
	}
	
	@PostMapping("/InsertarProductor")
	public String InsertarProductor(@ModelAttribute(name = "Cliente") Productor cliente) {
		productorService.InsertarProductor(cliente);
		return "redirect:/Dashboard/Productor/ListaProductor";
	}
	
	@GetMapping("/ObtenerProductor/{id}")
	public ModelAndView ObtenerProductor(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Productor/EditarProductor");
		Productor ObtenerCliente = productorService.ObtenerProductor(id);
		modelAndView.addObject("EditarProductor", ObtenerCliente);   
		return modelAndView;
	}
	
	@GetMapping("/VerDatosProductor/{id}")
	public ModelAndView VerDatosProductor(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Productor/VerDatosProductor");
		Productor ObtenerProductor = productorService.ObtenerProductor(id);
		modelAndView.addObject("DatosProductor", ObtenerProductor); 
		modelAndView.addObject("ListaCompras",compraService.getListaCompraByIdProductor(ObtenerProductor));
		return modelAndView;
	}
	
	@PostMapping("/ActualizarProductor/{id}")
	public String ActualizarProductor(@PathVariable(name = "id") int id, @ModelAttribute(name = "EditarProductor") Productor productor) {
		productorService.ActualizarProductor(productor);
		return "redirect:/Dashboard/Productor/ListaProductor";
	}
	
	@GetMapping("/ListaProductor")
	public ModelAndView ListaProductores(
			@RequestParam(name = "Busqueda", required = false, defaultValue = "") String busqueda,
			@RequestParam(name = "Filtro", required = false, defaultValue = "Todo") String filtro,
			Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("/Productor/ListaProductor");
		Page PaginaProductores = productorService.BuscarProductor(pageable, busqueda, filtro);
		modelAndView.addObject("ListaProductores", PaginaProductores.getContent());
		modelAndView.addObject("page", PaginaProductores);
		modelAndView.addObject("Busqueda", busqueda);
		modelAndView.addObject("Filtro", filtro);
		modelAndView.addObject("ListaVacia", ValidarListaVacia(PaginaProductores));
		return modelAndView;
	}
	
	public boolean ValidarListaVacia(Page list) {
		if(list.getContent().size() >0) {
			return false;
		}
		return true;
	}

	@PostMapping("/EliminarProductor/{id}")
	public String EliminarProductor(@PathVariable(name = "id") int id) {
		productorService.EliminarProductor(id);
		return "redirect:/Dashboard/Productor/ListaProductor";
	}
	
	 
}