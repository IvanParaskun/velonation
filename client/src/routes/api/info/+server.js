import { API } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
	let shopInfo = await fetch(`${API}/info`).then(response => response.json())
	return json(shopInfo)
}