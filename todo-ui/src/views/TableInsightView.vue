<template>
  <div v-if="loading" class="loader-progress">
    <ProgressSpinner  />
  </div>
  <div class="card flex justify-content-center">
    <Toast />
  </div>
  <HeaderInsight></HeaderInsight>

  <div class="card">
    <DataTable :value="todos.content" lazy paginator responsive @page="onPage($event)"
        :rows="todos.size" :totalRecords="todos.totalElements" tableStyle="min-width: 50rem">
      <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
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
import HeaderInsight from '../components/HeaderInsight.vue'
import { ref, onMounted } from 'vue';

import * as fetchProtocol from "../service/httpFetchDataService.js";

import { toastNotification } from "../service/toastNotificationService.js";
import { useToast } from 'primevue/usetoast';
const toast = useToast();

const todos = ref([])
var loading = ref(false)

const columns = [
  { field: 'description', header: 'Description' },
  { field: 'createdDate', header: 'Creation Date' },
  { field: 'doneDate', header: 'Completion Date' },
  { field: 'id', header: 'Delete' },
  { field: 'done', header: 'Undone' },
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

  const response = await fetchProtocol.PATCH(`${id}/undone`)

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    fetchData()

  disableLoad()
}



async function deleteById(id) {
  enableLoad();

  const response = await fetchProtocol.DELETE(`${id}`)

  if(response != null)
    if(response.isToast)
      toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)

  fetchData()
}



async function fetchData(page) {
  enableLoad();

  if(page === null || page === '' || page === undefined) page = 0

  const response = await fetchProtocol.GET(`todo-done?page=${page}`)

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    todos.value = response.obj

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
