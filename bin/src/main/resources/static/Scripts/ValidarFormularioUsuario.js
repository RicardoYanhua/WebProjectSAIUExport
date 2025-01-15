function ValidarFormularioUsuario() {
	// Obtención de los valores de los campos
	const user = document.getElementById('InputUsuario').value.trim();
	const password = document.getElementById('InputContrasenia').value.trim();
	const rol = document.getElementById('InputRol').value;
	const nombre = document.getElementById('InputNombreUsuario').value.trim();
	const apellido = document.getElementById('InputApellidoUsuario').value.trim();
	const correo = document.getElementById('InputCorreoUsuario').value.trim();

	// Validaciones de los campos
	if (!user) {
		showToastMessage('Por favor, ingrese el usuario.');
		return false;
	}
	if (!password) {
		showToastMessage('Por favor, ingrese la contraseña.');
		return false;
	}
	if (!rol) {
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

	return true; // Si todas las validaciones son correctas, se envía el formulario
}