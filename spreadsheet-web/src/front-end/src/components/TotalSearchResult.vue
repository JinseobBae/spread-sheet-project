<template>
  <div class="resultDetail">
    <div class="category_sheet">
      <div class="search_category">카테고리 : {{category}}</div>
      <div class="search_sheet">Sheet명 : {{sheet}}</div>

    </div>
    <div class="value_index">
      <div class="search_value">검색 값 : {{value}}</div>
      <div class="search_index">위치 : {{index}}</div>
    </div>
    <div class="link_sheet">
      <button class="search_router_link_btn" @click="emitResultData"> 바로 가기 </button>
      <router-link :to="{name: 'Sheet', params: {category: category, name: sheet}, query:{idx: index}} ">바로 가기</router-link>
    </div>

  </div>
</template>

<script>

export default {
  name: "TotalSearchResult",
  props:{
    category: String,
    sheet: String,
    value: String,
    index : String
  },
  data(){
    return{
    }
  },
  methods: {
    linkToIndex(){
      this.$router.push({name: 'Sheet', params: {category: this.category, name: this.sheet}, query:{idx: this.index} })
    },
    emitResultData(){
      this.$emit("test_emit", {category: this.category, sheet: this.sheet, value: this.value, index: this.index})
    }
  }
}
</script>

<style scoped>
.resultDetail {
  /*border: 1px solid #2d2d2d ;*/
  max-width: 90%;
  background: #fff;
  box-shadow: 0 2px 5px #ccc;
}

.category_sheet, .value_index{
  display : flex;
}

.search_category, .search_sheet, .search_index, .search_value {
  width: 90%;
  text-align: left;
  margin-top: 15px;
  margin-left: 10px;
}

.link_sheet {
  text-align: right;
  margin-right: 5px;
}

span {
  text-align: left;
}

.resultDetail:hover{
  background: #EAF2F5;
  opacity: 0.9;
}


</style>