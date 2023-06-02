<script>
	export let shopInfo
</script>

<footer>
   <div class="content">
      <a class="brand" href="/">VELO</a>
		<p style="margin-left: auto;">{shopInfo.address}</p>
      <p style="margin: auto 30pt;">© 2023 VELONATION</p>
		<p style="margin-right: auto;">{shopInfo.phoneNumber}</p>
      <a class="cart" href="/cart"><span class="material-symbols-outlined">shopping_bag</span></a>
   </div>
</footer>

<style>
   footer {
      display: flex; 
      width: 100%;
      height: 70pt;
      margin-top: auto;
      background-color: var(--surface1);
      color: var(--on-background);
   }

   footer .content {
      display: flex;
      width: 80%;
      margin: auto auto;
   }

   footer .content .brand {
      font-family: 'Nunito', sans-serif;
      font-weight: 600;
      font-size: 20pt;
   }

   footer .content p {
		margin: auto 0;
      font-size: 10pt;
      font-weight: 500;
      color: var(--secondary);
   }

   footer .content .cart .material-symbols-outlined {
      font-size: 25pt;
      font-weight: 300;
   }

   @media(max-width: 600px) {
      footer .content p {
         display: none;
      }

      footer .content .cart {
         margin-left: auto;
      }
   }
</style>