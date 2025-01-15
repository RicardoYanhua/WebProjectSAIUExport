package com.unu.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unu.app.entity.Productor;

public interface ProductorService {
	
	public abstract Page BuscarProductor(Pageable pageable, String busqueda, String Filter);
	
	
	public abstract List<Productor> ObtenerListaProductor_Id_Nombre();

	public abstract Productor InsertarProductor(Productor cliente);
	public abstract void EliminarProductor(int id);
	public abstract void ActualizarProductor(Productor cliente);
	public abstract Productor ObtenerProductor(int id);

}
