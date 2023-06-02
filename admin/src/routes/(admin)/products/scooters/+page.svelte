<script>
   import Scooter from './Scooter.svelte';

   /** @type {import('./$types').PageData} */
   export let data;
   let {scooters, status} = data

   if (status != 200)
   	throw new Error()

   function dropScooter(id) {
      scooters = scooters.filter(scooter => scooter.id != id)
   }
</script>


<div class="row justify-content-between">
   <div class="col-12">
      <h3>Самокаты <a style="color:brown" href="/products/scooters/create">+</a></h3>
      <div class="row row-cols-xxl-4 row-cols-xl-3 row-cols-lg-2 row-cols-md-2 row-cols-sm-2 row-cols-1">
         {#each scooters as scooter}
				<div class="col">
					<Scooter {scooter} drop={dropScooter} />
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
