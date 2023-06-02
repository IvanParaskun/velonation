import { SERVER } from '$env/static/private'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
	let response = await fetch(`${SERVER}/api/v1/products/info/bikes`)

	return new Response(JSON.stringify({ info: response.status == 200 ? await response.json() : null, status: response.status }), {
		headers: {
			'Access-Control-Allow-Origin': '*'
		}
	})
}