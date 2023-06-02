<script>
	export let product
	export let amount

	let price = (product.price - product.price / 100 * (product.discount ? product.discount : 0)) * amount
</script>

<div class="product d-flex">
	<img class="me-3" src="{product.imageUrl}" alt="Cart image of {product.name}">
	<div class="info my-auto">
		<h4>{product.name}</h4>
		<div class="d-flex">
			<p>x{amount}</p>
			<p class="ms-auto my-auto price">{price}₽</p>
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

	.price {
		font-size: 14pt;
	}

	@media(max-width: 500px) {
		img {
			display: none;
		}
	}
</style>