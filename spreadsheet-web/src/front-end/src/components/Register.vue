<template>
  <div class="add_sheet">
    <h1>Sheet 추가하기</h1>
    <form v-on:submit="submitSheet" >
      <h4>Category<span>*</span></h4>
      <select v-model="selectedCategory">
        <option :value="null" disabled>카테고리를 선택하세요.</option>
        <option v-for="category in categoryList" v-bind:key="category.categoryName" v-bind:value="category.categoryName">
          {{category.categoryName}}
        </option>
      </select>
      <button id="show-modal" @click="openModal">+</button>
      <h4>Sheet 명<span>*</span><p>모든 Category 내에서 중복 불가</p></h4>
      <input type="text" name="name" v-model="formData.sheet" placeholder="Sheet명을 입력해주세요." />
      <div class="btn-block">
        <button type="submit">추가</button>
      </div>
    </form>

    <Modal v-if="showModal" @close-modal="showModal = false" @push-category="pushCategory">
      <CategoryAdd @push-category="pushCategory" msg=""/>
    </Modal>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import CategoryAdd from "@/components/CategoryAdd";
import {findAllCategories, addSheet} from "@/api/api";

export default {
  name: "Register",
  components : {
    CategoryAdd,
    Modal
  },
  data() {
    return {
      showModal: false,
      selectedCategory: '',
      categoryList: [],
      formData: {}

    };
  },

  methods:{
    openModal(evt){
      evt.preventDefault()
      this.showModal =true
    },
    submitSheet(evt){
      evt.preventDefault()

      if(this.selectedCategory !== '' && this.formData.sheet !== undefined && this.formData.sheet.trim() !== ''){
        addSheet(this.selectedCategory, this.formData.sheet)
      }else{
        alert("값을 전부 입력해주세요.")
      }
      // window.location.reload()
    },
    pushCategory(categoryName){
      let isDup = false;

      this.categoryList.forEach((ec) => {
        if(ec.categoryName === categoryName){
          alert("중복된 카테고리입니다.");
          isDup = true;
        }
      })

      if(!isDup){
        this.categoryList.push({categoryName : categoryName})
        this.showModal = false
      }
    },

  },

  mounted() {
    findAllCategories().then(response => {
      this.categoryList = response
    })
  }

}
</script>

<style scoped>

html, body {
  min-height: 100%;
}
body, div, form, input, select {
  padding: 0;
  margin: 0;
  outline: none;
  font-family: Roboto, Arial, sans-serif;
  font-size: 14px;
  color: #666;
  line-height: 22px;
}
p {
  font-size : 11px;
  margin-top: 10px;
}
h1{
  margin: 15px 0 30px;
}

h1, h4 {
  font-weight: 400;
}
h4 {
  margin: 20px 0 4px;
  font-weight: 400;
}
span {
  color: red;
}
.small {
  font-size: 10px;
  line-height: 18px;
}
.add_sheet {
  display: flex;
  justify-content: center;
  align-items: center;
  height: inherit;
  padding: 3px;
  flex-direction: column;
}
form {
  width: 50%;
  padding: 20px;
  background: #fff;
  box-shadow: 0 2px 5px #ccc;
  text-align: left;
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
.title-block select, .title-block input {
  margin-bottom: 10px;
}
select {
  padding: 7px 0;
  border-radius: 3px;
  border: 1px solid #ccc;
  background: transparent;
}
select, table {
  width: 50%;
}
option {
  background: #fff;
}
.day-visited, .time-visited {
  position: relative;
}
input[type="date"]::-webkit-inner-spin-button {
  display: none;
}
input[type="time"]::-webkit-inner-spin-button {
  margin: 2px 22px 0 0;
}
.day-visited i, .time-visited i, input[type="date"]::-webkit-calendar-picker-indicator {
  position: absolute;
  top: 8px;
  font-size: 20px;
}
.day-visited i, .time-visited i {
  right: 5px;
  z-index: 1;
  color: #a9a9a9;
}
[type="date"]::-webkit-calendar-picker-indicator {
  right: 0;
  z-index: 2;
  opacity: 0;
}
.question-answer label {
  display: block;
  padding: 0 20px 10px 0;
}
.question-answer input {
  width: auto;
  margin-top: -2px;
}
th, td {
  width: 18%;
  padding: 15px 0;
  border-bottom: 1px solid #ccc;
  text-align: center;
  vertical-align: unset;
  line-height: 18px;
  font-weight: 400;
  word-break: break-all;
}
.first-col {
  width: 25%;
  text-align: left;
}
textarea {
  width: calc(100% - 6px);
}
.btn-block {
  margin-top: 20px;
  text-align: center;
}
button {
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
@media (min-width: 568px) {
  .title-block {
    display: flex;
    justify-content: space-between;
  }
  .title-block select {
    width: 30%;
    margin-bottom: 0;
  }
  .title-block input {
    width: 31%;
    margin-bottom: 0;
  }
  th, td {
    word-break: keep-all;
  }
}

#show-modal{
  width: 50px;
  height: 40px;
  margin-left: 10px;

  /*width: 150px;*/
  /*padding: 10px;*/
  /*border: none;*/
  /*-webkit-border-radius: 5px;*/
  /*-moz-border-radius: 5px;*/
  /*border-radius: 5px;*/
  /*background-color: #095484;*/
  /*font-size: 16px;*/
  /*color: #fff;*/
  /*cursor: pointer;*/
}
</style>