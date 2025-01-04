function ToggleMenuAction() {
	const toggleButton = document.getElementById('toggle-menu-button');
	const menuContainer = document.getElementById('Menu-container');
	isrotate = toggleButton.classList.toggle('rotate-icon');
	const ishidden = menuContainer.classList.toggle('hidden');
	
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
	const IconTogglerMenu = document.getElementById('toggle-menu-button');
	
	
	const isCollapsed = localStorage.getItem('menuIsHidden') === 'true';
	
	if (isCollapsed) {
		MenuContainer.classList.add('hidden');
		IconTogglerMenu.classList.add('rotate-icon');
	} else {
		MenuContainer.classList.remove('hidden');
		IconTogglerMenu.classList.remove('rotate-icon');
	}
});

document.getElementById('toggle-menu-button').addEventListener('click', ToggleMenuAction);





