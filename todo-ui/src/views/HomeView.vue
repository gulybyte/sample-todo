<template>
  <div class="card flex justify-content-center">
    <Toast />
  </div>
  <div v-if="loading" class="loader-progress">
    <ProgressSpinner  />
  </div>
  <div>
    <InputText v-model="description" placeholder="description" type="text" style="width: 100%;" class="mb-05rem mr-05rem" />

    <Button label="Salvar" @click="postData()" :disabled="description === ''"
      class="mb-05rem" style="margin-bottom: 0.5rem;"></Button>

    <InputText v-model="contextTodo" placeholder="context (tag) for todo" type="text" class="mb-05rem ml-05rem" />

    <div class="card">
      <DataTable :value="todos" tableStyle="min-width: 50rem" stripedRows editMode="cell" @cell-edit-complete="onCellEditComplete">

        <Column field="createdDate" header="Date" sortable>
          <template #body="createdDate">
            {{ removeYear(createdDate.data.createdDate) }}
          </template>
        </Column>

        <Column field="description" header="Description">
          <template #body="rowData">
            {{ rowData.data.description }}
          </template>
          <template #editor="{ data, field }">
            <InputText v-model="data[field]" autofocus style="width: 100%;" />
          </template>
        </Column>

        <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
          <template #body="rowData">
            <template v-if="col.field === 'contextTodo'">
              <div @mouseover="modeContextTodoButton = true" @mouseleave="modeContextTodoButton = false">
                <Tag v-show="!modeContextTodoButton" :value="rowData.data.contextTodo"></Tag>
                <Button class="context-mode-button" v-show="modeContextTodoButton"
                  @click="dialogContextTodoIsVisible = true ; dialogContextTodoId = rowData.data.id ; oldContextTodo = rowData.data.contextTodo"><i class="pi pi-pencil"></i></Button>
              </div>
            </template>
            <template v-if="col.field === 'id'">
              <Button class="mark-done-button" @click="markAsDone(rowData.data.id)"><i class="pi pi-check"></i></Button>
            </template>
            <template v-if="col.field === 'orderTodo'">
              <Button class="change-order-button" @click="changeOrderById(rowData.data.id)"><i class="pi pi-angle-double-up"></i></Button>
            </template>
          </template>
        </Column>

      </DataTable>
    </div>

    <Dialog v-model:visible="dialogContextTodoIsVisible" header="Change Context of the todo" :style="{ width: '75vw' }" maximizable modal>
        <InputText v-model="contextTodoEdit" :placeholder="oldContextTodo" type="text"  />
        <template #footer>
          <Button label="Salvar" @click="updateContextTodoById(dialogContextTodoId, contextTodoEdit)" :disabled="contextTodoEdit === ''"
            class="mb-05rem" style="margin-bottom: 0.5rem;"></Button>
          <label style="color: gray;" class="block mb-2">Tip: use a short name without spaces, a simple identifier.</label>
        </template>
    </Dialog>


  </div>


</template>


<script setup>
import { ref, onMounted } from 'vue';

import * as fetchProtocol from "../service/httpFetchDataService.js";

import { toastNotification } from "../service/toastNotificationService.js";
import { useToast } from 'primevue/usetoast';
const toast = useToast();

const description = ref('')
const contextTodo = ref('')
const todos = ref([])
var loading = ref(false)

const contextTodoEdit = ref('')
var modeContextTodoButton = ref(false)
var dialogContextTodoIsVisible = ref(false)
var dialogContextTodoId = ref('')
var oldContextTodo = ref('')

const columns = [
  { field: 'contextTodo', header: 'Context' },
  { field: 'id', header: 'Done' },
  { field: 'orderTodo', header: 'Order' },
];

function removeYear(value) {
  const [date, hours] = value.split(' ');
  const [day, month] = date.split('/');
  return `${hours} ${day}/${month}`;
}


const onCellEditComplete = (event) => {
    let { data, newValue } = event
    if(newValue != data.description) {
      updateDescriptionById(data.id, newValue)
    }
};

async function enableLoad() {
  loading.value = true
} function disableLoad() {
  setTimeout(() => {
    loading.value = false;
  }, 300);
}



async function markAsDone(id) {
  enableLoad();

  const response = await fetchProtocol.PATCH(`${id}/done`)

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    fetchData()

  disableLoad()
}



async function changeOrderById(id) {
  enableLoad();

  const response = await fetchProtocol.PATCH(`${id}/order`)

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    fetchData()

  disableLoad()
}



async function updateDescriptionById(id, description) {
  enableLoad();

  const body = JSON.stringify({ 'id': id, 'description': description })

  const response = await fetchProtocol.PATCH(`update-description`, body)

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    fetchData()

  disableLoad()
}



async function updateContextTodoById(id, context) {
  enableLoad();

  const body = JSON.stringify({ 'id': id, 'context': context })

  const response = await fetchProtocol.PATCH(`update-context`, body)

  contextTodoEdit.value = ''
  dialogContextTodoIsVisible.value = false

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    fetchData()

  disableLoad()
}



async function postData() {
  enableLoad();

  const body = JSON.stringify({ 'description': description.value, 'contextTodo': contextTodo.value })

  const response = await fetchProtocol.POST(``, body)

  description.value = ''
  contextTodo.value = ''

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    fetchData()

  disableLoad()
}



async function fetchData() {
  enableLoad();

  const response = await fetchProtocol.GET(``)

  if(response.isToast)
    toastNotification(toast, response.obj.severity, response.obj.title, response.obj.body, response.obj.details)
  else
    todos.value = response.obj

  disableLoad();
}

onMounted(fetchData)

</script>

<style>

.mark-done-button {
  background-color: rgb(116, 255, 41);
  border: none;
} .change-order-button {
  background-color: rgb(85, 0, 255);
  border: none;
} .context-mode-button {
  background-color: rgb(216, 255, 42);
  border: none;
}
</style>
