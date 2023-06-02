import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/products/bikes`, {
      headers: {
         'Authorization': event.locals.user.token
      }
   })

	return new Response(JSON.stringify({ bikes: response.status == 200 ? await response.json() : null, status: response.status }), {
		headers: {
			'Access-Control-Allow-Origin': '*'
		}
	})
}