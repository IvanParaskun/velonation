import { redirect } from '@sveltejs/kit'

/** @type {import('./$types').LayoutServerLoad} */
export async function load(event) {
   const user = event.locals.user

   if (!user)
      throw redirect(302, '/login')

   return { user }
 }