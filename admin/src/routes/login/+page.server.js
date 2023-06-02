import { fail, redirect } from '@sveltejs/kit'

/** @type {import('./$types').PageServerLoad} */
export async function load(event) {
   const user = event.locals.user

   if (user)
      throw redirect(302, '/')
}

/** @type {import('./$types').Actions} */
export const actions = {
   login: async (event) => {
      let form = await event.request.formData()
      const formData = Object.fromEntries(form)

      if (!formData.username || !formData.password)
         return fail(400, { formData, error: 'Missing required fields' })

      let username = formData.username
      let password = formData.password
      let body = { username, password }

      let { success, token } = await event.fetch('/api/auth', {
         method: 'POST',
         headers: {
            'Content-Type': 'application/json'
         },
         body: JSON.stringify(body)
      }).then(response => response.json())

      if (!success)
         return fail(400, {formData, error: 'Wrong credentials'})

      event.cookies.set('AuthorizationToken', token, {
         httpOnly: true,
         path: '/',
         secure: false,
         maxAge: 60 * 60 * 24
      })
      throw redirect(302, '/')
   }
}
