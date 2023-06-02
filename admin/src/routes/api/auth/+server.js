import { SERVER } from '$env/static/private'
import { json } from '@sveltejs/kit'

/** @type {import('./$types').RequestHandler} */
export async function POST({ request }) {
   let { username, password } = await request.json()
   let token = `Basic ${btoa(username + ':' + password)}`
   let response = await fetch(`${SERVER}/api/v1/admin/auth`, {
      headers: {
         'Authorization': token
      }
   })

   return json({ success: response.status == 200, token: response.status == 200 ? token : null })
}
