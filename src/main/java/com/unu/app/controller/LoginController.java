package com.unu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login") 
public class LoginController {
	
    @GetMapping("/form-sesion")
    public ModelAndView ejecutarMetodo2() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    
    @GetMapping("/redirect-dashboard")
    public String IniciarDashboard() {
        return "redirect:/Dashboard/Main";
    }
}