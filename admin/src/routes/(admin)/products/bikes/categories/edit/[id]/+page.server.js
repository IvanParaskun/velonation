/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   return await event.fetch(`/api/products/bikes/categories/category?id=${event.params.id}`).then(response => response.json())
}