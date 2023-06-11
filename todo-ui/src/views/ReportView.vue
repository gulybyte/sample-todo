<template>
  <h1 class="mb-05rem">Tarefas Anteriormente Concluidas</h1>

  <div class="card">
    <DataTable :value="todos" tableStyle="min-width: 50rem">
      <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header"></Column>
    </DataTable>
  </div>

</template>


<script setup>
import { ref, onMounted } from 'vue';

const URL = 'http://localhost:8080/api-rest/todos/finalized'

const todos = ref([])

const columns = [
  { field: 'description', header: 'Descrição' },
  { field: 'createdDate', header: 'Data Criação' },
  { field: 'doneDate', header: 'Data Finalização' },
];

async function fetchData() {
  const response = await fetch(URL)
  if (response.ok) {
    todos.value = await response.json()
  }
}

onMounted(fetchData)
</script>