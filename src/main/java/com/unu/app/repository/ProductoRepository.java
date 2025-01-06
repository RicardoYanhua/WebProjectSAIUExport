package com.unu.app.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unu.app.entity.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Serializable> {
}
