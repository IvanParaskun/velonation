<svelte:head>
   <title>Ошибка</title>
</svelte:head>

<div class="error">
   <div class="content">
      <p>
         Запрашиваемая вами страница недоступна. Если вы хотите оформить заказ, пожалуйста, перейдите в корзину.
      </p>
   </div>
</div>

<style>
   .error {
      display: flex;
      width: 100%;
      height: 100%;
      margin-top: 10%;
      margin-bottom: 80pt;
   }

   .error .content {
      margin: auto auto;
      width: 50%;
      text-align: justify;
      color: var(--secondary);
   }

   @media(max-width: 1200px) {
      .error .content {
         width: 70%;
      }
   }

   @media(max-width: 800px) {
      .error .content {
         width: 90%;
      }
   }
</style>
