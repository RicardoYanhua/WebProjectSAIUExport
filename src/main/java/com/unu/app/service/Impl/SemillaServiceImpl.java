package com.unu.app.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unu.app.entity.Semilla;
import com.unu.app.repository.SemillaRepository;
import com.unu.app.service.SemillaService;

@Service("semillaService")

public class SemillaServiceImpl implements SemillaService {

	@Autowired
	@Qualifier("semillaRepository")
	private SemillaRepository semillaRepository;

	@Override
	public Semilla InsertarSemilla(Semilla semilla) {
		return semillaRepository.save(semilla);
	}

	@Override
	public void EliminarSemilla(int id) {
		if (semillaRepository.existsById(id)) {
			semillaRepository.deleteById(id);
		}
	}

	@Override
	public void ActualizarSemilla(Semilla semilla) {
		semillaRepository.save(semilla);
		
	}

	@Override
	public Semilla ObtenerSemilla(int id) {
		 return semillaRepository.findById(id)
		            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}

	@Override
	public Page ListaSemilla(Pageable pageable) {
		Page<Semilla> listaProductos = semillaRepository.findAll(PageRequest.of(pageable.getPageNumber(), 12));
		return listaProductos;
	}
	
	@Override
	public List<Semilla> getSemillas() {
		List<Semilla> listaProductos = semillaRepository.findAll();
		return listaProductos;
	}
	
	
}
