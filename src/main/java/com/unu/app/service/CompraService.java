package com.unu.app.service;

import java.util.List;

import com.unu.app.entity.Productor;
import com.unu.app.entity.Compra;

public interface CompraService {
	
	public abstract List<Compra> ListaCompras();
	public abstract List<Compra> getListaCompraByIdCliente(Productor cliente);
	public abstract Compra InsertarCompra(Compra compra);
	public abstract void EliminarCompra(int id);
	public abstract Compra ObtenerCompra(int id);
	public abstract void ActualizarCompra(Compra compra);
	public abstract int getLastId();
	
	
}
