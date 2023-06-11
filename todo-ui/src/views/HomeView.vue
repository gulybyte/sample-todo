<template>

    <InputText v-model="inputText" type="text" style="width: 100%;" />

    <Button label="Salvar" @click="increment"
      :disabled="inputText === ''"></Button>
    {{ count }}

</template>

<script setup>
import { ref } from 'vue'

const count = ref(0)
const inputText = ref('')

function increment() {
  count.value++
}

</script>
