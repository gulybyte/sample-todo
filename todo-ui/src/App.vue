<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="container-main">
    <RouterView />

    <nav class="section-nav">
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/insight" style="padding-left: 1rem;">Insights</RouterLink>
    </nav>

  </div>


</template>
