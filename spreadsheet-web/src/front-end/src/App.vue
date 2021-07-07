<template>
  <div id="app">
    <vue-navigation-bar :options="navbar"/>
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
      sheetList : [],
      navbar: fetchAllSheets()
    }
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

}
</style>
