<template>
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
                  @click="dialogContextTodoIsVisible = true ; dialogContextTodoId = rowData.data.id"><i class="pi pi-pencil"></i></Button>
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

    <Dialog v-model:visible="dialogContextTodoIsVisible" header="Flex Scroll" :style="{ width: '75vw' }" maximizable modal>
        <InputText v-model="contextTodoEdit" placeholder="Context" type="text" />
        <template #footer>
          <Button label="Salvar" @click="updateContextTodoById(dialogContextTodoId, contextTodoEdit)" :disabled="contextTodoEdit === ''"
            class="mb-05rem" style="margin-bottom: 0.5rem;"></Button>
        </template>
    </Dialog>

  </div>
</template>





<script setup>
import { ref, onMounted } from 'vue';

const URL = 'http://localhost:8080/'
const HEADERS = { 'Content-Type': 'application/json' }

const description = ref('')
const contextTodo = ref('')
const todos = ref([])
var loading = ref(false)

const contextTodoEdit = ref('')
var modeContextTodoButton = ref(false)
var dialogContextTodoIsVisible = ref(false)
var dialogContextTodoId = ref('')

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


async function fetchData() {
  enableLoad();
  const response = await fetch(URL);
  if (response.ok) {
    todos.value = await response.json();
  }
  disableLoad();
}



async function markAsDone(id) {
  enableLoad();

  const URL_DELETE_DATA = `${URL+id}/done`

  await fetch(URL_DELETE_DATA, {method: "PATCH"})

  fetchData()
}



async function changeOrderById(id) {
  enableLoad();

  const URL_ORDER_DATA = `${URL+id}/order`

  await fetch(URL_ORDER_DATA, {method: "PATCH"})

  fetchData()
}



async function updateDescriptionById(id, description) {
  enableLoad();

  const URL_UPDATE_DESCRIPTION = `${URL}update-description`

  await fetch(URL_UPDATE_DESCRIPTION,
  {
    method: "PUT",
    headers: HEADERS,
    body: JSON.stringify({ 'id': id, 'description': description })
  })

  fetchData()
}



async function updateContextTodoById(id, context) {
  enableLoad();

  const URL_UPDATE_CONTEXT = `${URL}update-context`

  await fetch(URL_UPDATE_CONTEXT,
  {
    method: "PUT",
    headers: HEADERS,
    body: JSON.stringify({ 'id': id, 'context': context })
  })

  contextTodoEdit.value = ''
  dialogContextTodoIsVisible.value = false

  fetchData()
}



async function postData() {
  enableLoad();


  await fetch(URL,
  {
    method: 'POST',
    headers: HEADERS,
    body: JSON.stringify({ 'description': description.value, 'contextTodo': contextTodo.value })
  })

  description.value = ''
  contextTodo.value = ''

  fetchData()
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
