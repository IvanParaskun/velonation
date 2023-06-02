<script>
   import Navbar from "$lib/Navbar.svelte";
</script>

<Navbar />
<div class="container-fluid">
   <slot />
</div>

<style>
   .container-fluid {
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      padding-top: 60pt;
   }
</style>