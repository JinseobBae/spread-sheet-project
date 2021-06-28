<template>
  <vue-excel-editor v-model="rows" >
    <vue-excel-column field="col1"   label="User" />
    <vue-excel-column field="col2"   label="Name" />
    <vue-excel-column field="col3"  label="Contact" />
    <vue-excel-column field="col4" label="Gender" />
    <vue-excel-column field="col5"    label="Age" />
    <vue-excel-column field="col6"  label="Date Of Birth" />
    <vue-excel-column field="col7"  label="Date Of Birth" />
    <vue-excel-column field="col8"  label="Date Of Birth" />
    <vue-excel-column field="col9"  label="Date Of Birth" />
    <vue-excel-column field="col10"  label="Date Of Birth" />
  </vue-excel-editor>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Sheet',
  data() {
    return {
      rows: [
      ]
    }
  },
  created() {
    axios.get('/sheet/data?sheetName=TEST_SHEET')
        .then(response => {
          this.rows = response.data
        })
        .catch(error => {
          console.log(error)
        })

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
