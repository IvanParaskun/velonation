<script>
	/** @type {import('./$types').PageData} */
   export let data

	let { info } = data
</script>

<svelte:head>
   <title>VELO - Магазин велосипедов</title>
</svelte:head>


{#if info.bannered}
	<div class="row justify-content-center" style="margin-bottom: 50pt;">
		<div class="banner col-10 overflow-hidden">
			<div class="row">
				<div class="col-xxl-6 col-xl-6 col-lg-6 col-md-12 image">
					<img src={info.bannered.imageUrl} alt="{info.bannered.name}" >
				</div>
				<div class="col-xxl-6 col-xl-6 col-lg-6 col-md-12 d-flex">
					<div class="content">
						<p>{info.bannered.name}</p>
						<h3>{info.bannered.heading}</h3>
						<a class="btn-second" href="/bikes/{info.bannered.id}">Подробнее</a>
					</div>
				</div>
			</div>
		</div>
	</div>
{/if}

{#if info.carded.length == 3}
	<div class="row justify-content-center">
		<div class="col-10" style="padding: 0;">
			<div class="row gx-20 row-cols-xxl-3 row-cols-xl-3 row-cols-lg-3 row-cols-1">
				{#each info.carded as carded}
					<div class="col p-2">
						<div class="bike-card text-center overflow-hidden">
							<div class="content">
								<p>{carded.name}</p>
								<h6>{carded.heading}</h6>
								<a class="btn-second" href="/bikes/{carded.id}">Подробнее</a>
							</div>
							<img src={carded.imageUrl} alt="{carded.name}">
						</div>
					</div>
				{/each}
			</div>
		</div>
	</div>
{/if}


<style>
	.banner {
		padding: 30pt 0;
		background-color: var(--surface1);
	}

	.banner img {
		width: 100%;
		object-position: -50pt;
	}

	.banner .content {
		margin: auto 0;
		padding-right: 20pt;
	}

	.banner .content p {
		font-size: 13pt;
		font-weight: 500;
		margin: 0;
	}

	.banner .content h3 {
		font-size: 30pt;
		margin: 10pt 0 40pt 0;
	}

	.bike-card {
		background-color: var(--surface3);
		padding: 20pt 0;
		border-radius: 10pt;
	}

	.bike-card .content {
		margin-bottom: 20pt;
		padding: 0 10pt;
	}

	.bike-card .content p {
		font-weight: 500;
		font-size: 13pt;
	}

	.bike-card .content h6 {
		font-size: 17pt;
		margin-bottom: 30pt;
		font-weight: 700;
	}

	.bike-card img {
		width: 120%;
		object-position: -40pt;
	}

	@media(max-width: 1500px) {
		.banner .content h3 {
			font-size: 25pt;
		}
	}

	@media(max-width: 1200px) {
		.banner .content h3 {
			font-size: 20pt;
		}

		.bike-card img {
			width: 120%;
			object-position: -20pt;
		}
	}

	@media(max-width: 992px) {
		.banner .content {
			text-align: center;
			padding: 0 20pt;
		}

		.banner .image {
			display: flex;
			margin-bottom: 20pt;
		}

		.banner img {
			width: 80%;
			object-position: 0;
			margin: 0 auto;
		}

		.bike-card img {
			display: none;
		}

		.bike-card .content {
			margin: 0;
		}
	}
</style>