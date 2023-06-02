<script>
	import { lazyLoad } from "$lib/LazyLoad"
	import { cart, add } from "../cart/cart"

	export let accessory
	
	let cartValue
	let discount = accessory.price / 100 * (accessory.discount ? accessory.discount : 0)
	let price = Math.ceil(accessory.price - discount)

	cart.subscribe(value => {
		cartValue = value
	})
</script>

<div class="accessory">
	<div class="image">
		<img use:lazyLoad={accessory.imageUrl} alt="{accessory.name} image" />
		<button class="btn-primary" on:click={() => {add(accessory)}} disabled={accessory.id in cartValue}>
			{#if accessory.id in cartValue}
				В корзине
			{:else}
				Добавить
			{/if}
		</button>
	</div>
	<h4>{accessory.name}</h4>
	<div class="price">
		{#if discount}
			<p><s>{price}₽</s></p>
			<p class="new-price">{price - discount}₽</p>
		{:else}
			<p>{price}₽</p>
		{/if}
	</div>
</div>

<style>
	.accessory:hover {
		cursor: pointer;
	}

	.accessory:hover h4 {
		color: var(--primary);
	}

	.accessory:hover .image img {
		display: none;
	}

	.accessory:hover .image button {
		display: flex;
	}

	.accessory .image {
		display: flex;
		width: 100%;
		height: 170pt;
		border-radius: 10pt;
		background-color: white;
		box-shadow: 1pt 1pt 0.5em rgba(0, 0, 0, 0.2);
	}

	.accessory .image img {
		width: 90%;
		margin: auto auto;
		transition: 0.3s;
	}

	.accessory .image button {
		margin: auto auto;
		display: none;
	}

	.accessory h4 {
		font-size: 15pt;
		font-weight: 500;
		margin-top: 10pt;
		transition: 0.3s;
	}

	.accessory .price {
		display: flex;
		font-size: 15pt;
		font-weight: 400;
	}

	.accessory .price .new-price {
		color: var(--error);
		margin-left: 10pt;
	}

	@media(max-width: 768px) {
		.accessory .image {
			height: 200pt;
		}
	}

	@media(max-width: 650px) {
		.accessory .image {
			height: 150pt;
		}
	}

	@media(max-width: 576px) {
		.accessory .image {
			height: 250pt;
		}
	}

	@media(max-width: 450px) {
		.accessory .image {
			height: 200pt;
		}
	}

	@media(max-width: 320px) {
		.accessory .image {
			height: 150pt;
		}
	}
</style>
