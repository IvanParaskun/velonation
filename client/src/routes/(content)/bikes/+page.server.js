/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   let { bikes, status } = await event.fetch('/api/bikes').then(response => response.json())
	let infoResponse = await event.fetch('/api/bikes/info').then(response => response.json())
	let categoriesResponse = await event.fetch('/api/bikes/categories').then(response => response.json())
	
	if (status != 200 || infoResponse.status != 200 || categoriesResponse.status != 200)
		throw new Error()

	return { 
		bikes, 
		info: infoResponse.info, 
		categories: categoriesResponse.categories
	}
}