<template>
 <li class="context-menu-item">
   <slot></slot>
 </li>
</template>

<script>
export default {
  name: "ContextMenuItem",
  methods:{
    hi(){
      alert("masaka...?")
    }
  }
}
</script>

<style scoped>
.context-menu-item {
  display: flex;
  cursor: pointer;
  padding: 8px 15px;
  align-items: center;
  border-bottom: 1px solid #ebebeb;
}

.context-menu-item:hover {
  background-color: #67cdcc;
}
</style>