<script>
	import { cart, add } from '../../cart/cart.js';

   /** @type {import('./$types').PageData} */
   export let data;

	let bike = data.bike
	let cartValue

	cart.subscribe(value => {
		cartValue = value
	})
</script>

<svelte:head>
   <title>{bike.name} - VELO</title>
</svelte:head>

<div class="floating-bar">
   <div class="price">
      {#if bike.discount}
         <p><s>{bike.price}₽</s></p>
         <p class="new-price">{bike.price - (bike.price / 100 * bike.discount)}₽</p>
      {:else}
         <p>{bike.price}₽</p>
      {/if}
   </div>
   
   <button class="btn-primary" on:click={() => add(bike)} disabled={bike.id in cartValue}>
      {#if bike.id in cartValue}
         В корзине
      {:else}
         Добавить
      {/if}
   </button>
</div>

<div class="top-bar">
   <h1>{bike.name}</h1>
   <a href="#specs">Характеристики</a>
   <button class="btn-primary" on:click={() => add(bike)} disabled={bike.id in cartValue}>
      {#if bike.id in cartValue}
         В корзине
      {:else}
         Добавить {bike.price - (bike.price / 100 * (bike.discount ? bike.discount : 0))}₽
      {/if}
   </button>
</div>

<div class="banner">
   <div class="content">
      <div class="info">
         <h3>{bike.heading}</h3>
         <div class="price">
            {#if bike.discount}
               <p><s>{bike.price}₽</s></p>
               <p class="new-price">{bike.price - (bike.price / 100 * bike.discount)}₽</p>
            {:else}
               <p>{bike.price}₽</p>
            {/if}
         </div>
         <button class="btn-primary" on:click={() => add(bike)} disabled={bike.id in cartValue}>
            {#if bike.id in cartValue}
               В корзине
            {:else}
               Добавить
            {/if}
         </button>
      </div>
      <div class="image">
         <img src="{bike.imageUrl}" alt="{bike.name} image">
      </div>
   </div>
</div>

{#if bike.description}
   <div class="description">
      <p>{bike.description}</p>
   </div>
{/if}

<div id="specs">
   <h2>Технические характеристики</h2>
   <div class="list">
      <div class="spec">
         <h3>Пол</h3>
         <p>{bike.gender}</p>
      </div>

      {#if bike.modelYear}
         <div class="spec">
            <h3>Модельный год</h3>
            <p>{bike.modelYear}</p>
         </div>
      {/if}

      {#if bike.category}
         <div class="spec">
            <h3>Категория</h3>
            <p>{bike.category.name}</p>
         </div>
      {/if}

      {#if bike.frame && bike.frame.frameSize}
         <div class="spec">
            <h3>Размер рамы</h3>
            <p>{bike.frame.frameSize}″</p>
         </div>
      {/if}

      {#if bike.frame && bike.frame.material}
         <div class="spec">
            <h3>Материал рамы</h3>
            <p>{bike.frame.material}</p>
         </div>
      {/if}

      {#if bike.frame && bike.frame.isFolding != null}
         <div class="spec">
            <h3>Складной</h3>
            <p>{bike.frame.isFolding ? 'Да' : 'Нет'}</p>
         </div>
      {/if}

      {#if bike.transmission && bike.transmission.numberOfSpeeds}
         <div class="spec">
            <h3>Количество скоростей</h3>
            <p>{bike.transmission.numberOfSpeeds}</p>
         </div>
      {/if}

      {#if bike.transmission && bike.transmission.switchesType}
         <div class="spec">
            <h3>Тип переключателей</h3>
            <p>{bike.transmission.switchesType}</p>
         </div>
      {/if}

      {#if bike.transmission && bike.transmission.switches}
         <div class="spec">
            <h3>Переключатели</h3>
            <p>{bike.transmission.switches}</p>
         </div>
      {/if}

      {#if bike.transmission && bike.transmission.chain}
         <div class="spec">
            <h3>Цепь</h3>
            <p>{bike.transmission.chain}</p>
         </div>
      {/if}

      {#if bike.wheel && bike.wheel.wheelSize}
         <div class="spec">
            <h3>Размер колеса</h3>
            <p>{bike.wheel.wheelSize}″</p>
         </div>
      {/if}

      {#if bike.wheel && bike.wheel.bushings}
         <div class="spec">
            <h3>Втулки</h3>
            <p>{bike.wheel.bushings}</p>
         </div>
      {/if}

      {#if bike.wheel && bike.wheel.rimMaterial}
         <div class="spec">
            <h3>Материал ободов</h3>
            <p>{bike.wheel.rimMaterial}</p>
         </div>
      {/if}

      {#if bike.wheel && bike.wheel.tires}
         <div class="spec">
            <h3>Покрышки</h3>
            <p>{bike.wheel.tires}</p>
         </div>
      {/if}

      {#if bike.brakeSystem && bike.brakeSystem.brakesType}
         <div class="spec">
            <h3>Тип тормозов</h3>
            <p>{bike.wheel.brakesType}</p>
         </div>
      {/if}

      {#if bike.brakeSystem && bike.brakeSystem.brakes}
         <div class="spec">
            <h3>Тормоза</h3>
            <p>{bike.wheel.brakes}</p>
         </div>
      {/if}
   </div>
</div>

<style>
   .floating-bar {
      display: none;
      position: fixed;
      background-color: var(--background);
      height: 70pt;
      width: 100%;
      z-index: 2;
      bottom: 0;
      box-shadow: 0 10px 1em black;
      padding: 5pt 20pt;
   }

   .floating-bar .price {
      margin: auto 0;
      font-size: 20pt;
      font-weight: 500;
   }

   .floating-bar .price p {
      margin: 0;
   }

   .floating-bar button {
      margin: auto 0;
      margin-left: auto;
   }

   .top-bar {
      display: flex;
      width: 80%;
      margin: 0 auto;
      margin-bottom: 30pt;
   }

   .top-bar * {
      margin: auto 0;
   }

   .top-bar h1 {
      font-size: 22pt;
      font-weight: 500;
   }

   .top-bar a {
      padding: 5pt 20pt;
      border-radius: 100pt;
      background-color: var(--surface1);
      margin-left: 20pt;
   }

   .top-bar button {
      margin-left: auto;
   }

   .banner {
      display: flex;
      width: 100%;
      overflow: hidden;
      background-color: var(--surface3);
   }

   .banner .content {
      display: flex;
      width: 70%;
      height: 100%;
      margin: auto auto;
   }

   .banner .content .info {
      width: 40%;
      padding: 40pt 0;
      margin: auto 0;
      margin-left: 20pt;
   }

   .banner .content .info h3 {
      font-size: 30pt;
      font-weight: 600;
   }

   .banner .content .info .price {
      display: flex;
   }

   .banner .content .info p {
      margin: 20pt 0;
      font-size: 22pt;
      font-weight: 600;
   }

   .banner .content .info .new-price {
      color: var(--error);
      margin-left: 10pt;
   }

   .banner .content .image {
      width: 60%;
      margin: auto 0;
      overflow: visible;
   }

   .banner .content .image img {
      width: 100%;
      object-position: -50pt 50pt;
      overflow: visible;
   }

   .description {
      width: 80%;
      margin: 0 auto;
      margin-top: 40pt;
      font-size: 13pt;
   }

   #specs {
      width: 80%;
      margin: 0 auto;
      margin-top: 40pt;
      display: flex;
   }

   #specs .list {
      margin-top: 10pt;
      margin-left: 200pt;
   }

   #specs .list .spec {
      margin-bottom: 30pt;
   }

   #specs .list .spec h3 {
      font-size: 18pt;
      font-weight: 500;
   }

   #specs .list .spec p {
      font-size: 15pt;
   }

   @media(max-width: 1500px) {
      .banner .content {
         width: 90%;
      }

      .banner .content .info {
         width: 50%;
      }

      .banner .content .image {
         width: 50%;
      }

      .banner .content .image img {
         object-position: 0;
      }
   }

   @media(max-width: 1000px) {
      .floating-bar {
         display: flex;
      }

      .top-bar {
         flex-direction: column;
         width: 90%;
      }

      .top-bar a {
         margin-left: 0;
         margin-top: 10pt;
         width: 200pt;
         text-align: center;
      }

      .top-bar button {
         display: none;
      }

      .banner {
         background-color: transparent;
      }

      .banner .content {
         flex-direction: column-reverse;
         width: 100%;
      }

      .banner .content .image {
         width: 90%;
         margin: 0 auto;
         background-color: var(--surface1);
         border-radius: 20pt;
         padding: 40pt;
      }

      .banner .content .info {
         width: 90%;
         margin: 0 auto;
         display: flex;
         flex-direction: column;
      }

      .banner .content .info * {
         margin: 0 auto;
         text-align: center;
      }

      #specs {
         flex-direction: column;
      }

      #specs .list {
         margin-left: 0;
         margin-top: 30pt;
      }
   }

   @media(max-width: 500px) {
      .banner .content .image {
         padding: 20pt;
      }

      .banner .content .info h3 {
         font-size: 20pt;
      }

      .banner .content .info p {
         font-size: 16pt;
      }
   }
</style>