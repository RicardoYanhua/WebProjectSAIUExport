package com.unu.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.app.entity.Productor;

@Repository("productorRepository")
public interface ProductorRepository extends JpaRepository<Productor, Serializable> {

	@Query("SELECT p FROM Productor p WHERE p.dni LIKE CONCAT(:busqueda, '%')")
	public Page<Productor> BuscarProductorByDNI(Pageable pageable, @Param("busqueda") String busqueda);

	@Query("SELECT p FROM Productor p WHERE p.nombre LIKE CONCAT(:busqueda, '%')")
	public Page<Productor> BuscarProductorByNombre(Pageable pageable, @Param("busqueda") String busqueda);

	@Query("SELECT p FROM Productor p WHERE p.apellido LIKE CONCAT(:busqueda, '%')")
	public Page<Productor> BuscarProductorByApellido(Pageable pageable, @Param("busqueda") String busqueda);

	@Query("SELECT p FROM Productor p WHERE p.correo = :busqueda")
	public Page<Productor> BuscarProductorByCorreo(Pageable pageable, @Param("busqueda") String busqueda);

	@Query("SELECT p FROM Productor p WHERE p.telefono = :busqueda")
	public Page<Productor> BuscarProductorByTelefono(Pageable pageable, @Param("busqueda") String busqueda);

	@Query("SELECT p FROM Productor p WHERE " + "(p.dni LIKE CONCAT( :busqueda, '%')) OR"
	        + "(p.nombre LIKE CONCAT(:busqueda, '%')) OR "
	        + "(p.apellido LIKE CONCAT(:busqueda, '%')) OR "
	        + "(p.correo LIKE CONCAT(:busqueda, '%')) OR " + "(p.telefono LIKE CONCAT(:busqueda, '%'))")
	public Page<Productor> buscarByAll(Pageable pageable,  @Param("busqueda") String busqueda);

	@Query("SELECT new Productor(p.id,p.nombre,p.apellido) FROM Productor p ")
	public List<Productor> getListClientesNameAndId();


}
