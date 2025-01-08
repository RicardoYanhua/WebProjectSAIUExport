package com.unu.app.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.unu.app.entity.Compra;

@Repository("compraRepository")
public interface CompraRepository extends JpaRepository<Compra, Serializable> {
	
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    public int ObtenerUltimoId();
}
