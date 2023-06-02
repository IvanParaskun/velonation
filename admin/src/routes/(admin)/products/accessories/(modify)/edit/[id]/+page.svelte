<script>
	/** @type {import('./$types').PageData} */
	export let data;

	let { accessory, status } = data
	let violations
	let promise = null

	if (status != 200)
		throw new Error()

	async function submit(event) {
		const data = new FormData(this)

		promise = fetch('/api/products/accessories/accessory', {
			method: 'PUT',
			body: data
		})
		
		let responseData = await promise.then(response => response.json())

		if (responseData.statusCode == 'CREATED')
			window.location.href = '/products/accessories'

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
			<a style="font-size: 10pt; vertical-align: middle;" href="/products/accessories">
				<span class="material-symbols-outlined">arrow_back</span>
			</a>  
			Редактирование аксессуара "{accessory.name}"
		</h2>
		<form
			on:submit|preventDefault={submit}
			method="POST"
			enctype="multipart/form-data"
			class="needs-validation"
		>
			<input type="text" hidden name="id" value={accessory.id}> 
			<div class="mb-3">
				<label for="name" class="form-label">Название</label>
				<input
					value={accessory.name}
					type="text"
					name="name"
					class="form-control"
					id="name"
					minlength="3"
					maxlength="50"
					required
				/>
				{#if violations && violations.name != undefined}
					<p class="invalid">{violations.name}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Цена</label>
				<div class="input-group">
					<input
						value={accessory.price}
						type="number"
						name="price"
						step="1"
						class="form-control"
						id="price"
						aria-describedby="pricePrepend"
						min="1"
						max="500000"
						required
					/>
					<span class="input-group-text" id="pricePrepend">₽</span>
				</div>
				{#if violations && violations.price != undefined}
					<p class="invalid">{violations.price}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="discount" class="form-label">Скидка</label>
				<input
					value={accessory.discount}
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

			<div class="mb-3 form-check">
				<input
					checked={accessory.available}
					type="checkbox"
					class="form-check-input"
					name="available"
					id="available"
				/>
				<label class="form-check-label" for="available">Наличие</label>
			</div>

			<div class="mb-3">
				<label for="file" class="form-label">Изображение</label>
				<input type="file" name="file" class="form-control" />
				{#if violations && violations.file != undefined}
					<p class="invalid">{violations.file}</p>
				{/if}
			</div>

			{#if violations}
				<p class="invalid">Некорректное заполнение полей</p>
			{/if}

			<button type="submit" class="btn btn-primary">
				{#if promise}
					{#await promise}
						<div
							style="width: 13pt; height: 13pt;"
							class="spinner-border"
							role="status"
						/>
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
    }

    .invalid {
        margin-top: 5pt;
        color: red;
    }
</style>
