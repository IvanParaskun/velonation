<script>
	import { lazyLoad } from "$lib/LazyLoad";

	export let bike

	let discount = bike.price / 100 * (bike.discount ? bike.discount : 0)
	let price = Math.ceil(bike.price - discount)
</script>

<a class="bike" href="/bikes/{bike.id}">
	<div class="image">
		<img use:lazyLoad={bike.imageUrl} alt="Image of {bike.name} bike">
	</div>
	<h4>{bike.name}</h4>
	<div class="price">
		{#if discount != 0}
			<p><s>{bike.price}₽</s></p>
			<p class="new-price">{price}₽</p>
		{:else}
			<p>{bike.price}₽</p>
		{/if}
	</div>
</a>

<style>
	.bike {
      margin-right: 2%;
      margin-bottom: 20pt;
   }

   .bike:hover {
      cursor: pointer;
   }

   .bike:hover .image img {
      transform: scale(1.05);
   }

   .bike:hover h4 {
      color: var(--primary);
   }

   .bike .image {
      display: flex;
      width: 100%;
      height: 180pt;
      border-radius: 10pt;
      background-color: var(--surface1);
   }

   .bike .image img {
      width: 75%;
      margin: auto auto;   
      transition: 0.3s;
   }

   .bike h4 {
      font-size: 15pt;
      font-weight: 500;
      margin-top: 10pt;
      transition: 0.3s;
   }

   .bike .price {
      display: flex;
      font-size: 15pt;
      font-weight: 400;
   }

   .bike .price .new-price {
      color: var(--error);
      margin-left: 10pt;
   }

   @media(max-width: 1400px) {
		.bike .image {
			height: 200pt;
   	}
	}

	@media(max-width: 768px) {
		.bike .image {
			height: 250pt;
   	}
	}

	@media(max-width: 420px) {
		.bike .image {
			height: 150pt;
   	}
	}
</style>