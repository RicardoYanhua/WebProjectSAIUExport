
function AddClearTextFunction(inputId, buttonClass) {
	const inputElement = document.getElementById(inputId);
	const btnClearText = document.querySelector(buttonClass);
	inputElement.addEventListener('input', function() {
		if (inputElement.value.trim() !== '') {
			btnClearText.style.display = 'block';
		} else {
			btnClearText.style.display = 'none';
		}
	});
	
	inputElement.addEventListener('mouseenter', function() {
	        if (inputElement.value.trim() !== '') {
	            btnClearText.style.display = 'block';
	        }
	    });
	
	btnClearText.addEventListener('click', function() {
		inputElement.value = ''; 
		btnClearText.style.display = 'none';
	});
}

function ToggleVisibilityPassword(button) {
	const input = document.getElementById("InputPassword");
	if (input.type === "password") { input.type = "text"; }
	else { input.type = "password"; }
	button.classList.toggle("showPassword");
}
