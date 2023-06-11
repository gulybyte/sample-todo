<template>
  <div>
    <InputText v-model="description" type="text" style="width: 100%; margin-bottom: 0.5rem;" />

    <Button label="Salvar" @click="postData" :disabled="description === ''"></Button>

    <div class="card">
      <DataTable :value="todos" tableStyle="min-width: 50rem">
        <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
          <template #body="TETE">
            <template v-if="col.field === 'id'">
              <Button @click="deleteData(TETE.data.id)"><i class="pi pi-check"></i></Button>
            </template>
            <template v-if="col.field === 'description'">
              {{ TETE.data.description }}
            </template>
            <template v-if="col.field === 'createdDate'">
              {{ TETE.data.createdDate  }}
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
const todos = ref([])


const columns = [
  { field: 'description', header: 'Descrição' },
  { field: 'createdDate', header: 'Data Criação' },
  { field: 'id', header: 'Editar' },
];



async function fetchData() {
  const response = await fetch(URL)
  if (response.ok) {
    todos.value = await response.json()
  }
}



async function deleteData(id) {
  alert(URL+id+'/done')
  await fetch(URL+id+'/done', {method: "PATCH"})
  fetchData()
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
