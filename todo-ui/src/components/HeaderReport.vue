<template>

  <h1 class="mb-05rem">Tarefas Anteriormente Concluidas</h1>

  <nav class="section-nav-report">
    <RouterLink to="/report">Graficos</RouterLink>
    <RouterLink to="/report/table">Tabela</RouterLink>
  </nav>

</template>

<style>
.section-nav-report {
  padding-bottom: 1rem;
}
.section-nav-report> a {
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
