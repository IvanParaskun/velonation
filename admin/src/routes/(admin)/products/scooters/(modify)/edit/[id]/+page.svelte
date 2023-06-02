<script>
	/** @type {import('./$types').PageData} */
	export let data;

	let { scooter, status } = data
	let violations
	let promise = null

	if (status != 200)
		throw new Error()

	async function submit(event) {
		const data = new FormData(this)

		promise = fetch('/api/products/scooters/scooter', {
			method: 'PUT',
			body: data
		})
		
		let responseData = await promise.then(response => response.json())

		if (responseData.statusCode == 'CREATED')
			window.location.href = '/products/scooters'

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
			<a style="font-size: 10pt; vertical-align: middle;" href="/products/scooters">
				<span class="material-symbols-outlined">arrow_back</span>
			</a>  
			Редактирование самоката "{scooter.name}"
		</h2>
		<form
			on:submit|preventDefault={submit}
			method="POST"
			enctype="multipart/form-data"
			class="needs-validation"
		>
			<input type="text" hidden name="id" value={scooter.id}> 
			<div class="mb-3">
				<label for="name" class="form-label">Название</label>
				<input
					value={scooter.name}
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
						value={scooter.price}
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
					value={scooter.discount}
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
					checked={scooter.available}
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

			<hr />

			<div class="mb-3">
				<label for="modelYear" class="form-label">Модельный год</label>
				<input
					value={scooter.modelYear}
					type="number"
					name="modelYear"
					step="1"
					class="form-control"
					id="modelYear"
					min="2000"
					max="2023"
				/>
				{#if violations && violations.modelYear != undefined}
					<p class="invalid">{violations.modelYear}</p>
				{/if}
			</div>

			<hr />

			<div class="mb-3">
				<label for="heading" class="form-label">Промо-текст</label>
				<input
					value={scooter.heading}
					type="text"
					name="heading"
					class="form-control"
					id="heading"
					minlength="3"
					maxlength="100"
				/>
				{#if violations && violations.heading != undefined}
					<p class="invalid">{violations.heading}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="description" class="form-label">Описание</label>
				<textarea
					value={scooter.description}
					type="text"
					name="description"
					class="form-control"
					id="description"
					minlength="3"
					maxlength="500"
				/>
				{#if violations && violations.description != undefined}
					<p class="invalid">{violations.description}</p>
				{/if}
			</div>

			<hr />

			<div class="mb-3">
				<label for="wheelSize" class="form-label">Размер колеса</label>
				<div class="input-group has-validation">
					<input
						value={scooter.wheelSize}
						type="number"
						name="wheelSize"
						step="1"
						class="form-control"
						id="frameSize"
						aria-describedby="frameSizePrepend"
						min="1"
						max="1000"
					/>
					<span class="input-group-text" id="frameSizePrepend">мм</span>
				</div>
				{#if violations && violations.wheelSize != undefined}
					<p class="invalid">{violations.wheelSize}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="maxWeight" class="form-label">Максимальный вес</label>
				<div class="input-group has-validation">
					<input
						value={scooter.maxWeight}
						type="number"
						step="1"
						min="1"
						max="200"
						name="maxWeight"
						class="form-control"
						id="maxWeight"
						aria-describedby="maxWeightPrepend"
					/>

					<span class="input-group-text" id="maxWeightPrepend">кг</span>
				</div>
				{#if violations && violations.maxWeight != undefined}
					<p class="invalid">{violations.maxWeight}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="height" class="form-label">Высота</label>
				<div class="input-group has-validation">
					<input
						value={scooter.height}
						type="number"
						step="1"
						min="1"
						max="500"
						name="height"
						class="form-control"
						id="height"
						aria-describedby="heightPrepend"
					/>

					<span class="input-group-text" id="heightPrepend">см</span>
				</div>
				{#if violations && violations.height != undefined}
					<p class="invalid">{violations.height}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="steeringWidth" class="form-label">Ширина руля</label>
				<div class="input-group has-validation">
					<input
						value={scooter.steeringWidth}
						type="number"
						step="0.1"
						min="1"
						max="500"
						name="steeringWidth"
						class="form-control"
						id="steeringWidth"
						aria-describedby="steeringWidthPrepend"
					/>

					<span class="input-group-text" id="steeringWidthPrepend">см</span>
				</div>
				{#if violations && violations.steeringWidth != undefined}
					<p class="invalid">{violations.steeringWidth}</p>
				{/if}
			</div>

			<div class="mb-3">
				<label for="weight" class="form-label">Вес</label>
				<div class="input-group has-validation">
					<input
						value={scooter.weight}
						type="number"
						step="0.1"
						min="1"
						max="500"
						name="weight"
						class="form-control"
						id="weight"
						aria-describedby="weightPrepend"
					/>

					<span class="input-group-text" id="weightPrepend">кг</span>
				</div>
				{#if violations && violations.weight != undefined}
					<p class="invalid">{violations.weight}</p>
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
    hr {
        margin: 30pt 0;
    }

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
