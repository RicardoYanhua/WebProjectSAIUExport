package com.unu.app.entity;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.unu.app.entity.Ubicacion.Departamento;
import com.unu.app.entity.Ubicacion.GeoClientService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	@Column(name = "dni")
	private String dni;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String  apellido;

	@Column(name = "correo")
	private String correo;

	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "id_departamento")
    private String id_departamento;
	
	@Column(name = "id_provincia")
    private String id_provincia;
	
	@Column(name = "id_distrito")
    private String id_distrito;
	
	@Column(name = "direccion")
    private String direccion;

	@Column(name = "fecha_registro")
	private LocalDateTime fecha_registro;
	
	public Cliente(int id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Cliente() {
		this(0,"","","","","","","","","",LocalDateTime.now());
	}

	public Cliente(int id, String dni, String nombre, String apellido, String correo, String telefono,
			String id_departamento, String id_provincia, String id_distrito, String direccion,
			LocalDateTime fecha_registro) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.id_departamento = id_departamento;
		this.id_provincia = id_provincia;
		this.id_distrito = id_distrito;
		this.direccion = direccion;
		this.fecha_registro = fecha_registro;
	}
	
	@Transient
	private String NombreDepartamento = "Desconocido";
	@Transient
	private String NombreProvincia = "Desconocido";
	@Transient
	private String NombreDistrito = "Desconocido";
	
	public void setNombreDepartamento(String name) {
		this.NombreDepartamento = name;
	}
	public void setNombreProvincia(String name) {
		this.NombreProvincia = name;
	}
	public void setNombreDistrito(String name) {
		this.NombreDistrito = name;
	}
	
	public String getUbicacion() {
		return "" + NombreDepartamento + " " + NombreProvincia + " " + NombreDistrito;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(String id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(String id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getId_distrito() {
		return id_distrito;
	}

	public void setId_distrito(String id_distrito) {
		this.id_distrito = id_distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDateTime fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	
	
	
}
