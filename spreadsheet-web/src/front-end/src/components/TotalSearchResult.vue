<template>
  <div class="resultDetail">
    <div class="category_sheet">
      <div class="search_category">
        <span>카테고리</span>
        <p>{{category}}</p>
      </div>
      <div class="search_sheet">
        <span>Sheet명</span>
        <p>{{sheet}}</p>
      </div>

    </div>
    <div class="value_index">
      <div class="search_value">
        <span>검색 값</span>
        <p>{{value}}</p>
      </div>
      <div class="search_index">
        <span>위치</span>
        <p>{{index}}</p>
      </div>
    </div>
    <div class="link_sheet">
      <div class="search_sheet_view_container">
        <button class="search_router_link_btn" @click="emitResultData"> 바로 보기 🧐</button>
      </div>
      <div class="search_router_link_container">
        <button class="search_router_link_btn" @click="linkToIndex"> 바로 가기 ✈</button>

      </div>

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
      this.$emit("move_to_cell", {category: this.category, sheet: this.sheet, value: this.value, index: this.index})
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

span{
  font-size: 1vw;
}

p{
  font-size: 0.7vw;
}

.category_sheet, .value_index{
  display : flex;
}

.search_category, .search_sheet{
  width: 80%;
  text-align: left;
  margin-top: 10px;
  margin-left: 10px;
}

.search_index, .search_value{
  width: 80%;
  text-align: left;
  margin-left: 10px;
  overflow: hidden;
}

.link_sheet {
  display: flex;
  /*text-align: right;*/
  margin-right: 5px;
}

span {
  text-align: left;
}

.resultDetail:hover{
  background: #EAF2F5;
  opacity: 0.9;
}

.search_router_link_btn{
  border: none;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  background-color: #e6eef7;
  cursor: pointer;
}

.search_sheet_view_container, .search_router_link_container{
  margin-bottom: 10px;
  width: 50%;
  font-size: 0.8vw;
}

.search_sheet_view_container{
  text-align: left;
  margin-left: 15px;
}

.search_router_link_container{
  text-align: right;
  margin-right: 10px;
}


</style>