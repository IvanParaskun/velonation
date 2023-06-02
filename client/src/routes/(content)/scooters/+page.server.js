/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   let { scooters, status } = await event.fetch('/api/scooters').then(response => response.json())
	let infoResponse = await event.fetch('/api/scooters/info').then(response => response.json())

	if (status != 200 || infoResponse.status != 200)
		throw new Error()
	
	return { 
		scooters, 
		info: infoResponse.info
	}
}