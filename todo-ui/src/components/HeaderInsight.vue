<template>

  <h1 class="mb-05rem">Conclued todo's</h1>

  <nav class="section-nav-insight">
    <RouterLink to="/insight">Frequency</RouterLink>
    <RouterLink to="/insight/table">Table</RouterLink>
  </nav>

</template>

<style>
.section-nav-insight {
  padding-bottom: 1rem;
}
.section-nav-insight> a {
    color: rgb(255, 90, 25);
    font-weight: bold;
    text-decoration: none;
    cursor: pointer;
    font-size: large;
    padding-right: 1rem;
} .router-link-active, .router-link-exact-active {
    color: rgb(255, 200, 0) !important;
    text-decoration: underline !important;
}
</style>
