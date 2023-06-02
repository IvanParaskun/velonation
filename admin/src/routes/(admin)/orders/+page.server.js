/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   return await event.fetch(`/api/orders`).then(response => response.json())
}