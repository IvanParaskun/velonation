import { writable } from 'svelte/store';

export const cart = writable({})

export const add = (product) => {
	cart.update(cart => {
		cart[product.id] = {
			name: product.name,
			price: product.price,
			discount: product.discount,
			imageUrl: product.imageUrl,
			amount: 1
		}
		return cart
	})
}

export const update = (id, amount) => {
	cart.update(cart => {
		cart[id].amount = amount
		return cart
	})
}

export const del = (id) => {
	cart.update(cart => {
		delete cart[id]
		return cart
	})
}