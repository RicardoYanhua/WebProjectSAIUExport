package com.unu.app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.unu.app.controller.security.LoginStatusListener;
import com.unu.app.controller.security.PermisosService;
import com.unu.app.entity.Usuario;
import com.unu.app.service.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/Dashboard")
public class DashboardController {


	@Autowired
    private PermisosService permisosService;

    @ModelAttribute
    public void configurarPermisos(Model model) {
        permisosService.configurarPermisos(model);
    }
    
    @GetMapping("/")
    public String redirectToMain1() {
        return "redirect:/Dashboard/Main";
    }
    @GetMapping("")
    public String redirectToMain2() {
        return "redirect:/Dashboard/Main";
    }

    @GetMapping("/Main")
    public String main() {
        return "DashboardMain";
    }
	
	@GetMapping("/Productor")
	public String Productor() {
		return "redirect:/Dashboard/Productor/ListaProductor";
	}
	@GetMapping("/Semilla")
	public String Semilla() {
		return "redirect:/Dashboard/Semilla/ListaSemilla";
	}
	
	@GetMapping("/Usuario")
	public String Usuario() {
		return "redirect:/Dashboard/Usuario/ListaUsuario";
	}
	
	@GetMapping("/Compra")
	public String Compra() {
		return "redirect:/Dashboard/Compra/ListaCompra";
	}
	
	
}
