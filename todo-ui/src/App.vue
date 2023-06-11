<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>

<InputText type="text" v-model="value" />

  <nav>
    <RouterLink to="/">Home</RouterLink>
    <RouterLink to="/about">About</RouterLink>
  </nav>

  <RouterView />


<div class="flex flex-wrap gap-3 align-items-center justify-content-center">
    <div class="relative bg-primary-200 w-9rem h-9rem mx-3 my-3 md:my-0 border-round">
        <div class="absolute top-0 left-0 bg-primary font-bold flex align-items-center justify-content-center w-4rem h-4rem border-round">1</div>
    </div>
</div>

</template>