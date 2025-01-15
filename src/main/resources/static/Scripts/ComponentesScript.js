document.addEventListener('DOMContentLoaded', function() {
	const tableBody = document.querySelector('table tbody');
	const activateButton = document.querySelector('#activateButton');

	function toggleButtonState() {
		const rowCount = tableBody.querySelectorAll('tr').length;
		activateButton.disabled = rowCount <= 0;
	}

	toggleButtonState();

	const observer = new MutationObserver(toggleButtonState);
	observer.observe(tableBody, {
		childList: true
	});
});



document.addEventListener('click', function(event) {
	const dropdowns = document.querySelectorAll('.dropdown-menu.show');
	dropdowns.forEach(menu => {
		if (!menu.closest('.dropdown').contains(event.target)) {
			menu.classList.remove('show');
		}
	});
});

function filterOptions(input) {
	const container = input.closest('.dropdown');
	const query = input.value.toLowerCase();
	const menuItems = container.querySelectorAll('.dropdown-item:not(.no-results)');
	let hasResults = false;

	menuItems.forEach(item => {
		const itemText = item.textContent.toLowerCase();
		const isMatch = itemText.includes(query);
		item.style.display = isMatch ? 'block' : 'none';
		hasResults = hasResults || isMatch;
	});

	const noResults = container.querySelector('.no-results');
	noResults.classList.toggle('d-none', hasResults);

	const dropdownMenu = container.querySelector('.dropdown-menu');
	dropdownMenu.classList.toggle('show', query.length > 0);
}

function selectItem(element) {
	const container = element.closest('.dropdown');
	const input = container.querySelector('.search-input');
	const isSelected = container.querySelector('.is-selected');
	const clearButton = container.querySelector('.clear-button');

	input.value = element.textContent.trim();
	isSelected.value = 'True';
	input.setAttribute('readonly', true); // El input se vuelve de solo lectura al seleccionar un ítem
	clearButton.style.display = 'inline-block';
	container.querySelector('.dropdown-menu').classList.remove('show');
}

function clearInput(button) {
	const container = button.closest('.dropdown');
	const input = container.querySelector('.search-input');
	const isSelected = container.querySelector('.is-selected');
	const clearButton = button;

	input.value = '';
	isSelected.value = 'False';
	input.removeAttribute('readonly'); // Se permite la edición nuevamente al limpiar
	clearButton.style.display = 'none';
	container.querySelector('.dropdown-menu').classList.remove('show');
}





function FormatoDNI(event) {
	const input = event.target;
	input.value = input.value.replace(/[^0-9]/g, '').slice(0, 8);
}

function FormatoNumerosEnteros(event) {
	const input = event.target;
	input.value = input.value.replace(/[^0-9]/g, '');
}

function FormatoNumerosDecimales(event) {
	const input = event.target;
	// Elimina cualquier carácter que no sea número o punto decimal
	input.value = input.value.replace(/[^0-9.]/g, '');

	// Permite solo un punto decimal
	if ((input.value.match(/\./g) || []).length > 1) {
		input.value = input.value.replace(/\.+$/, ''); // Elimina el punto extra al final si hay más de uno
	}
}

function FormatoTelefono(event) {
	let input = event.target.value.replace(/\D/g, '');
	if (input.length > 9) {
		input = input.slice(0, 9);
	}
	let formattedInput = input.replace(/(\d{3})(?=\d)/g, '$1 ');
	event.target.value = formattedInput;
}

function AplicarFormtatos() {

	const IntegerInputs = document.querySelectorAll('.FormatoInteger');
	IntegerInputs.forEach(input => {
		input.addEventListener('input', FormatoNumerosEnteros);
	});

	const DoubleInputs = document.querySelectorAll('.FormatoDouble');
	DoubleInputs.forEach(input => {
		input.addEventListener('input', FormatoNumerosDecimales);
	});

	const DNIInputs = document.querySelectorAll('.FormatoDni');
	DNIInputs.forEach(input => {
		input.addEventListener('input', FormatoDNI);
	});

	const TelefonoInputs = document.querySelectorAll('.FormatoTelefono');
	TelefonoInputs.forEach(input => {
		input.addEventListener('input', FormatoTelefono);
	});
}


document.addEventListener('DOMContentLoaded', AplicarFormtatos);