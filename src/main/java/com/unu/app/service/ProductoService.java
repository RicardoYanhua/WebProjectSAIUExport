package com.unu.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unu.app.entity.Producto;

public interface ProductoService {
	
	public abstract Page ListaProducto(Pageable pageable);
	public abstract Producto InsertarProducto(Producto producto);
	public abstract void EliminarProducto(int id);
	public abstract void ActualizarProducto(Producto producto);
	public abstract Producto ObtenerProducto(int id);
	public abstract List<Producto> getProductos();
	
}
