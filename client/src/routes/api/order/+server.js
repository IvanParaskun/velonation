import { API } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').ReuqestHandler} */
export async function POST(event) {
	let data = await fetch(`${API}/orders/create`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(await event.request.json())
	}).then(response => response.json())

	return json(data)
}