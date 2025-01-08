package com.unu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unu.app.entity.Usuario;
import com.unu.app.entity.Usuario.Role;
import com.unu.app.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Usuarios")
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	public boolean getUserAccess(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("UsuarioLogin");
        return user != null && user.getRol() == Role.Administrador;
    }
	
	@ModelAttribute
    public void ConfigurarPermisos(Model model, HttpSession session) {
        model.addAttribute("UserAccess", getUserAccess(session));
    }
	
	@GetMapping("/ListaUsuarios")
	public ModelAndView ListaUsuarios(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("/Usuarios/ListaUsuarios");
		modelAndView.addObject("ListaUsuarios",usuarioService.ListaUsuarios());
		return modelAndView;
	}
	
	@GetMapping("/NuevoUsuario")
	public ModelAndView NuevoUsuario(){
		ModelAndView modelAndView = new ModelAndView("/Usuarios/InsertarUsuario");
		modelAndView.addObject("Usuario", new Usuario());
		return modelAndView;
	}
	
	@PostMapping("/InsertarUsuario")
	public String InsertarUsuario(@ModelAttribute(name = "Cliente") Usuario usuario) {
		usuarioService.InsertarUsuario(usuario);
		return "redirect:/Usuarios/ListaUsuarios";
	}
	
	@GetMapping("/ObtenerUsuario/{id}")
	public ModelAndView ObtenerUsuario(@PathVariable(name = "id") int id){
		ModelAndView modelAndView = new ModelAndView("/Usuarios/EditarUsuario");
		Usuario ObtenerCliente = usuarioService.ObtenerUsuario(id);
		modelAndView.addObject("UsuarioEditar", ObtenerCliente);   
		return modelAndView;
	}
	
	@PostMapping("/ActualizarUsuario/{id}")
	public String ActualizarUsuario(@PathVariable(name = "id") int id, @ModelAttribute(name = "UsuarioEditar") Usuario usuario) {
		usuarioService.ActualizarUsuario(usuario);
		return "redirect:/Usuarios/ListaUsuarios";
	}
	
	@PostMapping("/EliminarUsuario/{id}")
	public String EliminarUsuario(@PathVariable(name = "id") int id) {
		usuarioService.EliminarUsuario(id);
		return "redirect:/Usuarios/ListaUsuarios";
	}
}