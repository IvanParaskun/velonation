<script>
   export let category
   export let drop

   let promise = null

   async function remove() {
      promise = fetch('/api/products/bikes/categories/category', {
         method: 'DELETE',
         headers: {
            'Content-Type': 'application/json'
         },
         body: JSON.stringify({ id: category.id })
      })
      .then(response => response.json())
      .then(data => {
         if (data.status == 200)
            drop(category.id)

         return data.text
      })
   }
</script>

<div class="category">
   <a style="font-size: 15pt;" href="/products/bikes/categories/edit/{category.id}">{category.name}</a>
   {#if category.discount}<p>({category.discount}%)</p>{/if}
   <button style="margin-left: auto;" class="btn btn-danger" on:click={remove}>
      {#if promise}
          {#await promise}
              <div style="width: 13pt; height: 13pt;" class="spinner-border" role="status"></div>
          {:then text}
              {text}
          {/await}
      {:else}
          <span class="material-symbols-outlined" style="font-size: 10pt; padding-top: 5pt;">delete</span>
      {/if}
  </button>
</div>

<style>
   .category {
      display: flex;
      padding: 10pt;
      background-color: aliceblue;
      margin-bottom: 5pt;
      border-radius: 5pt;
   }

   .category * {
      margin: auto 0;
   }

   .category p {
      margin-left: 5pt;
   }
</style>