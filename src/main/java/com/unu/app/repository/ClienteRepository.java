package com.unu.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.app.entity.Cliente;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable> {

	@Query("SELECT c FROM Cliente c WHERE c.dni LIKE CONCAT(:busqueda, '%')")
	public Page<Cliente> BuscarClienteByDNI(Pageable pageable, @Param("busqueda") String busqueda);
	@Query("SELECT c FROM Cliente c WHERE c.nombre LIKE CONCAT(:busqueda, '%')")
	public Page BuscarClienteByNombre(Pageable pageable, @Param("busqueda") String busqueda);
	@Query("SELECT c FROM Cliente c WHERE c.apellido LIKE CONCAT(:busqueda, '%')")
	public Page<Cliente> BuscarClienteByApellido(Pageable pageable, @Param("busqueda") String busqueda);
	@Query("SELECT c FROM Cliente c WHERE c.correo = :busqueda")
	public Page<Cliente> BuscarClienteByCorreo(Pageable pageable, @Param("busqueda") String busqueda);
	@Query("SELECT c FROM Cliente c WHERE c.telefono = :busqueda")
	public Page<Cliente> BuscarClienteByTelefono(Pageable pageable, @Param("busqueda") String busqueda);

	@Query("SELECT c FROM Cliente c WHERE " + "(c.dni LIKE CONCAT( :busqueda, '%')) OR"
			+ "(c.nombre LIKE CONCAT(:busqueda, '%')) OR "
			+ "(c.apellido LIKE CONCAT(:busqueda, '%')) OR "
			+ "(c.correo LIKE CONCAT(:busqueda, '%')) OR " + "(c.telefono LIKE CONCAT(:busqueda, '%'))")
	public Page<Cliente> buscarByAll(Pageable pageable,  @Param("busqueda") String busqueda);

}
