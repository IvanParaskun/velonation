/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
	let { info, status } = await event.fetch('/api/bikes/info').then(response => response.json())
   
	if (status != 200)
		throw new Error()

	return { info }
}