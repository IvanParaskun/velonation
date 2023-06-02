<svelte:head>
   <title>Ошибка</title>
</svelte:head>

<div class="error">
   <div class="content">
      <p>
         К сожалению, мы обнаружили проблему на сервере и не можем отобразить
         страницу, которую вы запрашиваете. Мы приносим извинения за неудобства.
         Наша команда работает над устранением этой проблемы.
      </p>
      <p>
         Пожалуйста, попробуйте зайти на сайт позже или свяжитесь с нашей
         службой поддержки, чтобы получить дополнительную помощь. Мы постараемся
         решить эту проблему как можно быстрее и обеспечить вас качественным
         обслуживанием.
      </p>
      <p style="margin-bottom: 30pt;">
         Благодарим за понимание и выбор нашего магазина. Мы ценим вашу
         лояльность и готовы ответить на любые вопросы, которые у вас могут
         возникнуть.
      </p>

      <a href="tel: +7(983)-310-00-95">Свяжитесь с нами: +7(983)-310-00-95</a>
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

   .error .content a {
      font-size: 20pt;
      font-weight: 500;
      color: var(--primary);
      border-bottom: 2px solid;
      border-color: transparent;
      padding-bottom: 10pt;
      transition: 0.3s;
      text-align: left;
   }

   .error .content a:hover {
      border-color: var(--primary);
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
