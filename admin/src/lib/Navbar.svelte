<script>
  import { page } from "$app/stores";

  $: path = $page.url.pathname;
</script>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Администратор</a>

    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNavDropdown"
      aria-controls="navbarNavDropdown"
      aria-expanded="false"
      aria-label="Toggle navigation"
    ><span class="navbar-toggler-icon" /></button>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <!-- svelte-ignore a11y-invalid-attribute -->
          <a
            class="nav-link dropdown-toggle"
            href="#"
            role="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >Товары</a>
          <ul class="dropdown-menu">
            <li>
              <a
                class="dropdown-item"
                href="/products/bikes"
                class:active={path == "/products/bikes"}>Велосипеды</a>
            </li>
            <li>
              <a
                class="dropdown-item"
                href="/products/scooters"
                class:active={path == "/products/scooters"}>Самокаты</a>
            </li>
            <li>
              <a
                class="dropdown-item"
                href="/products/accessories"
                class:active={path == "/products/accessories"}>Аксессуары</a>
            </li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/orders" class:active={path == "/orders"}>Заказы</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<style>
  nav {
    position: fixed;
    width: 100%;
    z-index: 1;
  }

  nav .container-fluid {
    width: 80%;
  }
</style>
