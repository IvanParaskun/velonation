<script>
	import CheckoutItem from "./CheckoutItem.svelte";
	import { cart } from "../cart/cart";
   import { imask } from '@imask/svelte'

   let cartValue, total

	cart.subscribe(value => {
		cartValue = value
		let subtotal = 0
		let discountTotal = 0

		for (let product of Object.values(value)) {
			subtotal += product.price * product.amount
			discountTotal += product.price / 100 * (product.discount ? product.discount : 0) * product.amount
		}

		total = subtotal - discountTotal
	})

	let order = {
		id: null,
		cart: null,
		email: null,
		phoneNumber: null,
		deliveryInfo: null
	}
	let error = null

	$: deliveryCost = order.deliveryInfo ? 500 : 0;

	let promise = null
	async function submit() {
		order.cart = mapCart(cartValue)

		promise = fetch('/api/order', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(order)
		})

		let result = await promise.then(response => response.json())
		
		if (result.statusCode == "CREATED")
			order.id = result.message
		else
			error = true
	}

	function mapCart(cart) {
		let mapped = {}

		for (let [key, value] of Object.entries(cart))
			mapped[key] = value.amount

		return mapped
	}
</script>

<svelte:head>
   <title>Оформление заказа - VELO</title>
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
			<a href="/cart">Назад</a>

			<hr>

			{#each Object.entries(cartValue) as [_, product]}
				<CheckoutItem amount={product.amount} {product} />
			{/each}

			<hr>

			<form id="order" on:submit|preventDefault={submit}>
				<div class="mb-3">
					<label for="email" class="form-label">Email</label>
					<input placeholder="username@domain.zone" type="email" class="form-control" id="email" bind:value={order.email} minlength="3" maxlength="100" required>
				</div>

				<div class="mb-3">
					<label for="phone" class="form-label">Номер телефона</label>
					<input placeholder="+7(000) 000-0000" use:imask={{ mask: '+7(000) 000-0000', lazy: true }} type="text" class="form-control" id="phone" bind:value={order.phoneNumber} minlength="16" maxlength="16" required>
				</div>

				{#if order.deliveryInfo}
					<div class="mb-3">
						<label for="name" class="form-label">Имя</label>
						<input placeholder="Иван" type="text" class="form-control" id="name" bind:value={order.deliveryInfo.name} minlength="2" maxlength="50" required>
					</div>

					<div class="mb-3">
						<label for="street" class="form-label">Улица</label>
						<input placeholder="пр. Димитрова" type="text" class="form-control" id="street" bind:value={order.deliveryInfo.street} minlength="3" maxlength="50" required>
					</div>

					<div class="mb-3">
						<label for="houseNumber" class="form-label">Дом</label>
						<input placeholder="5" type="text" class="form-control" id="houseNumber" bind:value={order.deliveryInfo.houseNumber} minlength="1" maxlength="100" required>
					</div>

					<div class="mb-3">
						<label for="apartments" class="form-label">Квартира</label>
						<input type="text" class="form-control" id="apartments" bind:value={order.deliveryInfo.apartments} minlength="1" maxlength="10">
					</div>

					<button class="btn-second" on:click={() => {order.deliveryInfo = null}}>Самовывоз</button>
				{:else}
					<button class="btn-second" on:click={() => {order.deliveryInfo = { name: null, street: null, houseNumber: null, apartments: null }}}>+ Доставка</button>
				{/if}
			</form>
		</div>
		<div class="col-xxl-3 col-xl-3 col-lg-4 col-11 general">				
			{#if order.id}
				<h4>Заказ зарегистрирован под номером #{order.id}</h4>
				<p class="my-4">
					Мы сохранили Ваш заказ и отправили его на обработку. На Вашу почту направлено письмо с информацией о заказе, однако, 
					мы рекомендуем сохранить номер заказа на случай нарушения работы почтового сервиса. Спасибо за Ваше доверие!
				</p>
				<a class="btn btn-second" href="/">Вернуться на главную</a>
			{:else}
				<h3>Итог</h3>
				<div class="d-flex sub">
					<p class="head">Подитог</p>
					<p class="ms-auto">{total}₽</p>
				</div>

				{#if deliveryCost != 0}
					<div class="d-flex sub">
						<p class="head">Доставка</p>
						<p class="ms-auto">{deliveryCost}₽</p>
					</div>
				{/if}
				
				<div class="d-flex" style="font-size: 15pt; margin-top: 20pt;">
					<p class="head">Расчетная сумма</p>
					<p class="ms-auto">{total + deliveryCost}₽</p>
				</div>
				<button type="submit" form="order" class="btn btn-primary">
					{#if promise}
						{#await promise}
							<div class="spinner-border" role="status" style="width: 15pt; height: 15pt;"></div>
						{:then} 
							Оформить
						{/await}
					{:else}
						Оформить
					{/if}
				</button>
				{#if error}<p class="error mt-3">Произошла ошибка, пожалуйста, повторите позже.</p>{/if}
			{/if}
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
		padding: 20pt;
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

	.error {
		color: var(--error);
	}

	@media(max-width: 992px) {
		.products {
			margin-right: 0;
			margin-bottom: 20pt;
		}
	}
</style>