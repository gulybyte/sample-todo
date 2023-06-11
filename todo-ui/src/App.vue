<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>

  <div class="card flex justify-content-center">
      <Button label="Check" icon="pi pi-check" />
  </div>

  <nav>
    <RouterLink to="/">Home</RouterLink>
    <RouterLink to="/about">About</RouterLink>
  </nav>

  <RouterView />
</template>