<template>

  <InputText type="text" style="width: 100%;" />
  <Button label="Salvar" @click="count++"></Button>
  {{ count }}

</template>

<script>
export default {
  data() {
    return {
      count: 0
    }
  }
}
</script>