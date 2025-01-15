package com.unu.app.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unu.app.entity.Compra;
import com.unu.app.entity.DetalleCompra;
import com.unu.app.repository.DetalleCompraRepository;
import com.unu.app.service.CompraService;
import com.unu.app.service.DetalleCompraService;

@Service("detalleCompraService")

public class DetalleCompraServiceImpl implements DetalleCompraService {

	@Autowired
	@Qualifier("detalleCompraRepository")
	private DetalleCompraRepository detalleCompraRepository;
	
	@Autowired
	@Qualifier("compraService")
	private CompraService compraService;

	@Override
	public List<DetalleCompra> ListaDetalleCompraByIdCompra(Compra compra) {
		return detalleCompraRepository.ListaDetalleByIdCompra(compra);
	}

	@Override
	public DetalleCompra InsertarDetalleProducto(DetalleCompra detalleCompra) {
		return detalleCompraRepository.save(detalleCompra);
	}

	@Override
	public void EliminarDetalleProducto(int id) {
		if (detalleCompraRepository.existsById(id)) {
			detalleCompraRepository.deleteById(id);
		}
	}

	@Override
	public DetalleCompra ObtenerDetalleProducto(int id) {
		return detalleCompraRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("DetalleCompraNoEncontrado no encontrado con ID: " + id));
	}

	@Override
	public void ActualizarDetalleProducto(DetalleCompra detalleCompra) {
		detalleCompraRepository.save(detalleCompra);
	}

	@Override
	public double CalcularImporteTotalByIdCompra(Compra compra) {
		List<DetalleCompra> lista = ListaDetalleCompraByIdCompra(compra);
		double ImporteTotal = 0;
		for(DetalleCompra dc : lista) {
			ImporteTotal = ImporteTotal + dc.getSubtotal();
		}
		
		compra.setTotal(ImporteTotal);
		compraService.ActualizarCompra(compra);
		return ImporteTotal;
	}

	@Override
	public Double obtenerTotalPorCompra(int idCompra) {
		
		return detalleCompraRepository.obtenerTotalPorCompra(idCompra);
	}

	@Override
	public List<DetalleCompra> ListaDetalleByIdCompra(Long compra) {
		return detalleCompraRepository.ListaDetalleByIdCompra(compra);
	}

	
	
}
