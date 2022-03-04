<template>
  <div class="home">
    <vue-search/>
    <router-link :to="{name: 'Sheet', params: {category:'TEST', name:'TEST_SHEET'}, query:{idx:'A40'}}">TEST!!</router-link>
    <p>
      <button @click="searchAll">ddddddd</button>
    </p>

  </div>
</template>

<script>
import Search from "@/components/Search";
import {searchFromAll} from "@/api/api"

export default {
  name: 'Home',
  components:{
    'vue-search' : Search
  },
  methods: {
    searchAll(){
      searchFromAll("13")
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
