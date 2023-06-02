<script>
	import { cart, del, update } from "./cart";
	import CartItem from "./CartItem.svelte";

	let cartValue, subtotal, discountTotal, total

	cart.subscribe(value => {
		cartValue = value
		subtotal = 0
		discountTotal = 0

		for (let product of Object.values(value)) {
			subtotal += product.price * product.amount
			discountTotal += product.price / 100 * (product.discount ? product.discount : 0) * product.amount
		}

		total = subtotal - discountTotal
	})
</script>

<svelte:head>
   <title>Корзина - VELO</title>
</svelte:head>

{#if Object.entries(cartValue).length == 0}
	<div class="row justify-content-center empty mt-5">
		<div class="col-xxl-5 col-xl-5 col-lg-5 col-md-5 col-sm-5 text-center">
			<h1>В корзине пусто</h1>
			<a class="btn-second" href="/bikes">Начать покупки</a>
		</div>
	</div>
{:else}
	<div class="row justify-content-center">
		<div class="col-xxl-5 col-xl-5 col-lg-6 col-11 products">
			{#each Object.entries(cartValue) as [id, product]}
				<CartItem {id} {product} />
			{/each}
		</div>
		<div class="col-xxl-3 col-xl-3 col-lg-4 col-11 general">
			<h3>Итог</h3>
			<div class="d-flex sub">
				<p class="head">Подитог</p>
				<p class="ms-auto">{subtotal}₽</p>
			</div>
			<div class="d-flex sub">
				<p class="head">Скидка</p>
				<p class="ms-auto">{discountTotal}₽</p>
			</div>
			<div class="d-flex" style="font-size: 15pt; margin-top: 20pt;">
				<p class="head">Расчетная сумма</p>
				<p class="ms-auto">{total}₽</p>
			</div>
			<a href="/checkout" class="btn btn-second">Перейти к оформлению</a>
		</div>
	</div>
{/if}


<style>
	.empty h1 {
		margin-bottom: 20pt;
	}

	.products, .general {
		background-color: var(--surface1);
		padding: 20pt;
		border-radius: 10pt	;
	}

	.products {
		margin-right: 10pt;
		padding: 20pt 20pt 10pt 20pt;
		height: fit-content;
	}

	.general {
		height: fit-content;
	}

	h3 {
		margin-bottom: 20pt;
	}

	.sub {
		color: var(--secondary);
		margin-bottom: 5pt;
	}

	p {
		margin: 0;
	}

	.head {
		font-weight: 500;
	}

	.btn {
		margin-top: 15pt;
		width: 100%;
	}

	@media(max-width: 992px) {
		.products {
			margin-right: 0;
			margin-bottom: 20pt;
		}
	}
</style>