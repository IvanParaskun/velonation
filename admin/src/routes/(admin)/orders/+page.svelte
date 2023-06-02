<script>
   import Order from './Order.svelte';

   /** @type {import('./$types').PageData} */
   export let data;
   let {orders, status} = data

   if (status != 200)
   	throw new Error()

   function dropOrder(id) {
      orders = orders.filter(order => order.id != id)
   }
</script>


<div class="row justify-content-between">
   <div class="col-12">
      <h3>Заказы</h3>
      <div class="row row-cols-xxl-5 row-cols-xl-4 row-cols-lg-3 row-cols-md-2 row-cols-sm-2 row-cols-1">
         {#each orders as order}
				<div class="col">
					<Order {order} drop={dropOrder} />
				</div>
         {/each}
      </div>
   </div>
</div>

<style>
   h3 {
      margin-bottom: 20pt;
   }

	.col {
		margin-bottom: 15pt;
	}
</style>
