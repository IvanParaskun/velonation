<script>
	let violations
	let promise = null

	async function submit(event) {
		const data = new FormData(this)

		promise = fetch('/api/products/bikes/categories/category', {
			method: 'POST',
			body: data
		})
		
		let responseData = await promise.then(response => response.json())

		if (responseData.statusCode == 'CREATED')
			window.location.href = '/products/bikes'

		violations = getViolations(responseData.violations)
	}

	function getViolations(v) {
		let violations = {}

		for (let violation of v)
			violations[violation.fieldName] = violation.errorMessage

		return violations
	}
</script>

<div class="row justify-content-center">
	<div class="col-xxl-7 col-xl-7 col-lg-8 col-md-9">
		<h2 style="text-align: center;">
			<a style="font-size: 10pt; vertical-align: middle;" href="/products/bikes">
				<span class="material-symbols-outlined">arrow_back</span>
			</a>  
			Новая категория
		</h2>
		<form on:submit|preventDefault={submit} method="POST" enctype="multipart/form-data" class="needs-validation">
			<div class="mb-3">
				<label for="name" class="form-label">Название</label>
				<input
					type="text"
					name="name"
					class="form-control"
					id="name"
					minlength="3"
					maxlength="20"
					required
				/>
				{#if violations && violations.name != undefined}
					<p class="invalid">{violations.name}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="discount" class="form-label">Скидка</label>
				<input
					type="number"
					name="discount"
					step="1"
					class="form-control"
					id="discount" 
					min="1"
					max="99"
				/>
				{#if violations && violations.discount != undefined}
					<p class="invalid">{violations.discount}</p>
				{/if}
			</div>
		
			{#if violations}
				<p class="invalid">Некорректное заполнение полей</p>
			{/if}
		
			<button type="submit" class="btn btn-primary">
				{#if promise}
					{#await promise}
						<div style="width: 13pt; height: 13pt;" class="spinner-border" role="status"></div>
					{:then}
						Сохранить
					{/await}
				{:else}
					Сохранить
				{/if}
			</button>
		</form>
	</div>
</div>


<style>
	h2 {
		margin-bottom: 30pt;
	}

	form {
		margin-bottom: 30pt;
		display: flex;
		flex-direction: column;
	}

	.invalid {
		margin-top: 5pt;
		color: red;
	}

	form button {
		margin: 0 auto;
		width: 50%;
	}
</style>
