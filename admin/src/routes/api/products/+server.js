import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function DELETE(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/products/delete`, {
      method: 'DELETE',
      headers: {
         'Authorization': event.locals.user.token,
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(await event.request.json())
   })

   return json({ status: response.status, text: await response.text() })
}