<template>
  <div class="content">
    <h1>카테고리 추가</h1>
    <div id="add-category-modal-comment">
      <span>Sheet를 추가하지 않으면 해당 데이터는 유지되지 않습니다.</span>
    </div>
    <div>
      <input type="text" v-model="categoryName" placeholder="카테고리명을 입력해주세요." v-on:keyup.enter="addCategory"/>
    </div>
    <div>
      <button @click="addCategory">추가</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CategoryAdd",
  props: {
    msg: String
  },
  data(){
    return {
      categoryName: ''
    }
  },
  methods: {
    addCategory(){
      if(this.categoryName.trim() !== ''){
        this.$emit('push-category',this.categoryName)
      }else{
        alert("값을 입력해주세요.")
      }
    }
  }
}
</script>

<style scoped>
button {
  height: 50px;
  margin-top: 20px;
  width: 150px;
  padding: 10px;
  border: none;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  background-color: #007AFF;
  font-size: 16px;
  color: #fff;
  cursor: pointer;
}
button:hover {
  background-color: #0666a3;
}
input {
  width: calc(100% - 100px);
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 3px;
  vertical-align: middle;
}

input:hover, textarea:hover, select:hover {
  outline: none;
  border: 1px solid #095484;
  background: #e6eef7;
}

span{
  color: red
}

#add-category-modal-comment{
  margin-bottom: 10px;
}
</style>