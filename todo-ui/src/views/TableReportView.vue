<template>
  <div v-if="loading" class="loader-progress">
    <ProgressSpinner  />
  </div>
  <HeaderReport></HeaderReport>

  <div class="card">
    <DataTable :value="todos.content" lazy paginator responsive @page="onPage($event)"
        :rows="todos.size" :totalRecords="todos.totalElements" tableStyle="min-width: 50rem">
      <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
        <!-- esse bloco é inutil, porém nescessario graças ao button delete -->
        <template #body="rowData">
          <template v-if="col.field === 'description'">
            {{ rowData.data.description }}
          </template>
          <template v-if="col.field === 'createdDate'">
            {{ rowData.data.createdDate  }}
          </template>
          <template v-if="col.field === 'doneDate'">
            {{ rowData.data.doneDate  }}
          </template>
          <template v-if="col.field === 'id'">
            <Button class="trash-button" @click="deleteById(rowData.data.id)"><i class="pi pi-trash"></i></Button>
          </template>
          <template v-if="col.field === 'done'">
              <Button class="change-order-button" @click="undoneMarkAsDone(rowData.data.id)"><i class="pi pi-times"></i></Button>
            </template>
        </template>
      </Column>
    </DataTable>
  </div>

</template>


<script setup>
import HeaderReport from '../components/HeaderReport.vue'
import { ref, onMounted } from 'vue';

const URL = 'http://localhost:5000/'

const todos = ref([])
var loading = ref(false)

const columns = [
  { field: 'description', header: 'Descrição' },
  { field: 'createdDate', header: 'Data Criação' },
  { field: 'doneDate', header: 'Data Finalização' },
  { field: 'id', header: 'Deletar' },
  { field: 'done', header: 'Desmarcar' },
];



const onPage = (event) => {
  fetchData(event.page)
};

async function enableLoad() {
  loading.value = true
} function disableLoad() {
  setTimeout(() => {
    loading.value = false;
  }, 300);
}



async function undoneMarkAsDone(id) {
  enableLoad();

  const URL_DELETE_DATA = `${URL+id}/undone`

  await fetch(URL_DELETE_DATA, {method: "PATCH"})

  fetchData()
}



async function deleteById(id) {
  enableLoad();

  const URL_DELETE_DATA = `${URL+id}`

  await fetch(URL_DELETE_DATA, {method: "DELETE"})

  fetchData()
}



async function fetchData(page) {
  enableLoad();

  if(page === null || page === '' || page === undefined) page = 0

  const response = await fetch(`${URL}finalized?page=${page}`)

  if (response.ok) todos.value = await response.json()

  disableLoad();
}



onMounted(fetchData)
</script>

<style>
.trash-button {
  background-color: rgb(220, 27, 27);
  border: red;
}
</style>
