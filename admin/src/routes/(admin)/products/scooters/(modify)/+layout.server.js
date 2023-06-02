/** @type {import('./$types').LayoutServerLoad} */
export async function load(event) {
   return await event.fetch(`/api/products/scooters/scooter?id=${event.params.id}`).then(response => response.json())
}