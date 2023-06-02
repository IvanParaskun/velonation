<script>
   import Accessory from './Accessory.svelte';

   /** @type {import('./$types').PageData} */
   export let data;
   let {accessories, status} = data

   if (status != 200)
   	throw new Error()

   function dropAccessory(id) {
      accessories = accessories.filter(accessory => accessory.id != id)
   }
</script>


<div class="row justify-content-between">
   <div class="col-12">
      <h3>Аксессуары <a style="color:brown" href="/products/accessories/create">+</a></h3>
      <div class="row row-cols-xxl-4 row-cols-xl-3 row-cols-lg-2 row-cols-md-2 row-cols-sm-2 row-cols-1">
         {#each accessories as accessory}
				<div class="col">
					<Accessory {accessory} drop={dropAccessory} />
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
