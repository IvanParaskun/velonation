import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/orders`, {
      headers: {
         'Authorization': event.locals.user.token
      }
   })

	return json({ orders: response.status == 200 ? await response.json() : null, status: response.status })
}

/** @type {import('./$types').RequestHandler} */
export async function DELETE(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/orders/delete`, {
      method: 'DELETE',
      headers: {
         'Authorization': event.locals.user.token,
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(await event.request.json())
   })

	return json({ status: response.status, text: await response.text() })
}