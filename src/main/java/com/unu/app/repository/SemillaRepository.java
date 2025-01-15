package com.unu.app.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unu.app.entity.Semilla;

@Repository("semillaRepository")
public interface SemillaRepository extends JpaRepository<Semilla, Serializable> {
	
}
