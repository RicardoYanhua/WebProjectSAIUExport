package com.unu.app.entity.Ubicacion;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class GeoClientService {

	private ObjectMapper objectMapper = new ObjectMapper();

	public List<Departamento> getDepartamentos() throws IOException {
		ClassPathResource resource = new ClassPathResource("Ubicacion/ubigeo_peru_2016_departamentos.json");
		byte[] jsonData = Files.readAllBytes(Paths.get(resource.getURI()));
		return objectMapper.readValue(jsonData,
				objectMapper.getTypeFactory().constructCollectionType(List.class, Departamento.class));
	}

	public List<Provincia> getProvincias() throws IOException {
		ClassPathResource resource = new ClassPathResource("Ubicacion/ubigeo_peru_2016_provincias.json");
		byte[] jsonData = Files.readAllBytes(Paths.get(resource.getURI()));
		return objectMapper.readValue(jsonData,
				objectMapper.getTypeFactory().constructCollectionType(List.class, Provincia.class));
	}

	public List<Distrito> getDistritos() throws IOException {
		ClassPathResource resource = new ClassPathResource("Ubicacion/ubigeo_peru_2016_distritos.json");
		byte[] jsonData = Files.readAllBytes(Paths.get(resource.getURI()));
		return objectMapper.readValue(jsonData,
				objectMapper.getTypeFactory().constructCollectionType(List.class, Distrito.class));
	}

	public Departamento getDepartamentoById(String departamentoId) throws IOException {
		return getDepartamentos().stream().filter(departamento -> departamento.getId().equals(departamentoId))
				.findFirst().orElse(null);
	}

	public String getDepartamentoNameById(String id) throws IOException {
		return getDepartamentos().stream().filter(departamento -> departamento.getId().equals(id)).findFirst()
				.map(Departamento::getName).orElse("Desconocido");
	}

	public String getProvinciaNameById(String id) throws IOException {
		return getProvincias().stream().filter(provincia -> provincia.getId().equals(id)).findFirst()
				.map(Provincia::getName).orElse("Desconocido");
	}

	public String getDistritoNameById(String id) throws IOException {
		return getDistritos().stream().filter(distrito -> distrito.getId().equals(id)).findFirst()
				.map(Distrito::getName).orElse("Desconocido");
	}

	public Provincia getProcinciaById(String procinciaId) throws IOException {
		return getProvincias().stream().filter(provincia -> provincia.getId().equals(procinciaId)).findFirst()
				.orElse(null);
	}

	public Distrito getDistritosById(String distritoId) throws IOException {
		return getDistritos().stream().filter(distrito -> distrito.getId().equals(distritoId)).findFirst().orElse(null);
	}

	public List<Provincia> getProvinciasByDepartamentoId(String departamentoId) throws IOException {
		return getProvincias().stream().filter(provincia -> provincia.getDepartment_id().equals(departamentoId))
				.toList();
	}

	public List<Distrito> getDistritosByProvinciaId(String provinciaId) throws IOException {
		return getDistritos().stream().filter(distrito -> distrito.getProvince_id().equals(provinciaId)).toList();
	}
}