/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   let { scooter, status} = await event.fetch(`/api/scooters/scooter?id=${event.params.id}`).then(response => response.json())

	if (status != 200)
		throw new Error()
	
	return { scooter }
}