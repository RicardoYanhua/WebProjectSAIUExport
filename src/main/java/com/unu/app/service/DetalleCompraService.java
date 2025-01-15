package com.unu.app.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.unu.app.entity.Compra;
import com.unu.app.entity.DetalleCompra;

public interface DetalleCompraService {
	

	public abstract List<DetalleCompra> ListaDetalleCompraByIdCompra(Compra compra);
	public abstract List<DetalleCompra> ListaDetalleByIdCompra( Long compra);
	public abstract double CalcularImporteTotalByIdCompra(Compra compra);
	
	public abstract DetalleCompra InsertarDetalleProducto(DetalleCompra detalleCompra);
	public abstract void EliminarDetalleProducto(int id);
	public abstract DetalleCompra ObtenerDetalleProducto(int id);
	public abstract void ActualizarDetalleProducto(DetalleCompra detalleCompra);
	
	 Double obtenerTotalPorCompra(int idCompra);
	
}
