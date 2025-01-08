package com.unu.app.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Compra;
import com.unu.app.entity.DetalleCompra;
import com.unu.app.entity.Producto;
import com.unu.app.repository.CompraRepository;
import com.unu.app.repository.DetalleCompraRepository;
import com.unu.app.repository.ProductoRepository;
import com.unu.app.service.CompraService;
import com.unu.app.service.DetalleCompraService;
import com.unu.app.service.ProductoService;

@Service("compraService")

public class CompraServiceImpl implements CompraService {

	@Autowired
	@Qualifier("compraRepository")
	private CompraRepository compraRepository;
	

	@Override
	public List<Compra> ListaCompras() {
		return compraRepository.findAll();
	}

	@Override
	public Compra InsertarCompra(Compra compra) {
		return compraRepository.save(compra);
	}

	@Override
	public void EliminarCompra(int id) {
		if (compraRepository.existsById(id)) {
			compraRepository.deleteById(id);
		}
	}

	@Override
	public Compra ObtenerCompra(int id) {
		return compraRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}

	@Override
	public void ActualizarCompra(Compra compra) {
		compraRepository.save(compra);
		
	}

	@Override
	public int getLastId() {
		return compraRepository.ObtenerUltimoId();
	}

	
	
	
}
