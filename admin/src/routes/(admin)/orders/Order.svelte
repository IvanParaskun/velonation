<script>
   export let order
   export let drop
  
   let promise = null
	let submit = false

   async function remove() {
      promise = fetch('/api/orders', {
         method: 'DELETE',
         headers: {
            'Content-Type': 'application/json'
         },
         body: JSON.stringify({ id: order.id })
      })
      .then(response => response.json())
      .then(data => {
         if (data.status == 200)
            drop(order.id)

         return data.text
      })
   }
</script>

<div class="card">
   <div class="cart-body">
      <h5 class="card-title">Заказ #{order.id}</h5>
      <p>{order.email}</p>
      <p>{order.phoneNumber}</p>
      <div class="btn-group">
         <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
               Корзина
            </button>
            <ul class="dropdown-menu">
               {#each Object.entries(order.cart) as [key, value]}
                  <!-- svelte-ignore a11y-missing-attribute -->
                  <li><a class="dropdown-item">{key}: <b>{value}</b></a></li>
               {/each}
            </ul>
         </div>

         {#if order.deliveryInfo}
            <div style="margin-left: 5pt;" class="dropdown">
               <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                  Доставка
               </button>
               <ul class="dropdown-menu">
                  <li><p class="dropdown-item"><b>Имя:</b> {order.deliveryInfo.name}</p></li>
                  <li><p class="dropdown-item"><b>Улица:</b> {order.deliveryInfo.street}</p></li>
                  <li><p class="dropdown-item"><b>Дом:</b> {order.deliveryInfo.houseNumber}</p></li>
                  {#if order.deliveryInfo.apartments}
                  <li><p class="dropdown-item"><b>Квартира:</b> {order.deliveryInfo.apartments}</p></li>
                  {/if}
               </ul>
            </div>
         {/if}
      </div>

      {#if submit}
         <div class="btn-group">
            <button class="btn btn-danger" on:click={remove}>
               {#if promise}
                  {#await promise}
                     <div style="width: 13pt; height: 13pt;" class="spinner-border" role="status"></div>
                  {:then}
                     <span class="material-symbols-outlined" style="font-size: 15pt; padding-top: 5pt;">delete</span>
                  {/await}
               {:else}
                  Удалить
               {/if}
            </button>
            <button class="btn btn-warning" on:click={() => submit = false}>Отмена</button>
         </div>
      {:else}
         <button style="width: 100%;" on:click={() => submit = true} class="btn btn-danger">
            <span class="material-symbols-outlined" style="font-size: 15pt; padding-top: 5pt;">delete</span>
         </button>
      {/if}
   </div>
</div>

<style>
   .card {
      padding: 20pt;
   }

   .card h5 {
      margin-bottom: 10pt;
   }

   .card p {
      margin-bottom: 5pt;
   }

   .card .btn-group {
      display: flex;
      margin: 10pt 0;
   }
</style>