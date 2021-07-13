<template>
  <div id="app">
    <vue-navigation-bar :options="navbar"/>
    <main id="main">
      <router-view/>
    </main>
  </div>
</template>

<script>
import { fetchAllSheets } from './api/api'

export default {
  name: 'app',
  data(){
    return {
      sheetList : [],
      navbar: fetchAllSheets()
    }
  }
}

</script>

<style>
#main {
  margin-top: 100px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

}
</style>
