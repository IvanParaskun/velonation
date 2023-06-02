<script>
	import Nav from '$lib/Nav.svelte'  
   import Footer from '$lib/Footer.svelte'

	/** @type {import('./$types').PageData} */
   export let data
	
	let { shopInfo } = data
</script>

<Nav />

<div class="container-fluid">
   <slot />
</div>

<Footer {shopInfo}/>

<style>
	.container-fluid {
		margin: 20pt 0;
	}
</style>