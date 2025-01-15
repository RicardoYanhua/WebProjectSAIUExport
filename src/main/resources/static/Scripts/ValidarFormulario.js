function ValidarFormularioProductor() {
	const dni = document.getElementById('InputDniProductor').value.trim();
	const nombre = document.getElementById('InputNombreProductor').value.trim();
	const apellido = document.getElementById('InputApellidoProductor').value.trim();
	const correo = document.getElementById('InputCorreoProductor').value.trim();
	const telefono = document.getElementById('InputTelefonoProductor').value.trim();
	const departamento = document.getElementById('departamentosSelect').value;
	const provincia = document.getElementById('provinciasSelect').value;
	const distrito = document.getElementById('distritosSelect').value;
	const direccion = document.getElementById('InputDireccionProductor').value.trim();
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


function ValidarFormularioUsuario() {
    const user = document.getElementById('InputUsuario').value.trim();
    const password = document.getElementById('InputContrasenia').value.trim();
    const rol = document.querySelector('input[name="rol"]:checked'); // Selecciona el radio button seleccionado
    const nombre = document.getElementById('InputNombreUsuario').value.trim();
    const apellido = document.getElementById('InputApellidoUsuario').value.trim();
    const correo = document.getElementById('InputCorreoUsuario').value.trim();

    if (!user) {
        showToastMessage('Por favor, ingrese el usuario.');
        return false;
    }
    if (!password) {
        showToastMessage('Por favor, ingrese la contraseña.');
        return false;
    }
    if (!rol) { // Verifica si algún radio button está seleccionado
        showToastMessage('Por favor, seleccione un rol.');
        return false;
    }
    if (!nombre) {
        showToastMessage('Por favor, ingrese el nombre.');
        return false;
    }
    if (!apellido) {
        showToastMessage('Por favor, ingrese el apellido.');
        return false;
    }
    if (!correo) {
        showToastMessage('Por favor, ingrese el correo.');
        return false;
    }

    return true;
}


function ValidarFormularioSemilla() {
	const productor = document.getElementById('isSelected').value.trim();
	const nombre = document.getElementById('InputNombreSemilla').value.trim();
	const descripcion = document.getElementById('InputDescripcionSemilla').value.trim();
	const precio = document.getElementById('InputPrecioSemilla').value.trim();
	const cantidad = document.getElementById('InputCantidadSemilla').value.trim();
	
	
	if (productor === "False") {
		showToastMessage('Debe seleccionar un Prodcutor de la lista. ');
		return false;
	}
	if (!nombre) {
		showToastMessage('Por favor, complete el campo NOMBRE SEMILLA.');
		return false;
	}
	if (!descripcion) {
		showToastMessage('Por favor, complete el campo DESCRIPCION SEMILLA.');
		return false;
	}
	if (!precio) {
		showToastMessage('Por favor,complete el campo PRECIO SEMILLA.');
		return false;
	}
	if (!cantidad) {
		showToastMessage('Por favor, complete el campo CANTIDAD SEMILLA.');
		return false;
	}
	return true;
}


function ValidarFormularioDetalle() {

			const productor = document.getElementById('InputProductor');
			const productor_text = document
					.getElementById('InputTextProductor').value.trim();
			const semilla = document.getElementById('InputSemilla').value
					.trim();
			const cantidad = document.getElementById('InputCantidad').value
					.trim();

			if (!productor_text) {
				showToastMessage('Por favor, seleccione un productor');
				return false;
			}
			if (productor.value == false) {
				showToastMessage('Por favor, seleccione un productor2');
				return false;
			}
			if (!semilla || semilla === "False") {
				showToastMessage('Por favor, seleccione una semilla');
				return false;
			}
			if (!cantidad) {
				showToastMessage('Por favor, Inserte la Cantidad');
				return false;
			}

			return true;
		}