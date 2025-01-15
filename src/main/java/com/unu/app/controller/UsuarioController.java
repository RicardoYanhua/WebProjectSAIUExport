package com.unu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unu.app.controller.security.PermisosService;
import com.unu.app.entity.Productor;
import com.unu.app.entity.Usuario;
import com.unu.app.repository.UsuarioRepository;
import com.unu.app.service.UsuarioService;
import com.unu.app.service.CompraService;
import com.unu.app.service.ProductorService;

@Controller
@RequestMapping("/Dashboard/Usuario")
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermisosService permisosService;

	@ModelAttribute
	public void configurarPermisos(Model model) {
		permisosService.configurarPermisos(model);
	}

	@Autowired
	@Qualifier("productorService")
	private ProductorService productorService;

	@Autowired
	@Qualifier("compraService")
	private CompraService compraService;

	@GetMapping("/RegistrarUsuario")
	public ModelAndView Register() {
		ModelAndView modelAndView = new ModelAndView("/Usuario/InsertarUsuario");
		modelAndView.addObject("UserRequest", new Usuario());
		return modelAndView;
	}

	public ModelAndView ValidarDatosFormularioRegister(Usuario user, String pass, String passConfirm) {
		ModelAndView modelAndView = new ModelAndView("/Usuario/InsertarUsuario");
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
		try {
			var bCryptEncoder = new BCryptPasswordEncoder();
			Usuario Usuario_register = user;
			Usuario_register.setContrasenia(bCryptEncoder.encode(Usuario_register.getContrasenia()));
			usuarioRepository.save(Usuario_register);
		} catch (Exception e) {
			
		}
		return new ModelAndView("redirect:/Dashboard/Usuario/ListaUsuario");
	}

	@PostMapping("/RegistrarUsuario")
	public ModelAndView RegistrarUsuario(@ModelAttribute(name = "UserRequest") Usuario userRequest,
			@ModelAttribute(name = "Contrasenia") String pass,
			@ModelAttribute(name = "ContraseniaConfirm") String passConfirm) {
		return ValidarDatosFormularioRegister(userRequest, pass, passConfirm);
	}

	

	@GetMapping("/ObtenerUsuario/{id}")
	public ModelAndView ObtenerUsuario(@PathVariable(name = "id") int id) {
		ModelAndView modelAndView = new ModelAndView("/Usuario/EditarUsuario");
		Usuario ObtenerCliente = usuarioService.getUserById(id);
		modelAndView.addObject("EditarUsuario", ObtenerCliente);
		return modelAndView;
	}


	@PostMapping("/ActualizarUsuario/{id}")
	public String ActualizarProductor(@PathVariable(name = "id") int id,
			@ModelAttribute(name = "EditarUsuario") Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/Dashboard/Usuario/ListaUsuario";
	}

	@GetMapping("/ListaUsuario")
	public ModelAndView ListaProductores(
			@RequestParam(name = "Busqueda", required = false, defaultValue = "") String busqueda,
			@RequestParam(name = "Filtro", required = false, defaultValue = "Todo") String filtro, Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView("/Usuario/ListaUsuario");
		Page PaginaProductores = usuarioService.ListarUauario(pageable);
		modelAndView.addObject("ListaUsuarios", PaginaProductores.getContent());
		modelAndView.addObject("page", PaginaProductores);
		modelAndView.addObject("Busqueda", busqueda);
		modelAndView.addObject("Filtro", filtro);

		modelAndView.addObject("ListaVacia", ValidarListaVacia(PaginaProductores));
		return modelAndView;
	}

	public boolean ValidarListaVacia(Page list) {
		if (list.getContent().size() > 0) {
			return false;
		}
		return true;
	}

	@PostMapping("/EliminarUsuario/{id}")
	public String EliminarProductor(@PathVariable(name = "id") int id) {
		usuarioService.EliminarUsuario(id);
		return "redirect:/Dashboard/Usuario/ListaUsuario";
	}

}