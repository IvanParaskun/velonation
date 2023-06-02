<script>
   import { lazyLoad } from "$lib/utils";

   export let scooter
   export let drop

   let promise = null

   async function remove() {
      promise = fetch('/api/products', {
         method: 'DELETE',
         headers: {
            'Content-Type': 'application/json'
         },
         body: JSON.stringify({ id: scooter.id })
      })
      .then(response => response.json())
      .then(data => {
         if (data.status == 200)
            drop(scooter.id)

         return data.text
      })
   }
</script>

<div class="card">
   <img use:lazyLoad={scooter.imageUrl} class="card-img-top" alt="{scooter.name} image" />
   <div class="card-body">
      <a href="/products/scooters/edit/{scooter.id}" class="card-title">#{scooter.id} {scooter.name}</a>
		
      <div class="button-group">
			<a href="/products/scooters/copy/{scooter.id}" class="btn btn-secondary">
				<span class="material-symbols-outlined" style="font-size: 15pt; padding-top: 3pt;">content_copy</span>
			</a>
			<button style="width: 100%; margin-left: 5pt;" class="btn btn-danger" on:click={remove}>
				{#if promise}
					{#await promise}
						<div style="width: 13pt; height: 13pt;" class="spinner-border" role="status"></div>
					{:then text}
						{text}
					{/await}
				{:else}
					Удалить
				{/if}
			</button>
		</div>
   </div>
</div>

<style>
	.card {
      border: none;
      background-color: aliceblue;
      padding: 10pt;
   }

	img {
		width: 80%;
		margin: 0 auto;
	}

	.card-title {
		font-size: 13pt;
		font-weight: 500;
	}

	.button-group {
		margin-top: 10pt;
		display: flex;
	}
</style>