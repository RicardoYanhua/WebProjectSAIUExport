package com.unu.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unu.app.entity.Semilla;

public interface SemillaService {
	
	public abstract Page ListaSemilla(Pageable pageable);
	public abstract Semilla InsertarSemilla(Semilla semilla);
	public abstract void EliminarSemilla(int id);
	public abstract void ActualizarSemilla(Semilla semilla);
	public abstract Semilla ObtenerSemilla(int id);
	public abstract List<Semilla> getSemillas();
	
}
