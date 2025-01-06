package com.unu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Dashboard") 
public class DashboardController {
	
	@GetMapping("/Main")
    public String MostrarHTML_DashBoardMain() {
        return "DashboardMain";
    }
	
	@GetMapping("/redirect-cliente")
    public String MostrarHTML_Cliente() {
		return "redirect:/Dashboard/Clientes/ListaClientes";
    }
	
	@GetMapping("/redirect-producto")
    public String MostrarHTML_Producto() {
		return "redirect:/Dashboard/Productos/ListaProductos";
    }
	
	@GetMapping("/redirect-cuenta")
    public String MostrarHTML_Cuenta() {
		return "redirect:/Dashboard/Cuenta/VerCuenta";
    }
	
	@GetMapping("/redirect-venta")
    public String MostrarHTML_Venta() {
		return "redirect:/Dashboard/Ventas/ListaVentas";
    }
	
	
	
}





















/*
@GetMapping("/fragmento-menu")
@ResponseBody
public String getMenuFragment(Model model) {
	
	return "<div th:insert=\"Components/NavBar :: NavBar\">sadasd</div>";

}*/