/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   return await event.fetch(`/api/products/accessories`).then(response => response.json())
}