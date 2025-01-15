package com.unu.app.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unu.app.entity.Productor;
import com.unu.app.entity.Compra;
import com.unu.app.repository.CompraRepository;
import com.unu.app.service.CompraService;

@Service("compraService")

public class CompraServiceImpl implements CompraService {

	@Autowired
	@Qualifier("compraRepository")
	private CompraRepository compraRepository;

	@Override
	public List<Compra> getListaCompras() {
		return compraRepository.findAll();
	}

	@Override
	public Compra InsertarCompra(Compra compra) {
		return compraRepository.save(compra);
	}

	@Override
	public void EliminarCompra(int id) {
		if (compraRepository.existsById(id)) {
			compraRepository.deleteById(id);
		}
	}

	@Override
	public Compra ObtenerCompra(int id) {
		return compraRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Compra no encontrado con ID: " + id));
	}

	@Override
	public void ActualizarCompra(Compra compra) {
		compraRepository.save(compra);
	}

	@Override
	public int getLastId() {
		return compraRepository.obtenerSiguienteId();
	}

	@Override
	public List<Compra> getListaCompraByIdProductor(Productor cliente) {
		return compraRepository.getListaCompraByIdProductor(cliente);
	}

	@Override
	public boolean isExistCompra(int id) {
		return compraRepository.existsById(id);
		
	}

}
