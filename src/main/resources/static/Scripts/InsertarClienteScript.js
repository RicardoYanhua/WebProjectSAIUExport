// Cargar los departamentos al cargar la página
$(document).ready(function() {
	
	// Obtener los departamentos
	$.get('/Ubicaciones/getDepartamentos', function(data) {
		const departamentosSelect = $('#departamentosSelect');
		departamentosSelect.empty();
		departamentosSelect.append('<option disabled selected value="">Seleccione una opción...</option>');
		data.forEach(function(departamento) {
			departamentosSelect.append(
				`<option value="${departamento.id}">${departamento.name}</option>`
			);
		});
	});

	// Actualizar las provincias al seleccionar un departamento
	$('#departamentosSelect').change(function() {
		const departamentoId = $(this).val(); // Obtener el ID del departamento seleccionado

		if (departamentoId) {
			$.get('/Ubicaciones/getProvincias', { ID: departamentoId }, function(data) {
				const provinciasSelect = $('#provinciasSelect');
				provinciasSelect.empty();
				provinciasSelect.append('<option disabled selected value="">Seleccione una opción...</option>');
				data.forEach(function(provincia) {
					provinciasSelect.append(
						`<option value="${provincia.id}">${provincia.name}</option>`
					);
				});

				// Reiniciar el select de distritos cuando cambie el departamento
				$('#distritosSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
			}).fail(function() {
				alert('Error al cargar las provincias.');
			});
		} else {
			// Reiniciar provincias y distritos si no hay selección
			$('#provinciasSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
			$('#distritosSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
		}
	});

	// Actualizar los distritos al seleccionar una provincia
	$('#provinciasSelect').change(function() {
		const provinciaId = $(this).val(); // Obtener el ID de la provincia seleccionada

		if (provinciaId) {
			$.get('/Ubicaciones/getDistritos', { ID: provinciaId }, function(data) {
				const distritosSelect = $('#distritosSelect');
				distritosSelect.empty();
				distritosSelect.append('<option disabled selected value="">Seleccione una opción...</option>');
				data.forEach(function(distrito) {
					distritosSelect.append(
						`<option value="${distrito.id}">${distrito.name}</option>`
					);
				});
			}).fail(function() {
				alert('Error al cargar los distritos.');
			});
		} else {
			// Reiniciar distritos si no hay selección
			$('#distritosSelect').empty().append('<option disabled selected value="">Seleccione una opción...</option>');
		}
	});
});
