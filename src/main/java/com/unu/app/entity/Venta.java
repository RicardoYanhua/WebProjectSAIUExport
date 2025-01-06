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
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY) // Utiliza la estrategia de carga perezosa
    @JoinColumn(name = "id_cliente")
	private Cliente id_cliente;
	
	@ManyToOne(fetch = FetchType.LAZY) // Utiliza la estrategia de carga perezosa
    @JoinColumn(name = "id_ususario")
	private Usuario id_ususario;
	
	@Column(name = "total_venta")
	private double total_venta;
	
	@Column(name = "fecha_venta")
	private LocalDateTime fecha_venta;

	public Venta() {
		this(0,new Cliente(),new Usuario(),0.0,LocalDateTime.now());
	}
	
	public Venta(int id, Cliente id_cliente, Usuario id_ususario, double total_venta, LocalDateTime fecha_venta) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_ususario = id_ususario;
		this.total_venta = total_venta;
		this.fecha_venta = fecha_venta;
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

	public Usuario getId_ususario() {
		return id_ususario;
	}

	public void setId_ususario(Usuario id_ususario) {
		this.id_ususario = id_ususario;
	}

	public double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(double total_venta) {
		this.total_venta = total_venta;
	}

	public LocalDateTime getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(LocalDateTime fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	
	
	
}
