<script>
   import Bike from './Bike.svelte';
   import Category from './Category.svelte';

   /** @type {import('./$types').PageData} */
   export let data;
   let {bikes, categories, status} = data

   if (status != 200)
   	throw new Error()

   function dropBike(id) {
      bikes = bikes.filter(bike => bike.id != id)
   }

   function dropCategory(id) {
      categories = categories.filter(category => category.id != id)
   }
</script>


<div class="row justify-content-between">
   <div class="col-xxl-3 col-xl-3 col-lg-3 d-flex flex-column" style="margin-bottom: 20pt;">
      <h3>Категории <a style="color:brown" href="/products/bikes/categories/create">+</a></h3>
      {#each categories as category}
         <Category {category} drop={dropCategory} />
      {/each}
   </div>

   <div class="col-xxl-9 col-xl-9 col-lg-9">
      <h3>Велосипеды <a style="color:brown" href="/products/bikes/create">+</a></h3>
      <div class="row row-cols-xxl-4 row-cols-xl-3 row-cols-lg-2 row-cols-md-2 row-cols-sm-2 row-cols-1">
         {#each bikes as bike}
				<div class="col">
					<Bike {bike} drop={dropBike} />
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
