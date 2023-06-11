<template>

  <InputText v-model="description" type="text" style="width: 100%; margin-bottom: 0.5rem;" />

    <Button label="Salvar" @click="postData"
      :disabled="description === ''"></Button>

</template>



<script setup>
import { ref } from 'vue'

  const URL = 'http://localhost:8080/api-rest/todos/'
  const HEADERS = { 'Content-Type': 'application/json' }

  const description = ref('')

  async function postData()
  {
    await fetch(URL, {
      method: 'POST',
      headers: HEADERS,
      body: JSON.stringify({ 'description': description.value })
    })
  }
</script>
