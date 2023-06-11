<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>

  <div class="container-main">
    <RouterView />

    <nav>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/about">About</RouterLink>
    </nav>

  </div>


</template>