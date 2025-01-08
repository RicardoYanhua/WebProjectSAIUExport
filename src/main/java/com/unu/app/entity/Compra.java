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
@Table(name = "compras")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_cliente")
	private Cliente id_cliente;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "fecha_compra")
	private LocalDateTime fecha_compra;

	public Compra() {
		this(0,new Cliente(),0.0,LocalDateTime.now());
	}
	
	public Compra(int id, Cliente id_cliente, double total, LocalDateTime fecha_compra) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.total = total;
		this.fecha_compra = fecha_compra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(LocalDateTime fecha_compra) {
		this.fecha_compra = fecha_compra;
	}
	
}
