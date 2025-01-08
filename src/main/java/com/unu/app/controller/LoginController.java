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
import com.unu.app.entity.Usuario.Role;
import com.unu.app.service.ClienteService;
import com.unu.app.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Login")
public class LoginController {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@GetMapping("/IniciarSesion")
	public ModelAndView ejecutarMetodo2() {
		ModelAndView modelAndView = new ModelAndView("/Sesion/FormLogin");
		modelAndView.addObject("LoginRequest", new Usuario());
		return modelAndView;
	}

	@PostMapping("/IniciarSesion")
	public ModelAndView procesarLogin(@ModelAttribute(name = "LoginRequest") Usuario loginRequest,
			HttpSession session) {
		Usuario usuarioOpt = usuarioService.ObtenerUsuario(loginRequest.getUsuario(), loginRequest.getContrasenia());
		if (usuarioOpt != null) {
			session.setAttribute("UsuarioLogin", usuarioOpt);
			return new ModelAndView("redirect:/Main");
		}
		ModelAndView modelAndView = new ModelAndView("/Sesion/FormLogin");
		modelAndView.addObject("LoginRequest", loginRequest);
		modelAndView.addObject("error", "Usuario o contraseña incorrectos.");
		return modelAndView;
	}

	@GetMapping("/CerrarSesion")
	public String CerrarSesion(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); 
		}
		return "redirect:/Login/IniciarSesion";
	}

}