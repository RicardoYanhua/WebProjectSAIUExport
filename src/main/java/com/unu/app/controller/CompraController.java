package com.unu.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unu.app.entity.Productor;
import com.unu.app.entity.Compra;
import com.unu.app.entity.DetalleCompra;
import com.unu.app.entity.Semilla;
import com.unu.app.service.SemillaService;
import com.unu.app.service.CompraService;
import com.unu.app.service.DetalleCompraService;
import com.unu.app.service.ProductorService;

@Controller
@RequestMapping("/Compras")
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
	
	public List<Productor> getListClientes(){
			return productorService.getListProductorNameAndId();
	}
	public List<Semilla> getListProductos(){
		return semillaService.getSemillas();
	}
	public List<DetalleCompra> getListaDetalleCompra(Compra compra){
		return detalleCompraService.ListaDetalleCompraByIdCompra(compra);
	}
	
	@GetMapping("/ListaCompras")
	public ModelAndView ListaCompras() {
		ModelAndView modelAndView = new ModelAndView("/Compras/ListaCompras");
		modelAndView.addObject("ListaCompras", compraService.ListaCompras());
		return modelAndView;
	}
	
	public String getCodigo(String texto) {
	    if (texto == null || texto.isEmpty()) {
	        return "";
	    }
	    String[] partes = texto.split("-");
	    return partes.length > 0 ? partes[0].trim() : "error";  
	}
	
	@PostMapping("/EliminarCompra/{id}")
	public String EliminarCompra(@PathVariable(name = "id") String id) {
		
		
		try {
		    int number = Integer.parseInt(id);  
		} catch (NumberFormatException e) {
			return "redirect:/Compras/ListaCompras";
		}
		
		
		Compra c = compraService.ObtenerCompra(Integer.valueOf(id));
		List<DetalleCompra> ListaDetalle = detalleCompraService.ListaDetalleCompraByIdCompra(c);
		
		for(DetalleCompra dc : ListaDetalle) {
			Semilla p = semillaService.ObtenerSemilla(dc.getId_producto().getId());
			p.setCantidad(p.getCantidad() + dc.getCantidad());
			semillaService.ActualizarSemilla(p);
			detalleCompraService.EliminarDetalleProducto(dc.getId());
		}
		compraService.EliminarCompra(Integer.valueOf(id));
		return "redirect:/Compras/ListaCompras";
	}
	
	@PostMapping("/EliminarDetalleProducto/{id}")
	public String EliminarDetalleProducto(
			@PathVariable(name = "id") int id,
			@RequestParam(name = "ClienteSeleccionado") String cliente,
			@RequestParam(name = "CodigoCompra", required = false, defaultValue = "") String codigoCompra
			
			) {
		
		Compra ObtenerCompra = null;
		if (!codigoCompra.isEmpty()) {
		        int idCompraParsed = Integer.parseInt(codigoCompra);
		        ObtenerCompra = compraService.ObtenerCompra(idCompraParsed);
		} 
		DetalleCompra dc = detalleCompraService.ObtenerDetalleProducto(id);
		Semilla p = semillaService.ObtenerSemilla(dc.getId_producto().getId());
		p.setCantidad(p.getCantidad() + dc.getCantidad());
		semillaService.ActualizarSemilla(p);
		
		detalleCompraService.EliminarDetalleProducto(id);
		return "redirect:/Compras/RealizarCompra?ClienteSeleccionado=" + cliente
				+"&CodigoCompra=" + codigoCompra
				+ "&ImporteTotal=" + String.format("%.2f", detalleCompraService.CalcularImporteTotalByIdCompra(ObtenerCompra));
	}
	
	@PostMapping("/InsertarDetalleCompra")
	public String InsertarDetalleCompra(
			@RequestParam(name = "ClienteSeleccionado") String cliente,
			@RequestParam(name = "ProductoSeleccionado") String producto,
			@RequestParam(name = "CantidadInsertada") String cantidad,
			@RequestParam(name = "ImporteTotal", required = false, defaultValue = "0") String importeTotalStr,
			@RequestParam(name = "CodigoCompra", required = false, defaultValue = "") String codigoCompra
			) {
		
		String CodigoCliente = getCodigo(cliente);

		try {
		    int number = Integer.parseInt(CodigoCliente);  
		} catch (NumberFormatException e) {
		    return "redirect:/Compras/RealizarCompra?ClienteSeleccionado=" + cliente 
		            + "&CodigoCompra=" + codigoCompra 
		            + "&ErrorCliente=Cliente no existe en la base de datos.";
		}
		
		String CodigoProducto = getCodigo(producto);
		
		
		try {
		    int number = Integer.parseInt(CodigoProducto);  
		} catch (NumberFormatException e) {
		    return "redirect:/Compras/RealizarCompra?ClienteSeleccionado=" + cliente 
		            + "&CodigoCompra=" + codigoCompra 
		            + "&ErrorProducto=El producto no existe en la base de datos.";
		}
		
		int cantidadInsertada = Integer.parseInt(cantidad);
		
		if(cantidadInsertada < 0) {
			return "redirect:/Compras/RealizarCompra?ClienteSeleccionado=" + cliente 
		            + "&CodigoCompra=" + codigoCompra 
		            + "&ErrorProducto=La cantidad es negativo no valido.";
		}
		
		Productor obtenerCliente = productorService.ObtenerProductor(Integer.parseInt(CodigoCliente));
		Semilla ObtenerProducto = semillaService.ObtenerSemilla(Integer.parseInt(CodigoProducto));
		
		if(cantidadInsertada > ObtenerProducto.getCantidad()) {
			 return "redirect:/Compras/RealizarCompra?ClienteSeleccionado=" + cliente 
			            + "&CodigoCompra=" + codigoCompra 
			            + "&ErrorProducto=No hay sufientes productos.";
		}else {
			ObtenerProducto.setCantidad(ObtenerProducto.getCantidad() - cantidadInsertada);
			semillaService.ActualizarSemilla(ObtenerProducto);
		}
		
		Compra ObtenerCompra = null;
		if (!codigoCompra.isEmpty()) {
		        int idCompraParsed = Integer.parseInt(codigoCompra);
		        ObtenerCompra = compraService.ObtenerCompra(idCompraParsed);
		} else {
			ObtenerCompra = new Compra();
			ObtenerCompra.setId_cliente(obtenerCliente);
			compraService.InsertarCompra(ObtenerCompra);
			codigoCompra = String.valueOf(compraService.getLastId());
		}
		
		DetalleCompra GenerarDetalle = new DetalleCompra();
		GenerarDetalle.setId_compra(ObtenerCompra);
		GenerarDetalle.setId_producto(ObtenerProducto);
		GenerarDetalle.setCantidad(cantidadInsertada);
		
		double Subtotal = ObtenerProducto.getPrecio() * cantidadInsertada;
		GenerarDetalle.setSubtotal(Subtotal);		
		detalleCompraService.InsertarDetalleProducto(GenerarDetalle);
		
		return "redirect:/Compras/RealizarCompra?ClienteSeleccionado=" + cliente
				+ "&CodigoCompra=" + codigoCompra
				+ "&ImporteTotal=" + String.format("%.2f", detalleCompraService.CalcularImporteTotalByIdCompra(ObtenerCompra));
		
	}
	@GetMapping("/RealizarCompra")
	public ModelAndView RealizarCompra(
			 @RequestParam(name = "ClienteSeleccionado", required = false, defaultValue = "") String cliente,
			 @RequestParam(name = "CodigoCompra", required = false, defaultValue = "") String id_compra,
			 @RequestParam(name = "ErrorCliente", required = false, defaultValue = "") String errorCliente,
			 @RequestParam(name = "ErrorProducto", required = false, defaultValue = "") String errorProducto,
			 @RequestParam(name = "ImporteTotal", required = false, defaultValue = "") String importeTotal
			){
		ModelAndView modelAndView = new ModelAndView("/Compras/RealizarCompra");
		modelAndView.addObject("ListaClientes" , (List<Productor>) getListClientes());
		modelAndView.addObject("ListaProductos" , (List<Semilla>) getListProductos());
		modelAndView.addObject("ClienteSeleccionado", cliente);
		modelAndView.addObject("CodigoCompra", id_compra);
		modelAndView.addObject("ErrorCliente", errorCliente);
		modelAndView.addObject("ErrorProducto", errorProducto);
		modelAndView.addObject("ImporteTotal", importeTotal);
		
		
		if (!id_compra.isEmpty()) {
			int idCompraParsed = Integer.parseInt(id_compra);
	        Compra compra = compraService.ObtenerCompra(idCompraParsed);
		        modelAndView.addObject("ListaDetalleCompra", getListaDetalleCompra(compra));
		        modelAndView.addObject("MostrarBotonesVolver",false);
			    modelAndView.addObject("MostrarBotonesCompra",true);
		} else {
		    modelAndView.addObject("ListaDetalleCompra", null);
		    modelAndView.addObject("MostrarBotonesVolver",true);
		    modelAndView.addObject("MostrarBotonesCompra",false);
		}
		return modelAndView;
	}
	
	

	
	 
}