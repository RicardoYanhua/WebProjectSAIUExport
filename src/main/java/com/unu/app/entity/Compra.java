package com.unu.app.entity;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	@JoinColumn(name = "id_productor")
	private Productor id_productor;

	@Column(name = "total")
	private double total;

	@Column(name = "fecha_compra")
	private LocalDateTime fecha_compra;

	public Compra() {
		this(0, new Productor(), 0.0, LocalDateTime.now());
	}

	public Compra(int id, Productor id_productor, double total, LocalDateTime fecha_compra) {
		super();
		this.id = id;
		this.id_productor = id_productor;
		this.total = total;
		this.fecha_compra = fecha_compra;
	}

	public String formatearFecha() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return this.fecha_compra.format(formatter);
	}
	public String formatearTotal() {
        DecimalFormat formatter = new DecimalFormat("$ 0.00");
        return formatter.format(this.total);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Productor getId_productor() {
		return id_productor;
	}

	public void setId_productor(Productor id_productor) {
		this.id_productor = id_productor;
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
