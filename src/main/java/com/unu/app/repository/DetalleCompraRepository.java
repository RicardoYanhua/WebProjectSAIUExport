package com.unu.app.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.app.entity.Compra;
import com.unu.app.entity.DetalleCompra;

@Repository("detalleCompraRepository")
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Serializable> {
	
	@Query("SELECT d FROM DetalleCompra d WHERE d.id_compra = :idCompra")
    List<DetalleCompra> ListaDetalleByIdCompra(@Param("idCompra") Compra compra);
	
	@Query("SELECT d FROM DetalleCompra d WHERE d.id_compra = :idCompra")
    List<DetalleCompra> ListaDetalleByIdCompra(@Param("idCompra") Long compra);
	
	@Query("SELECT SUM(d.subtotal) FROM DetalleCompra d WHERE d.id_compra.id = :idCompra")
    Double obtenerTotalPorCompra(@Param("idCompra") int idCompra);
	
	
}
