package com.unu.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.app.entity.Cliente;
import com.unu.app.entity.Compra;

@Repository("compraRepository")
public interface CompraRepository extends JpaRepository<Compra, Serializable> {
	
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    public int ObtenerUltimoId();
	
	@Query("SELECT c FROM Compra c WHERE c.id_cliente = :idCliente")
    List<Compra> getListaCompraByIdCliente(@Param("idCliente") Cliente cliente);
}
