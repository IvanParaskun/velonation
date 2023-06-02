/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   let { accessories, status } = await event.fetch('/api/accessories').then(response => response.json())
	
	if (status != 200)
		throw new Error()

	return { accessories }
}