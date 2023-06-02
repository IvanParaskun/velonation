<script>
	/** @type {import('./$types').PageData} */
   export let data

	let { shopInfo } = data

	if (shopInfo == undefined)
		shopInfo = {
			'address': null,
			'phoneNumber': null
		}
	
	async function update() {
		let response = await fetch('/api/info', {
			method: 'POST',
			body: JSON.stringify(shopInfo)
		}).then(response => response.json())

		shopInfo = response.shopInfo
	}
</script>

<div class="row justify-content-center">
   <div class="col-xl-7 align-self-center text-center">
      <h3>Добро пожаловать в панель администратора</h3>

		<form on:submit|preventDefault={update}>
			<input type="text" bind:value={shopInfo.address} placeholder="Адрес">
			<input type="text" bind:value={shopInfo.phoneNumber} placeholder="Номер телефона">
			<input type="submit" value="Обновить">
		</form>

      <a href="/products/bikes" class="btn btn-secondary">Начать</a>
   </div>
</div>

<style>
   .row {
      margin: auto 0;
   }
   
   a {
      margin-top: 10pt;
   }
</style>