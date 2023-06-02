/** @type {import('./$types').LayoutServerLoad} */
export async function load(event) {
   return await event.fetch(`/api/products/accessories/accessory?id=${event.params.id}`).then(response => response.json())
}