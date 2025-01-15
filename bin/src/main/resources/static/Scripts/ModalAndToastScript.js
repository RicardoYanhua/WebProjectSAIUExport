

document.addEventListener('DOMContentLoaded', function() {
	const modal = document.getElementById('MessageConfirmModal');
	const modalTitle = document.getElementById('modalLabel');
	const modalBody = document.getElementById('modalBody');
	const deleteForm = document.getElementById('deleteForm');

	modal.addEventListener('show.bs.modal', function(event) {
		const button = event.relatedTarget;
		const Id = button.getAttribute('data-id');
		const title = button.getAttribute('data-title');
		const body = button.getAttribute('data-body');
		const url = button.getAttribute('data-url');
		modalTitle.textContent = title;
		modalBody.textContent = body;
		deleteForm.action = `${url}/${Id}`;
		
		
	});
});


let toastTimeoutId;

    function showToastMessage(message) {
        const toastElement = document.getElementById('validationToast');
        const toastMessage = document.getElementById('toastMessage');
        
        toastMessage.textContent = message;
        toastElement.classList.add('show');

        if (toastTimeoutId) {
            clearTimeout(toastTimeoutId);
        }

        toastTimeoutId = setTimeout(() => {
            toastElement.classList.remove('show');
            toastTimeoutId = null;
        }, 2000);
    }
