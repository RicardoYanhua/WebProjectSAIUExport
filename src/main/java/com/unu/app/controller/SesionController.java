package com.unu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unu.app.entity.Usuario;
import com.unu.app.entity.Usuario.Role;
import com.unu.app.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SesionController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/Register")
	public ModelAndView Register() {
		ModelAndView modelAndView = new ModelAndView("/Sesion/FormRegister");
		modelAndView.addObject("UserRequest", new Usuario());
		return modelAndView;
	}

	public ModelAndView ValidarDatosFormularioRegister(Usuario user, String pass, String passConfirm) {
		ModelAndView modelAndView = new ModelAndView("/Sesion/FormRegister");
		boolean hasErrors = false;

		String Usuario = user.getUsuario();
		if (Usuario.equals("")) {
			modelAndView.addObject("UsuarioError", "El campo usuario está vacío");
			hasErrors = true;
		} else if (Usuario.length() < 8) {
			modelAndView.addObject("UsuarioError", "El usuario es muy corto.");
			hasErrors = true;
		} 

		String Correo = user.getCorreo();
		if (Correo.equals("")) {
			modelAndView.addObject("CorreoError", "El campo correo está vacío");
			hasErrors = true;
		} 

		if (pass.equals("")) {
			modelAndView.addObject("ContraseniaError", "El campo contraseña está vacío");
			hasErrors = true;
		} else if (pass.length() < 8) {
			modelAndView.addObject("ContraseniaError", "La contraseña es muy corta.");
			hasErrors = true;
		} else if (passConfirm.equals("")) {
			modelAndView.addObject("ContraseniaConfirmError", "Por favor confirme su contraseña");
			hasErrors = true;
		} else if (!pass.equals(passConfirm)) {
			modelAndView.addObject("ContraseniaConfirmError", "Las contraseñas no coinciden.");
			hasErrors = true;
		} else {
			user.setContrasenia(passConfirm);
		}

		String Nombre = user.getNombre();
		if (Nombre.equals("")) {
			modelAndView.addObject("NombreError", "El campo nombre está vacío");
			hasErrors = true;
		}

		String Apellido = user.getApellido();
		;
		if (Apellido.equals("")) {
			modelAndView.addObject("ApellidoError", "El campo apellido está vacío");
			hasErrors = true;
		}

		if (user.getDireccion().equals("")) {
			modelAndView.addObject("DireccionError", "El campo dirección está vacío");
			hasErrors = true;
		}

		String Telefono = user.getTelefono();
		if (Telefono.equals("")) {
			modelAndView.addObject("TelefonoError", "El campo teléfono está vacío");
			hasErrors = true;
		} else if (Telefono.length() < 11) {
			modelAndView.addObject("TelefonoError", "El telefono no es valido.");
			hasErrors = true;
		}

		modelAndView.addObject("UserRequest", user);

		if (hasErrors) {
			return modelAndView;
		}

		user.setRol(Role.Cliente);
		
		
		try {
			var  bCryptEncoder = new BCryptPasswordEncoder();
			Usuario Usuario_register = user;
			Usuario_register.setContrasenia(bCryptEncoder.encode(Usuario_register.getContrasenia()));
			usuarioRepository.save(Usuario_register);
		} catch (Exception e) {}
		return new ModelAndView("redirect:/Login");
	}

	
	@PostMapping("/Register")
	public ModelAndView Registrarse(
			@ModelAttribute(name = "UserRequest") Usuario userRequest,
			@ModelAttribute(name = "Contrasenia") String pass,
			@ModelAttribute(name = "ContraseniaConfirm") String passConfirm) {
		return ValidarDatosFormularioRegister(userRequest, pass, passConfirm);
	}
	
	@GetMapping("/Login")
	public ModelAndView Login() {
	    ModelAndView modelAndView = new ModelAndView("/Sesion/FormLogin");
	    return modelAndView;
	}
	
	
	
	
	

}