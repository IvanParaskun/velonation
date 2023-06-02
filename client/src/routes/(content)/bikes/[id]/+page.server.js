/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   let { bike, status } = await event.fetch(`/api/bikes/bike?id=${event.params.id}`).then(response => response.json())

	if (status != 200)
		throw new Error()

	return { bike }
}