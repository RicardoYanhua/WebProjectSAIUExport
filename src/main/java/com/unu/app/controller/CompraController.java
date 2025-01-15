package com.unu.app.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.unu.app.entity.Productor;
import com.unu.app.controller.security.PermisosService;
import com.unu.app.entity.Compra;
import com.unu.app.entity.DetalleCompra;
import com.unu.app.entity.Semilla;
import com.unu.app.service.SemillaService;
import com.unu.app.service.CompraService;
import com.unu.app.service.DetalleCompraService;
import com.unu.app.service.ProductorService;

@Controller
@RequestMapping("/Dashboard/Compra")
public class CompraController {

	@Autowired
	@Qualifier("productorService")
	private ProductorService productorService;

	@Autowired
	@Qualifier("compraService")
	private CompraService compraService;

	@Autowired
	@Qualifier("semillaService")
	private SemillaService semillaService;

	@Autowired
	@Qualifier("detalleCompraService")
	private DetalleCompraService detalleCompraService;

	@Autowired
	private PermisosService permisosService;

	@ModelAttribute
	@Qualifier("configPermisos")
	public void configurarPermisos(Model model) {
		permisosService.configurarPermisos(model);
	}

	@GetMapping("/ListaCompra")
	public ModelAndView ListaCompra() {
		ModelAndView modelAndView = new ModelAndView("/Compra/ListaCompra");
		modelAndView.addObject("ListaCompras", compraService.getListaCompras());
		return modelAndView;
	}
	
	
	@GetMapping("/Detalles")
    public ModelAndView verDetalles(@RequestParam("id_compra") int idCompra) {
        ModelAndView modelAndView = new ModelAndView("/Compra/VerDetallesCompra");
        Compra compra = compraService.ObtenerCompra(idCompra);
       // modelAndView.addObject("Compra",compra);
        modelAndView.addObject("ListaDetalleCompra", detalleCompraService.ListaDetalleCompraByIdCompra(compra));
        String ImportTotal = String.valueOf(detalleCompraService.CalcularImporteTotalByIdCompra(compra));
        modelAndView.addObject("ImporteTotal",ImportTotal);
        
        return modelAndView; 
    }

	public String getCodigo(String texto) {
		if (texto == null || texto.isEmpty()) {
			return "";
		}
		String[] partes = texto.split(" | ");
		return partes.length > 0 ? partes[0].trim() : "error";
	}

	@PostMapping("/EliminarCompraAdmin/{id}")
	public String EliminarProductor(@PathVariable(name = "id") int id) {
		Compra ObtenerCompra = compraService.ObtenerCompra(Integer.valueOf(id));

		List<DetalleCompra> ListaDetalle = detalleCompraService.ListaDetalleCompraByIdCompra(ObtenerCompra);

		for (DetalleCompra Detalle : ListaDetalle) {
			Semilla ObtenerSemilla = semillaService.ObtenerSemilla(Detalle.getId_semilla().getId());
			ObtenerSemilla.setCantidad(ObtenerSemilla.getCantidad() + Detalle.getCantidad());
			semillaService.ActualizarSemilla(ObtenerSemilla);
			detalleCompraService.EliminarDetalleProducto(Detalle.getId());
		}
		compraService.EliminarCompra(Integer.valueOf(id));
		return "redirect:/Dashboard/Compra/ListaCompra";
	}

	@PostMapping("/EliminarCompra")
	public String EliminarCompra(@RequestParam(name = "CodigoCompra") String id_compra) {
		System.out.println("Compra " + id_compra);
		if (!id_compra.isEmpty() && !id_compra.equals("")) {
			Compra ObtenerCompra = compraService.ObtenerCompra(Integer.valueOf(id_compra));

			List<DetalleCompra> ListaDetalle = detalleCompraService.ListaDetalleCompraByIdCompra(ObtenerCompra);

			for (DetalleCompra Detalle : ListaDetalle) {
				Semilla ObtenerSemilla = semillaService.ObtenerSemilla(Detalle.getId_semilla().getId());
				ObtenerSemilla.setCantidad(ObtenerSemilla.getCantidad() + Detalle.getCantidad());
				semillaService.ActualizarSemilla(ObtenerSemilla);
				detalleCompraService.EliminarDetalleProducto(Detalle.getId());
			}
			compraService.EliminarCompra(Integer.valueOf(id_compra));

		}

		return "redirect:/Dashboard/Compra/ListaCompra";

	}

	@PostMapping("/EliminarDetalleCompra/{id}")
	public String EliminarDetalleProducto(@PathVariable(name = "id") int id,
			@RequestParam(name = "CodigoCompra", required = false, defaultValue = "") String id_compra,
			@RequestParam(name = "ProductorSeleccionado", required = false, defaultValue = "") String productor) {

		int idCompraParsed = Integer.parseInt(id_compra);
		Compra ObtenerCompra = compraService.ObtenerCompra(idCompraParsed);
		DetalleCompra dc = detalleCompraService.ObtenerDetalleProducto(id);
		Semilla p = semillaService.ObtenerSemilla(dc.getId_semilla().getId());
		p.setCantidad(p.getCantidad() - dc.getCantidad());
		semillaService.ActualizarSemilla(p);
		detalleCompraService.EliminarDetalleProducto(id);
		String productorCodificado = URLEncoder.encode(productor, StandardCharsets.UTF_8);
		String codigoCompraCodificado = URLEncoder.encode(id_compra, StandardCharsets.UTF_8);
		return "redirect:/Dashboard/Compra/RegistrarCompra?ProductorSeleccionado=" + productorCodificado
				+ "&CodigoCompra=" + codigoCompraCodificado;

	}

	@PostMapping("/InsertarDetalleCompra")
	public String InsertarDetalleCompra(
			@RequestParam(name = "CodigoCompra", required = false, defaultValue = "") String id_compra,
			@RequestParam(name = "ProductorSeleccionado", required = false, defaultValue = "") String productor,
			@RequestParam(name = "SemillaSeleccionado") String semilla,
			@RequestParam(name = "Cantidad") String cantidad) {

		String CodigoProductor = getCodigo(productor);
		String CodigoSemilla = getCodigo(semilla);
		int cantidadInsertada = Integer.parseInt(cantidad);
		Productor obtenerProductor = productorService.ObtenerProductor(Integer.parseInt(CodigoProductor));
		Semilla ObtenerSemilla = semillaService.ObtenerSemilla(Integer.parseInt(CodigoSemilla));
		ObtenerSemilla.setCantidad(ObtenerSemilla.getCantidad() + cantidadInsertada);
		semillaService.ActualizarSemilla(ObtenerSemilla);

		Compra ObtenerCompra = null;
		if (id_compra.isEmpty() || id_compra.equals("")) {
			ObtenerCompra = new Compra();
			ObtenerCompra.setId_productor(obtenerProductor);
			compraService.InsertarCompra(ObtenerCompra);
			id_compra = String.valueOf(compraService.getLastId());
		} else {
			ObtenerCompra = compraService.ObtenerCompra(Integer.parseInt(id_compra));
		}
		DetalleCompra detalleCompra = new DetalleCompra();
		detalleCompra.setId_compra(ObtenerCompra);
		detalleCompra.setId_semilla(ObtenerSemilla);
		detalleCompra.setCantidad(cantidadInsertada);
		double Subtotal = ObtenerSemilla.getPrecio() * cantidadInsertada;
		detalleCompra.setSubtotal(Subtotal);
		detalleCompraService.InsertarDetalleProducto(detalleCompra);

		String ImportTotal = String.valueOf(detalleCompraService.CalcularImporteTotalByIdCompra(ObtenerCompra));

		String productorCodificado = URLEncoder.encode(productor, StandardCharsets.UTF_8);
		String codigoCompraCodificado = URLEncoder.encode(id_compra, StandardCharsets.UTF_8);
		String importeTotalCodificado = URLEncoder.encode(ImportTotal, StandardCharsets.UTF_8);

		return "redirect:/Dashboard/Compra/RegistrarCompra?ProductorSeleccionado=" + productorCodificado
				+ "&CodigoCompra=" + codigoCompraCodificado + "&ImporteTotal=" + importeTotalCodificado;
	}

	@GetMapping("/RegistrarCompra")
	public ModelAndView RealizarCompra(
			@RequestParam(name = "CodigoCompra", required = false, defaultValue = "") String id_compra,
			@RequestParam(name = "ProductorSeleccionado", required = false, defaultValue = "") String productor,
			@RequestParam(name = "ImporteTotal", required = false, defaultValue = "") String importeTotal) {

		ModelAndView modelAndView = new ModelAndView("/Compra/RegistrarCompra");
		modelAndView.addObject("CodigoCompra", id_compra);
		modelAndView.addObject("ListaProductor", productorService.ObtenerListaProductor_Id_Nombre());
		modelAndView.addObject("ListaSemilla", semillaService.getSemillas());
		modelAndView.addObject("ProductorSeleccionado", productor);
		modelAndView.addObject("isActivedCompra", true);

		if (!id_compra.isEmpty() || !id_compra.equals("")) {
			int idCompraParsed = Integer.parseInt(id_compra);
			Compra compra = compraService.ObtenerCompra(idCompraParsed);
			modelAndView.addObject("ListaDetalleCompra", detalleCompraService.ListaDetalleCompraByIdCompra(compra));
			modelAndView.addObject("ImporteTotal", detalleCompraService.obtenerTotalPorCompra(idCompraParsed));
		}
		return modelAndView;
	}

}