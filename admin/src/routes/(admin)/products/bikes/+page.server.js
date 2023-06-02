/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   return await event.fetch(`/api/products/bikes`).then(response => response.json())
}