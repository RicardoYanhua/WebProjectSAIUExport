package com.unu.app.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Producto;
import com.unu.app.repository.ProductoRepository;
import com.unu.app.service.ProductoService;

@Service("productoService")

public class ProductoServiceImpl implements ProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productoRepository;

	@Override
	public Producto InsertarProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void EliminarProducto(int id) {
		if (productoRepository.existsById(id)) {
			productoRepository.deleteById(id);
		}
	}

	@Override
	public void ActualizarProducto(Producto producto) {
		productoRepository.save(producto);
		
	}

	@Override
	public Producto ObtenerProducto(int id) {
		 return productoRepository.findById(id)
		            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}

	@Override
	public Page ListaProducto(Pageable pageable) {
		Page<Producto> listaProductos = productoRepository.findAll(PageRequest.of(pageable.getPageNumber(), 12));
		return listaProductos;
	}
	
	
}
