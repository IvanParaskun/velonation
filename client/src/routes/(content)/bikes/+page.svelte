<script>
   import Bike from "./Bike.svelte";

   /** @type {import('./$types').PageData} */
   export let data;

	let { bikes, info, categories } = data

   let search = '';
   let categoryName = null;
   let brand = null;
   let modelYear = null;
   let gender = null;

   function reload() {
      search = '';
      categoryName = null;
      brand = null;
      modelYear = null;
      gender = null;
   }

   $: filtered = bikes.filter(
      (bike) =>
         bike.name.toLowerCase().includes(search.toLowerCase()) &&
         (categoryName == null || (bike.category != null && bike.category.name == categoryName)) &&
         (brand == null || bike.brand == brand) &&
         (modelYear == null || bike.modelYear == modelYear) &&
         (gender == null || bike.gender == gender)
   );
</script>

<svelte:head>
   <title>Велосипеды - VELO</title>
</svelte:head>

<div class="row justify-content-center" style="margin-bottom: 30pt ;">
	<div class="col-10">
		<div class="top-bar d-flex">
			<div class="category">
				<div class="dropdown">
					<button id="category" class="btn dropdown-toggle font-title-medium" type="button" data-bs-toggle="dropdown" aria-expanded="true">{categoryName == null ? 'Категория' : categoryName}</button>
					<ul class="dropdown-menu">
						<!-- svelte-ignore a11y-invalid-attribute -->
						<li><a class="dropdown-item" on:click={() => (categoryName = null)} href="#">Все</a></li>
						{#each categories as category}
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (categoryName = category.name)} href="#">{category.name}</a></li>
						{/each}
					</ul>
				</div>
			</div>
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
			<div class="col-xxl-3 col-xl-4 col-12">
				<div class="filters">
					<div class="top d-flex">
						<h3>Фильтровать</h3>
						<!-- svelte-ignore a11y-click-events-have-key-events -->
						<span on:click={reload} class="material-symbols-outlined refresh">refresh</span>
					</div>
			
					<div class="dropdown filter">
						<button id="brand" class="btn dropdown-toggle font-title-medium" type="button" data-bs-toggle="dropdown" aria-expanded="true">{brand == null ? 'Бренд' : brand}</button>
						<ul class="dropdown-menu">
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (brand = null)} href="#">Все</a></li>
							{#each info.brands as brandName}
								<!-- svelte-ignore a11y-invalid-attribute -->
								<li><a class="dropdown-item" on:click={() => (brand = brandName)} href="#">{brandName}</a></li>
							{/each}
						</ul>
					</div>
			
					<div class="dropdown filter">
						<button id="modelYear" class="btn dropdown-toggle font-title-medium" type="button" data-bs-toggle="dropdown" aria-expanded="true">{modelYear == null ? 'Год' : modelYear}</button>
						<ul class="dropdown-menu">
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (modelYear = null)} href="#">Все</a></li>
							{#each info.modelYears as year}
								<!-- svelte-ignore a11y-invalid-attribute -->
								<li><a class="dropdown-item" on:click={() => (modelYear = year)} href="#">{year}</a></li>
							{/each}
						</ul>
					</div>
			
					<div class="dropdown filter">
						<button id="gender" class="btn dropdown-toggle font-title-medium" type="button" data-bs-toggle="dropdown" aria-expanded="true">{gender == null ? 'Пол' : gender}</button>
						<ul class="dropdown-menu">
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (gender = null)} href="#">Все</a></li>
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (gender = 'Мужской')} href="#">Мужской</a></li>
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (gender = 'Женский')} href="#">Женский</a></li>
							<!-- svelte-ignore a11y-invalid-attribute -->
							<li><a class="dropdown-item" on:click={() => (gender = 'Унисекс')} href="#">Унисекс</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-xxl-9 col-xl-8 col-12 bikes">
				<div class="row row-cols-xxl-3 row-cols-xl-2 row-cols-lg-2 row-cols-md-2 row-cols-1 bikes">
					{#each filtered as bike}
						<div class="col">
							<Bike {bike} />
						</div>
					{/each}
				</div>
			</div>
		</div>
	</div>
</div>

<style>
   .top-bar {
      border: 1pt solid var(--outline);
      border-radius: 100pt;
   }

   .top-bar .category {
      padding: 10pt 30pt;
      border-right: 1pt solid var(--outline);
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
	
	.filters {
		padding: 20pt;
		border-radius: 10pt;
		background-color: var(--surface2);
	}

	.filters .top {
		margin-bottom: 10pt;
	}

	.filters .top h3 {
		font-weight: 400;
	}

	.filters .refresh {
		margin: auto 0 auto auto;
	}

	.filters .refresh:hover {
		cursor: pointer;
	}

	@media(max-width: 1200px) {
		.bikes {
			margin-top: 20pt;
		}
	}

   @media(max-width: 600px) {
      .top-bar {
         flex-direction: column;
         border-radius: 5pt;
      }

      .top-bar .category {
         border: none;
         margin: 0 auto;
      }

      .top-bar .search {
         padding-top: 0;
      }
   }
</style>
