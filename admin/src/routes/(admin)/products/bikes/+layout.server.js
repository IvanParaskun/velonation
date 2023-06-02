/** @type {import('./$types').LayoutServerLoad} */
export async function load(event) {
	return await event.fetch('/api/products/bikes/categories').then(response => response.json())
}