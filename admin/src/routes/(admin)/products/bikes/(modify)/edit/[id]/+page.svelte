<script>
	/** @type {import('./$types').PageData} */
	export let data;

	let { bike, categories, status } = data
	let violations
	let promise = null

	if (status != 200)
		throw new Error()

	async function submit(event) {
		const data = new FormData(this)

		promise = fetch('/api/products/bikes/bike', {
			method: 'PUT',
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
			Редактирование велосипеда "{bike.name}"
		</h2>
		<form on:submit|preventDefault={submit} method="POST" enctype="multipart/form-data" class="needs-validation">
			<input type="text" hidden name="id" value={bike.id} />
			<div class="mb-3">
				<label for="brand" class="form-label">Бренд</label>
				<input
					value={bike.brand}
					type="text"
					name="brand"
					class="form-control"
					id="brand"
					minlength="3"
					maxlength="15"
					required
				/>
				{#if violations && violations.brand != undefined}
					<p class="invalid">{violations.brand}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="model" class="form-label">Модель</label>
				<input
					value={bike.model}
					type="text"
					name="model"
					class="form-control"
					id="model"
					minlength="3"
					maxlength="35"
					required
				/>
				{#if violations && violations.model != undefined}
					<p class="invalid">{violations.model}</p>
				{/if}
			</div>
			 
			{#if violations && violations.name != undefined}
				<p class="invalid">{violations.name}</p>
			{/if}
		
			<div class="mb-3">
				<label for="price" class="form-label">Цена</label>
				<div class="input-group">
					<input
						value={bike.price}
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
					value={bike.discount}
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
					checked={bike.available}
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
					value={bike.modelYear}
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
		
			<div class="mb-3">
				<label for="gender" class="form-label">Пол</label>
				<select class="form-select" id="gender" name="gender" value={bike.gender}>
					<option value="Мужской">Мужской</option>
					<option value="Женский">Женский</option>
					<option value="Унисекс">Унисекс</option>
				</select>
			</div>
		
			<div class="mb-3">
				<label for="categoryId" class="form-label">Категория</label>
		
				<select name="categoryId" id="categoryId" class="form-select" value={bike.category ? bike.category.id : ""}>
					<option value="">Нет</option>
					{#each categories as category}
						<option value={category.id}>{category.name}</option>
					{/each}
				</select>
		
				{#if violations && violations.category != undefined}
					<p class="invalid">{violations.category}</p>
				{/if}
			</div>
		
			<hr />
		
			<div class="mb-3">
				<label for="heading" class="form-label">Промо-текст</label>
				<input
					value={bike.heading}
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
					value={bike.description}
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
		
			<div class="mb-3 form-check">
				<input
					checked={bike.bannered}
					type="checkbox"
					class="form-check-input"
					name="bannered"
					id="bannered"
				/>
				<label class="form-check-label" for="bannered">На баннере</label>
				{#if violations && violations.bannered != undefined}
					<p class="invalid">{violations.bannered}</p>
				{/if}
			</div>
		
			<div class="mb-3 form-check">
				<input
					checked={bike.carded}
					type="checkbox"
					class="form-check-input"
					name="carded"
					id="carded"
				/>
				<label class="form-check-label" for="carded">На карточке</label>
				{#if violations && violations.carded != undefined}
					<p class="invalid">{violations.carded}</p>
				{/if}
			</div>
		
			<hr />
		
			<div class="mb-3">
				<label for="frameSize" class="form-label">Размер рамы</label>
				<div class="input-group has-validation">
					<input
						value={bike.frame ? bike.frame.frameSize : null}
						type="number"
						name="frame.frameSize"
						step="0.1"
						class="form-control"
						id="frameSize"
						aria-describedby="frameSizePrepend"
						min="1"
						max="100"
					/>
					<span class="input-group-text" id="frameSizePrepend">″</span>
				</div>
				{#if violations && violations['frame.frameSize'] != undefined}
					<p class="invalid">{violations['frame.frameSize']}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="frameMeterial" class="form-label">Материал рамы</label>
				<select class="form-select" id="frameMaterial" name="frame.material" value={bike.frame ? bike.frame.material : null}>
					<option value="Алюминий" selected>Алюминий</option>
					<option value="Сталь">Сталь</option>
				</select>
			</div>
		
			<div class="mb-3 form-check">
				<input
					value={bike.frame ? bike.frame.isFolding : null}
					type="checkbox"
					class="form-check-input"
					name="frame.isFolding"
					id="isFolding"
				/>
				<label class="form-check-label" for="isFolding">Складная</label>
			</div>
		
			<hr />
		
			<div class="mb-3">
				<label for="numberOfSpeeds" class="form-label">Количество скоростей</label>
				<input
					value={bike.transmission ? bike.transmission.numberOfSpeeds : null}
					type="number"
					step="1"
					name="transmission.numberOfSpeeds"
					class="form-control"
					id="numberOfSpeeds"
					min="0"
					max="50"
				/>
				{#if violations && violations['transmission.numberOfSpeeds'] != undefined}
					<p class="invalid">{violations['transmission.numberOfSpeeds']}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="switchesType" class="form-label">Тип переключателей</label>
				<input
					value={bike.transmission ? bike.transmission.switchesType : null}
					type="text"
					name="transmission.switchesType"
					class="form-control"
					id="switchesType"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations['transmission.switchesType'] != undefined}
					<p class="invalid">{violations['transmission.switchesType']}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="switches" class="form-label">Переключатели</label>
				<input
					value={bike.transmission ? bike.transmission.switches : null}
					type="text"
					name="transmission.switches"
					class="form-control"
					id="switches"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations['transmission.switches'] != undefined}
					<p class="invalid">{violations['transmission.switches']}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="chain" class="form-label">Цепь</label>
				<input
					value={bike.transmission ? bike.transmission.chain : null}
					type="text"
					name="transmission.chain"
					class="form-control"
					id="chain"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations['transmission.chain'] != undefined}
					<p class="invalid">{violations['transmission.chain']}</p>
				{/if}
			</div>
		
			<hr />
		
			<div class="mb-3">
				<label for="wheelSize" class="form-label">Размер колеса</label>
				<div class="input-group">
					<input
						value={bike.wheel ? bike.wheel.wheelSize : null}
						type="number"
						name="wheel.wheelSize"
						step="0.1"
						class="form-control"
						id="wheelSize"
						aria-describedby="wheelSizePrepend"
						min="1"
						max="100"
					/>
					<span class="input-group-text" id="wheelSizePrepend">″</span>
				</div>
				{#if violations && violations['wheel.wheelSize'] != undefined}
					<p class="invalid">{violations['wheel.wheelSize']}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="bushings" class="form-label">Втулки</label>
				<input
					value={bike.wheel ? bike.wheel.bushings : null}
					type="text"
					name="wheel.bushings"
					class="form-control"
					id="bushings"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations['wheel.bushings'] != undefined}
					<p class="invalid">{violations['wheel.bushings']}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="rimMaterial" class="form-label">Материал обода</label>
				<select class="form-select" id="rimMaterial" name="wheel.rimMaterial" value={bike.wheel ? bike.wheel.rimMaterial : null}>
					<option value="Сталь" selected>Сталь</option>
					<option value="Алюминий">Алюминий</option>
				</select>
			</div>
		
			<div class="mb-3">
				<label for="tires" class="form-label">Покрышки</label>
				<input
					value={bike.wheel ? bike.wheel.tires : null}
					type="text"
					name="wheel.tires"
					class="form-control"
					id="tires"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations['wheel.tires'] != undefined}
					<p class="invalid">{violations['wheel.tires']}</p>
				{/if}
			</div>
		
			<hr />
		
			<div class="mb-3">
				<label for="brakesType" class="form-label">Тип тормозов</label>
				<input
					value={bike.brakeSystem ? bike.brakeSystem.brakesType : null}
					type="text"
					name="brakeSystem.brakesType"
					class="form-control"
					id="brakesType"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations["brakeSystem.brakesType"] != undefined}
					<p class="invalid">{violations["brakeSystem.brakesType"]}</p>
				{/if}
			</div>
		
			<div class="mb-3">
				<label for="brakes" class="form-label">Тормоза</label>
				<input
					value={bike.brakeSystem ? bike.brakeSystem.brakes : null}
					type="text"
					name="brakeSystem.brakes"
					class="form-control"
					id="brakes"
					minlength="3"
					maxlength="50"
				/>
				{#if violations && violations["brakeSystem.brakes"] != undefined}
					<p class="invalid">{violations["brakeSystem.brakes"]}</p>
				{/if}
			</div>
		
			{#if violations}
				<p class="invalid">Некорректное заполнение полей</p>
			{/if}

			{#if violations && violations.global != undefined}
				<p class="invalid">{violations.global}</p>
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
	select {
		margin-bottom: 15pt;
	}

	hr {
		margin: 30pt 0;
	}

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
