import { API } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
	let response = await fetch(`${API}/products/scooters/${event.url.searchParams.get('id')}`)
	return json({ scooter: response.status == 200 ? await response.json() : null, status: response.status })
}