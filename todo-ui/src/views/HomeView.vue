<template>
  <div>
    <InputText v-model="description" type="text" style="width: 100%;" class="mb-05rem" />

    <Button label="Salvar" @click="postData" :disabled="description === ''"
      class="mb-05rem" style="margin-bottom: 0.5rem;"></Button>

    <div class="card">
      <DataTable :value="todos" tableStyle="min-width: 50rem" stripedRows editMode="cell" @cell-edit-complete="onCellEditComplete">

        <Column field="createdDate" header="Data" sortable>
          <template #body="createdDate">
            {{ removeYear(createdDate.data.createdDate) }}
          </template>
        </Column>

        <Column field="description" header="Descrição">
          <template #body="description">
            {{ description.data.description }}
          </template>
          <template #editor="{ data, field }">
            <InputText v-model="data[field]" autofocus style="width: 100%;" />
          </template>
        </Column>

        <Column v-for="col of columns" :key="col.field" :field="col.field" :header="col.header">
          <template #body="rowData">
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
  </div>
</template>





<script setup>
import { ref, onMounted } from 'vue';

const URL = 'http://localhost:5000/'
const HEADERS = { 'Content-Type': 'application/json' }

const description = ref('')
const todos = ref([])


const columns = [
  { field: 'id', header: 'Concluir' },
  { field: 'orderTodo', header: 'Ordem' },
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



async function changeOrderById(id) {

  const URL_ORDER_DATA = `${URL+id}/order`

  await fetch(URL_ORDER_DATA, {method: "PATCH"})

  fetchData()
}



async function updateDescriptionById(id, description) {

  const URL_UPDATE_DESCRIPTION = `${URL+id}/update-description`

  await fetch(URL_UPDATE_DESCRIPTION,
  {
    method: "PATCH",
    headers: HEADERS,
    body: JSON.stringify({ 'description': description })
  })

  fetchData()
}



async function postData() {
  await fetch(URL,
  {
    method: 'POST',
    headers: HEADERS,
    body: JSON.stringify({ 'description': description.value })
  })

  description.value = ''

  fetchData()
}

onMounted(fetchData)
</script>

<style>
.mark-done-button {
  background-color: rgb(0, 38, 255);
  border: none;
} .change-order-button {
  background-color: rgb(208, 255, 0);
  border: none;
}
</style>
