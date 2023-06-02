import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/products/scooters/${event.url.searchParams.get('id')}`, {
      headers: {
         'Authorization': event.locals.user.token
      }
   })

   return json({ scooter: response.status == 200 ? await response.json() : null, status: response.status })
}

/** @type {import('./$types').RequestHandler} */
export async function POST(event) {
   let data = await event.request.formData()

	let response = await fetch(`${SERVER}/api/v1/admin/products/create/scooter`, {
		method: 'POST',
		headers: {
         'Authorization': event.locals.user.token
      },
		body: data
	})

   return json(await response.json())
}

/** @type {import('./$types').RequestHandler} */
export async function PUT(event) {
   let data = await event.request.formData()
	
	let response = await fetch(`${SERVER}/api/v1/admin/products/update/scooter`, {
		method: 'PUT',
		headers: {
         'Authorization': event.locals.user.token
      },
		body: data
	})

   return json(await response.json())
}