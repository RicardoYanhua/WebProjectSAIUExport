package com.unu.app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.unu.app.controller.security.LoginStatusListener;
import com.unu.app.controller.security.PermisosService;
import com.unu.app.entity.Usuario;
import com.unu.app.service.UsuarioService;

import org.springframework.ui.Model;

@Controller
public class WebClientController {

	@Autowired
    private PermisosService permisosService;

    @ModelAttribute
    public void configurarPermisos(Model model) {
        permisosService.configurarPermisos(model);
    }

	@GetMapping("/Inicio")
	public String Inicio() {
		return "/WebPages/PageInicio";
	}

	@GetMapping("/Contacto")
	public String Contacto() {
		return "/WebPages/PageContacto";
	}

	/*
	 * 
	 * @GetMapping("/Productor") public String Productor() { return
	 * "redirect:/Productor/ListaProductor"; }
	 * 
	 * @GetMapping("/Semilla") public String Semilla() { return
	 * "redirect:/Semilla/ListaSemilla"; }
	 * 
	 * @GetMapping("/Usuario") public String Usuario() { return
	 * "redirect:/Usuario/ListaUsuario"; }
	 * 
	 * @GetMapping("/Inicio") public String Inicio() { return
	 * "/WebPages/Inicio/Page"; }
	 * 
	 * @GetMapping("/Contacto") public String Contacto() { return
	 * "/WebPages/Contacto/Page"; }
	 * 
	 */

}
