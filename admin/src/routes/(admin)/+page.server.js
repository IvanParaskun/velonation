/** @type {import('./$types').LayoutServerLoad} */
export async function load(event) {
	let { shopInfo } = await event.fetch('/api/info').then(response => response.json())

   return { shopInfo }
 }