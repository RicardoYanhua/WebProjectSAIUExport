package com.unu.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_compra")
public class DetalleCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra")
	private Compra id_compra;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semilla")
	private Semilla id_semilla;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "subtotal")
	private double subtotal;
	
	@Column(name = "fecha_compra")
	private LocalDateTime fecha_compra;

	public DetalleCompra() {
		this(0,new Compra(),new Semilla(),0,0.0,LocalDateTime.now());
	}
	
	public DetalleCompra(int id, Compra id_compra, Semilla id_semilla, int cantidad,
			double subtotal, LocalDateTime fecha_compra) {
		super();
		this.id = id;
		this.id_compra = id_compra;
		this.id_semilla = id_semilla;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.fecha_compra = fecha_compra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Compra getId_compra() {
		return id_compra;
	}

	public void setId_compra(Compra id_compra) {
		this.id_compra = id_compra;
	}

	public Semilla getId_semilla() {
		return id_semilla;
	}

	public void setId_semilla(Semilla id_semilla) {
		this.id_semilla = id_semilla;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public LocalDateTime getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(LocalDateTime fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	
}
