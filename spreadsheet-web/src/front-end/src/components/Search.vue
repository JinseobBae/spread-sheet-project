<template>
  <div>
    <div class="formSet container center_div ">
      <form class="center" id="searchForm" v-on:submit="onSubmitForm">
        <input class= "py-2 border-right-0 border" id="searchText" type="search" v-model="search" placeholder="Input text">
        <input id="search" type="submit" value="Search" >
      </form>
    </div>
    <div>
      <p v-if="time !== ''">
        [TOTAL : {{count}} / TIME : {{time}}ms]
      </p>
    </div>
    <div v-if="result.length > 0" class="result">
      <vue-excel-editor ref="resultGrid" v-model="result" no-header-edit disable-panel-filter  :localized-label="labels" >
        <vue-excel-column label="Category" field="category"  width="150px"/>
        <vue-excel-column label="SheetName" field="sheetName"  width="150px" :link="routeToLink"/>
        <vue-excel-column label="Column1" field="col1"  width="150px"/>
        <vue-excel-column label="Column2" field="col2"  width="150px"/>
        <vue-excel-column label="Column3" field="col3"  width="150px"/>
        <vue-excel-column label="Column4" field="col4"  width="150px"/>
        <vue-excel-column label="Column5" field="col5"  width="150px"/>
        <vue-excel-column label="Column6" field="col6"  width="150px"/>
        <vue-excel-column label="Column7" field="col7"  width="150px"/>
        <vue-excel-column label="Column8" field="col8"  width="150px"/>
        <vue-excel-column label="Column9" field="col9"  width="150px"/>
        <vue-excel-column label="Column10" field="col10" width="150px"/>
      </vue-excel-editor>
    </div>
  </div>
</template>

<script>
import { findSearchRow } from '@/api/api'
import { label } from '@/label/label.kr'

export default {
  name: "SearchResult",
  data() {
    return {
      search: "",
      result : [],
      count : "",
      time : "",
      labels: label
    }
  },
  methods: {
    onSubmitForm(e){
      e.preventDefault()
      if(this.search !== '' && this.search !== 'undefined'){
        findSearchRow(this.search).then( r => {
          this.result = r.list
          this.count = r.count
          this.time = r.time
        })
      }else{
        alert("Please, input search text")
      }

    },
    routeToLink(content){
      this.$router.push(`/sheet/${content}`)
      // console.log(content, record)
    }
  }
}
</script>

<style>

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
.result{
  margin-top: 30px;
}
</style>
