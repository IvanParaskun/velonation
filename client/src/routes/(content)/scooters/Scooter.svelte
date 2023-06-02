<script>
	import { lazyLoad } from "$lib/LazyLoad";

	export let scooter

	let discount = scooter.price / 100 * (scooter.discount ? scooter.discount : 0)
	let price = Math.ceil(scooter.price - discount)
</script>

<a class="scooter" href="/scooters/{scooter.id}">
	<div class="image">
		<img use:lazyLoad={scooter.imageUrl} alt="Image of {scooter.name} scooter">
	</div>
	<h4>{scooter.name}</h4>
	<div class="price">
		{#if discount != 0}
			<p><s>{scooter.price}₽</s></p>
			<p class="new-price">{price}₽</p>
		{:else}
			<p>{scooter.price}₽</p>
		{/if}
	</div>
</a>

<style>
	.scooter {
      margin-right: 2%;
      margin-bottom: 20pt;
   }

   .scooter:hover {
      cursor: pointer;
   }

   .scooter:hover .image img {
      transform: scale(1.05);
   }

   .scooter:hover h4 {
      color: var(--primary);
   }

   .scooter .image {
      display: flex;
      width: 100%;
      height: 250pt;
      border-radius: 10pt;
      background-color: var(--surface1);
   }

   .scooter .image img {
      width: 75%;
      margin: auto auto;   
      transition: 0.3s;
   }

   .scooter h4 {
      font-size: 15pt;
      font-weight: 500;
      margin-top: 10pt;
      transition: 0.3s;
   }

   .scooter .price {
      display: flex;
      font-size: 15pt;
      font-weight: 400;
   }

   .scooter .price .new-price {
      color: var(--error);
      margin-left: 10pt;
   }

   @media(max-width: 1400px) {
		.scooter .image {
			height: 250pt;
   	}
	}

	@media(max-width: 1200px) {
		.scooter .image {
			height: 300pt;
   	}
	}

	@media(max-width: 900px) {
		.scooter .image {
			height: 250pt;
   	}
	}


	@media(max-width: 768px) {
		.scooter .image {
			height: 400pt;
   	}
	}

	@media(max-width: 550px) {
		.scooter .image {
			height: 300pt;
   	}
	}

	@media(max-width: 420px) {
		.scooter .image {
			height: 200pt;
   	}
	}
</style>