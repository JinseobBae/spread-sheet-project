<template>
  <div class="home">
    <total-search/>
  </div>
</template>

<script>
// import Search from "@/components/Search";
import {searchFromAll} from "@/api/api"
import TotalSearch from "@/components/TotalSearch";

export default {
  name: 'Home',
  components:{
    'total-search' : TotalSearch
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

.home{
  height: 100%;
}
</style>
