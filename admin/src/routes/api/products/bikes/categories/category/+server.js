import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/products/bikes/categories/${event.url.searchParams.get('id')}`, {
      headers: {
         'Authorization': event.locals.user.token
      }
   })

   return json({ category: response.status == 200 ? await response.json() : null, status: response.status })
}

/** @type {import('./$types').RequestHandler} */
export async function POST(event) {
   let data = await event.request.formData()

	let response = await fetch(`${SERVER}/api/v1/admin/products/create/category`, {
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
	
	let response = await fetch(`${SERVER}/api/v1/admin/products/update/category`, {
		method: 'PUT',
		headers: {
         'Authorization': event.locals.user.token
      },
		body: data
	})

   return json(await response.json())
}

/** @type {import('./$types').RequestHandler} */
export async function DELETE(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/products/delete/category`, {
      method: 'DELETE',
      headers: {
         'Authorization': event.locals.user.token,
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(await event.request.json())
   })

   return json({ status: response.status, text: await response.text() })
}
