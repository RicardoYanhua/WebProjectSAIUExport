function ValidarFormularioProductor() {
	const dni = document.getElementById('InputDniCliente').value.trim();
	const nombre = document.getElementById('InputNombreCliente').value
		.trim();
	const apellido = document.getElementById('InputApellidoCliente').value
		.trim();
	const correo = document.getElementById('InputCorreoCliente').value
		.trim();
	const telefono = document.getElementById('InputTelefonoCliente').value
		.trim();
	const departamento = document.getElementById('departamentosSelect').value;
	const provincia = document.getElementById('provinciasSelect').value;
	const distrito = document.getElementById('distritosSelect').value;
	const direccion = document.getElementById('InputDireccionCliente').value
		.trim();

	if (!dni) {
		showToastMessage('Por favor, ingrese el DNI del cliente.');
		return false;
	}

	if (!nombre) {
		showToastMessage('Por favor, ingrese el nombre del cliente.');
		return false;
	}

	if (!apellido) {
		showToastMessage('Por favor, ingrese el apellido del cliente.');
		return false;
	}

	if (!correo) {
		showToastMessage('Por favor, ingrese el correo del cliente.');
		return false;
	}

	if (!telefono) {
		showToastMessage('Por favor, ingrese el teléfono del cliente.');
		return false;
	}

	if (!departamento) {
		showToastMessage('Por favor, seleccione un departamento.');
		return false;
	}

	if (!provincia) {
		showToastMessage('Por favor, seleccione una provincia.');
		return false;
	}

	if (!distrito) {
		showToastMessage('Por favor, seleccione un distrito.');
		return false;
	}

	if (!direccion) {
		showToastMessage('Por favor, ingrese la dirección del cliente.');
		return false;
	}

	return true;
}