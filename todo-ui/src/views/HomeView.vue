<template>
  <div>
    <InputText v-model="description" type="text" style="width: 100%; margin-bottom: 0.5rem;" />

    <Button label="Salvar" @click="postData" :disabled="description === ''"></Button>

    <div class="card">
      <DataTable :value="products" tableStyle="min-width: 50rem">
        <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
          <template #body>
            <template v-if="col.field === 'id'">
              <Button>{{ col.field }}</Button>
            </template>
            <template v-else>
              {{ col.field }}
            </template>
          </template>
        </Column>
      </DataTable>
    </div>
  </div>
</template>





<script setup>
import { ref, onMounted } from 'vue';

const URL = 'http://localhost:8080/api-rest/todos/'
const HEADERS = { 'Content-Type': 'application/json' }

const description = ref('')
const products = ref([])


const columns = [
  { field: 'description', header: 'Descrição' },
  { field: 'createdDate', header: 'Data Criação' },
  { field: 'id', header: 'Editar' },
];



async function fetchData() {
  const response = await fetch(URL)
  if (response.ok) {
    products.value = await response.json()
  }
}



async function postData() {
  if (description.value !== '') {
    await fetch(URL, {
      method: 'POST',
      headers: HEADERS,
      body: JSON.stringify({ 'description': description.value })
    })
    fetchData()
  }
}

onMounted(fetchData)
</script>
