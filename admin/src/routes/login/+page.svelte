<script>
   import { enhance } from '$app/forms';

   /** @type {import('./$types').ActionData} */
   export let form;
</script>   

<div class="container-fluid">
   <div class="row justify-content-center">
      <div class="col-10 col-xl-4 col-lg-6 align-self-center">
         <form method="POST" action="?/login" use:enhance>
            <h1>Вход</h1>
            <div class="mb-3">
               <label for="username" class="form-label">Имя пользователя</label>
               <input name="username" type="text" class="form-control" id="username" value={form?.formData.username || null} required />
            </div>
      
            <div class="mb-3">
               <label for="password" class="form-label">Пароль</label>
               <input name="password" type="password" class="form-control" id="password" value={form?.formData.password || null} required />
            </div>
      
            {#if form?.error}
               <p class="error">{form?.error}</p>
            {/if}
      
            <button class="btn btn-primary" type="submit">Войти</button>
         </form>
      </div>
   </div>
</div>

<style>
   .container-fluid, .row {
      height: 100vh;
   }

   form {
      display: flex;
      flex-direction: column;
   }

   h1 {
      margin: 0 auto 20pt auto;
   }

   button {
      margin-top: 20pt;
   }

   .error {
      color: red;
      margin: 0 auto;
   }
</style>
