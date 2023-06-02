import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function GET(event) {
	let shopInfo = await fetch(`${SERVER}/api/v1/info`).then(response => response.json())
	return json({ shopInfo })
}

/** @type {import('./$types').RequestHandler} */
export async function POST(event) {
   let response = await fetch(`${SERVER}/api/v1/admin/info`, {
      method: 'POST',
      headers: {
         'Authorization': event.locals.user.token,
         'Content-Type': 'application/json'
      },
      body: JSON.stringify(await event.request.json())
   })

	let shopInfo = await response.json()

	return json({ shopInfo })
}