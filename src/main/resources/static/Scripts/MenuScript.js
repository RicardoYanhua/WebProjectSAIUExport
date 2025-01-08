function ToggleMenuAction() {
	const menuContainer = document.getElementById('Menu-container');
	const toggleButton = document.getElementById('ButtonMenuToggle');
	const textLogoHidden = document.getElementById('TextLogo');
	
	toggleButton.classList.toggle('RotateIconButtonMenuToggle');
	const ishidden = menuContainer.classList.toggle('HiddenMenu');
	textLogoHidden.classList.toggle('HiddenLogo');
	
	localStorage.setItem('menuIsHidden', ishidden);
}

function setActiveByIndex(index) {
	const menuLists = document.querySelectorAll('.MenuItems');
	menuLists.forEach(menuList => {
		const navLinks = menuList.querySelectorAll('.nav-item .nav-link');
		navLinks.forEach(link => link.classList.remove('active'));
		if (index >= 0 && index < navLinks.length) {
			navLinks[index].classList.add('active');
		}
	});
}

function checkWindowWidth() {
	const navbar = document.getElementById('navbarNav');
	const toggler = document.querySelector('.navbar-toggler');
	if (window.innerWidth >= 1024) {
		if (navbar.classList.contains('show')) {
			toggler.click();
		}
	}
}

window.addEventListener("resize", checkWindowWidth);

document.addEventListener('DOMContentLoaded', () => {
	const MenuContainer = document.getElementById('Menu-container');
	const toggleButton = document.getElementById('ButtonMenuToggle');
	const textLogoHidden = document.getElementById('TextLogo');
	
	const isCollapsed = localStorage.getItem('menuIsHidden') === 'true';
	
	
	if (isCollapsed) {
		MenuContainer.classList.add('HiddenMenu');
		toggleButton.classList.add('RotateIconButtonMenuToggle');
		textLogoHidden.classList.add('HiddenLogo');
	} else {
		MenuContainer.classList.remove('HiddenMenu');
		toggleButton.classList.remove('RotateIconButtonMenuToggle');
		textLogoHidden.classList.remove('HiddenLogo');
	}
});

document.getElementById('ButtonMenuToggle').addEventListener('click', ToggleMenuAction);





