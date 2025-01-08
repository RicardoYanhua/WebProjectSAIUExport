package com.unu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	@GetMapping("/Main")
    public String MostrarHTML_DashBoardMain() {
        //return "DashboardMain";
        return "redirect:/Clientes/ListaClientes";
    }
	
	@GetMapping("/redirect-cliente")
    public String MostrarHTML_Cliente() {
		return "redirect:/Clientes/ListaClientes";
    }
	
	@GetMapping("/redirect-ayuda")
    public String MostrarHTML_Ayuda() {
		return "/Info/InfoAyuda";
    }
	
	@GetMapping("/redirect-producto")
    public String MostrarHTML_Producto() {
		return "redirect:/Productos/ListaProductos";
    }
	
	@GetMapping("/redirect-cuenta")
    public String MostrarHTML_Cuenta() {
		return "redirect:/Cuenta/Details";
    }
	@GetMapping("/redirect-usuario")
    public String MostrarHTML_Usuario() {
		return "redirect:/Usuarios/ListaUsuarios";
    }
	
	@GetMapping("/redirect-compra")
    public String MostrarHTML_Venta() {
		return "redirect:/Compras/ListaCompras";
    }
	
	
	
}





















/*
@GetMapping("/fragmento-menu")
@ResponseBody
public String getMenuFragment(Model model) {
	
	return "<div th:insert=\"Components/NavBar :: NavBar\">sadasd</div>";

}*/