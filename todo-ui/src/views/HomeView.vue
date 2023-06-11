<template>
  <div>
    <InputText v-model="description" type="text" style="width: 100%;" />
    <Button label="Salvar" @click="postData" :disabled="description === ''"></Button>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const description = ref('')

async function postData() {

  if (description.value !== '') {
    const response = await fetch('http://localhost:8080/api-rest/todos/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ 'description': description.value })
    })
  }
}

</script>
