<template>
  <div style="height: 100%">
    <div class="formSet container center_div ">
      <form class="center" id="totalSearchForm" v-on:submit="searchSubmit">
        <input class= "py-2 border-right-0 border" id="searchText" type="search" v-model="search" placeholder="Input text">
        <input id="search" type="submit" value="Search" >
      </form>
    </div>

    <div id= "result_whole" >
      <div class="result_sheet" v-if="result.length > 0" >
        <resultSheetView refs="asdf"/>
      </div>
      <div class="result_data" v-if="result.length > 0">
        <div class="resultBody" v-for="data in result" v-bind:key="data.uuid">
          <result-view :category="data.category" :sheet="data.sheet" :index="data.index" :value="data.value" @test_emit="emitTestMethod"/>
        </div>
    </div>
    </div>

  </div>

</template>

<script>

import { searchFromAll } from "@/api/api";
import TotalSearchResult from "@/components/TotalSearchResult";
import SearchResultSheet from "@/components/SearchResultSheet";

export default {
  name: "TotalSearch",
  components: {
    'result-view' : TotalSearchResult,
    'resultSheetView' : SearchResultSheet,
  },
  data() {
    return {
      search: "",
      result : [],
    }
  },
  methods: {
    searchSubmit(e){
      e.preventDefault();
      if(this.search !== '' && this.search !== 'undefined'){
        searchFromAll(this.search).then(response => {
          this.result = response
        })
      }else{
        alert("검색어를 입력해주세요.")
      }

    },
    emitTestMethod(resultData){
      console.log(resultData)
      console.log(this.$refs)
      console.log(this.$refs.asdf)

    }
  }
}
</script>

<style scoped>
.formSet {
  width: auto;

}

#searchForm{
  /*border: 1px  solid #000066;*/
}

#searchText {
  border: 1px  solid #E8E8E8;
  width: 25vw;
  height: 5vh;
  font-size: 1em;
  outline: none;
}

#searchText:focus{
  outline: 2px  solid #67cdcc;
}

#search {
  border-left: 0;
  border-right: 1px solid #E8E8E8;
  border-bottom: 1px solid #E8E8E8;
  border-top: 1px solid #E8E8E8;
  /*margin-left: -10px;*/
  height: 5vh;
  font-size: 1em;
  cursor: pointer;
  background: none;
}

#search:hover{
  opacity: 0.75;
  background: #67cdcc;
}
.formSet{
  margin-bottom: 20px;
}

#result_whole{
  /*background: red;*/
  height: 100%;
  display: flex;
}

.result_sheet{
  float: left;
  margin-left: 10px;
  width: 70%;

}

.result_data{
  float: right;
  display: block;
  width: 30%;
  text-align: -webkit-center;
  height: 700px;
  overflow-y: scroll;
}

.resultBody{
  /*background: blue;*/
  margin : 10px 5px 5px 5px;
  max-width: 100%;

}

</style>