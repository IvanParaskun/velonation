<script>
   import { page } from '$app/stores'

	$: path = $page.url.pathname
</script>

<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
	  	<a class="navbar-brand ms-5" class:active={path == "/"} href="/">VELO</a>

	  	<button class="navbar-toggler me-5" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
	  	</button>

	  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		 	<ul class="navbar-nav mx-auto">
				<li class="nav-item">
					<a class="nav-link" class:active={path == "/bikes"} aria-current="page" href="/bikes">Велосипеды</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" class:active={path == "/scooters"} aria-current="page" href="/scooters">Самокаты</a>
				</li>
				<li class="nav-item">
					<a class:active={path == "/accessories"} class="nav-link" aria-current="page" href="/accessories">Аксессуары</a>
				</li>
		 	</ul>

			<a class="cart me-5" href="/cart">
				<span class:active={path === '/cart'} class="material-symbols-outlined">shopping_bag</span>
			</a>
	  	</div>
	</div>
 </nav>

<style>
	nav {
		padding: 20pt 0;
	}

	.nav-link {
		padding: 7pt 20pt !important;
		border-radius: 5pt;
	}

	.nav-link:hover {
		background-color: var(--primary-container);
	}

	.active {
		color: var(--primary) !important;
	}

	.navbar-brand {
		font-size: 20pt;
		font-family: 'Nunito', sans-serif;
		font-weight: 500;
		margin-right: 0;
	}

	.material-symbols-outlined {
		font-size: 25pt;
		font-weight: 300;
	}

	.cart {
		margin-left: 15pt;
	}

	button {
		border: none !important;
		box-shadow: none !important;
	}
</style>