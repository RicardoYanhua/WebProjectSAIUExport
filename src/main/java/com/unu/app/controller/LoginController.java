package com.unu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unu.app.entity.Usuario;
import com.unu.app.service.ClienteService;
import com.unu.app.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login") 
public class LoginController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
    @GetMapping("/form-sesion")
    public ModelAndView ejecutarMetodo2(
    		
    		) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginRequest",new Usuario());
        return modelAndView;
    }
    
    @PostMapping("/IniciarSesion")
    public ModelAndView  procesarLogin(
    		@ModelAttribute(name ="loginRequest") Usuario loginRequest, 
    		HttpSession session
            ) {
        Usuario usuarioOpt = usuarioService.ObtenerUsuario(loginRequest.getUsuario(), loginRequest.getContrasenia());
        
        if (usuarioOpt != null) {
        	session.setAttribute("user", usuarioOpt);
            return new ModelAndView("redirect:/Dashboard/Main");
        }
        
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginRequest", loginRequest);
        modelAndView.addObject("error", "Usuario o contraseña incorrectos.");
        return modelAndView;
    }
    
    @GetMapping("/CerrarSesion")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false); // Obtiene la sesión actual, si existe
        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }
        //redirectAttributes.addFlashAttribute("mensaje", "Has cerrado sesión exitosamente.");
        return "redirect:/login/form-sesion"; // Redirige a la página de inicio de sesión
    }
    
}