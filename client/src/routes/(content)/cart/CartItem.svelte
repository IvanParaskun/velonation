<script>
	import { del, update } from "./cart";
	export let id
	export let product
</script>

<div class="product d-flex">
	<img src="{product.imageUrl}" alt="Cart image of {product.name}">
	<div class="info ms-3 my-auto">
		<h4>{product.name}</h4>
		<div class="d-flex">
			<div class="dropdown">
				<button class="btn dropdown-toggle btn-secondary" type="button" data-bs-toggle="dropdown" aria-expanded="false">
				  	x{product.amount}
				</button>
				<ul class="dropdown-menu">
				  	<li><p class="dropdown-item" on:click={() => {update(id, 1)}}>x1</p></li>
				  	<li><p class="dropdown-item" on:click={() => {update(id, 2)}}>x2</p></li>
				  	<li><p class="dropdown-item" on:click={() => {update(id, 3)}}>x3</p></li>
					<li><p class="dropdown-item" on:click={() => {update(id, 4)}}>x4</p></li>
					<li><p class="dropdown-item" on:click={() => {update(id, 5)}}>x5</p></li>
				</ul>
			</div>
			<p class="ms-auto my-auto price">{product.price}₽</p>
			<span on:click={() => {del(id)}} class="del my-auto ms-2 material-symbols-outlined">delete</span>
		</div>
	</div>
</div>

<style>
	.product {
		margin-bottom: 10pt;
	}

	.info {
		width: 100%;
	}

	img {
		width: 100pt;
	}

	h4 {
		font-size: 14pt;
	}

	p {
		margin: 0;
	}

	.dropdown-item:hover {
		cursor: pointer;
	}

	.price {
		font-size: 14pt;
	}

	.del {
		font-weight: 300;
		color: var(--error);
	}

	.del:hover {
		cursor: pointer;
	}

	@media(max-width: 500px) {
		img {
			display: none;
		}
	}
</style>