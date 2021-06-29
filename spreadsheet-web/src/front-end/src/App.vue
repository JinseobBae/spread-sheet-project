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

export default {
  name: 'app',
  data(){
    return {
      sheetList : ['데이터표준화' , '코드-메시지배포' , '모델-데이터베이스-이관관리']
    }
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
