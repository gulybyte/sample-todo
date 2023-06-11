<template>
  <div>
    <InputText v-model="description" type="text" style="width: 100%;" class="mb-05rem" />

    <Button label="Salvar" @click="postData" :disabled="description === ''"
      class="mb-05rem" style="margin-bottom: 0.5rem;"></Button>

    <div class="card">
      <DataTable :value="todos" tableStyle="min-width: 50rem">
        <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
          <template #body="rowData">
            <template v-if="col.field === 'id'">
              <Button class="mark-done-button" @click="markAsDone(rowData.data.id)"><i class="pi pi-check"></i></Button>
            </template>
            <template v-if="col.field === 'description'">
              {{ rowData.data.description }}
            </template>
            <template v-if="col.field === 'createdDate'">
              {{ rowData.data.createdDate  }}
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
  { field: 'id', header: 'Concluir' },
];



async function fetchData() {
  const response = await fetch(URL)
  if (response.ok) {
    todos.value = await response.json()
  }
}



async function markAsDone(id) {

  const URL_DELETE_DATA = `${URL+id}/done`

  await fetch(URL_DELETE_DATA, {method: "PATCH"})

  fetchData()
}



async function postData() {
  await fetch(URL,
  {
    method: 'POST',
    headers: HEADERS,
    body: JSON.stringify({ 'description': description.value })
  })

  fetchData()
}

onMounted(fetchData)
</script>

<style>
.mark-done-button {
  background-color: rgb(0, 38, 255);
  border: none;
}
</style>