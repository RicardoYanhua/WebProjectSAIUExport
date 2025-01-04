package com.unu.app.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

enum Role {
    admin,
    empleado
}

@Entity
@Table(name = "usuarios")
public class Usuario {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name= "id")
		private int id;
		
		@Column(name = "usuario")
		private String usuario;
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name = "apellido")
		private String apellido;
		
		@Column(name = "correo")
		private String correo;
		
		@Column(name = "contrasenia")
		private String contrasenia;
		
		@Enumerated(EnumType.STRING)
		@Column(name = "rol", nullable = false)
		private Role rol;
		
		@Column(name = "fecha_registro")
		private LocalDateTime fecha_registro;
		
		public Usuario() {
			this(0,"","","","","",Role.empleado,LocalDateTime.now());
		}
		
		public Usuario(int id, String usuario, String nombre, String apellido, String correo, String contrasenia,
				Role rol, LocalDateTime fecha_registro) {
			super();
			this.id = id;
			this.usuario = usuario;
			this.nombre = nombre;
			this.apellido = apellido;
			this.correo = correo;
			this.contrasenia = contrasenia;
			this.rol = rol;
			this.fecha_registro = fecha_registro;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
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

		public String getContrasenia() {
			return contrasenia;
		}

		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}

		public Role getRol() {
			return rol;
		}

		public void setRol(Role rol) {
			this.rol = rol;
		}

		public LocalDateTime getFecha_registro() {
			return fecha_registro;
		}

		public void setFecha_registro(LocalDateTime fecha_registro) {
			this.fecha_registro = fecha_registro;
		}
		
		
		
		
		
			
		
}
