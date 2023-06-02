/** @type {import('@sveltejs/kit').Handle} */
export async function handle({ event, resolve }) {
	const token = event.cookies.get('AuthorizationToken');

	if (token)
		event.locals.user = { token };
	else
		event.locals.user = null

	return resolve(event)
} 
