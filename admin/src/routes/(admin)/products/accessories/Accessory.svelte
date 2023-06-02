<script>
   import { lazyLoad } from "$lib/utils";

   export let accessory
   export let drop

   let promise = null

   async function remove() {
      promise = fetch('/api/products', {
         method: 'DELETE',
         headers: {
            'Content-Type': 'application/json'
         },
         body: JSON.stringify({ id: accessory.id })
      })
      .then(response => response.json())
      .then(data => {
         if (data.status == 200)
            drop(accessory.id)

         return data.text
      })
   }
</script>

<div class="card">
   <img use:lazyLoad={accessory.imageUrl} class="card-img-top" alt="{accessory.name} image" />
   <div class="card-body">
      <a href="/products/accessories/edit/{accessory.id}" class="card-title">#{accessory.id} {accessory.name}</a>
		
      <div class="button-group">
			<a href="/products/accessories/copy/{accessory.id}" class="btn btn-secondary">
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