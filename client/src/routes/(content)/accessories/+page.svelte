<script>
   import Accessory from './Accessory.svelte';

   /** @type {import('./$types').PageData} */
   export let data;

   let { accessories } = data
   let search = ''

   $: filtered = accessories.filter((accessory) => accessory.name.toLowerCase().includes(search.toLowerCase()))
</script>

<svelte:head>
   <title>Аксессуары - VELO</title>
</svelte:head>

<div class="row justify-content-center" style="margin-bottom: 30pt ;">
	<div class="col-10">
		<div class="top-bar">
			<div class="search">
				<span class="material-symbols-outlined" style="font-size: 25pt; font-weight: 300; margin: auto 0;">search</span>
				<input type="text" bind:value={search} />
			</div>
		</div>
	</div>
</div>

<div class="row justify-content-center">
	<div class="col-10">
		<div class="row justify-content-between d-flex">
			<div class="col-12">
				<div class="row row-cols-xxl-5 row-cols-xl-4 row-cols-lg-4 row-cols-md-3 row-cols-sm-2 row-cols-1">
					{#each filtered as accessory}
						<div class="col">
							<Accessory {accessory} />
						</div>
					{/each}
				</div>
			</div>
		</div>
	</div>
</div>

<style>
   .top-bar {
      display: flex;
      width: 100%;
      border: 1pt solid var(--outline);
      border-radius: 100pt;
      margin: 0 auto;
   }

   .top-bar .search {
      display: flex;
      width: 100%;
      padding: 10pt 20pt;
   }

   .top-bar .search input {
      width: 100%;
      height: 100%;
      margin: auto 0;
      margin-left: 10pt;
      outline: none;
      border: none;
      background-color: var(--background);
   }
</style>
