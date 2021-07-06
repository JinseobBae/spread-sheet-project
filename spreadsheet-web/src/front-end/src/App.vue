<template>
  <div id="app">
    <header>
      <p></p>
      <p v-for="item in sheetList" :key="item">
        <router-link :to="`/sheet/${item}`"> {{item}}</router-link>
      </p>
    </header>
    <main>
      <router-view/>
    </main>
  </div>
</template>

<script>
import { fetchAllSheets } from './api/api'
import Util from "./util/util"

export default {
  name: 'app',
  data(){
    return {
      sheetList : []
    }
  },
  created() {
    fetchAllSheets()
        .then(sheets => {
          sheets.forEach((sheet) => {
            this.sheetList.push(sheet.sheetName)
          })

        })
  }
}

function init(){
  //Event - When document is fully loaded
  window.addEventListener("load", function(){

    //Elements for search - auto enter press
    let searchColumnElements = document.querySelectorAll(".cell.column-filter.column-filter")

    Util.makeAutoEnterPressedElement(searchColumnElements);

  });
}

init();

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
