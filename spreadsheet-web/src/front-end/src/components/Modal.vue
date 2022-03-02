<template>
  <div class="modal">
    <div class="overlay" @click="$emit('close-modal')"></div>
    <div class="modal-card">
      <slot />
    </div>
  </div>
</template>

<script>
export default {
  // name: "CategoryAddModal"

}
</script>

<style scoped>
/* Modal */
.modal{
  display: inline-grid;
  align-content: center;
}
.overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
}
.overlay {
  opacity: 0.5;
  background-color: black;
}
.modal-card {
  position: relative;
  min-width: 40%;
  max-width: 50%;
  margin: auto;
  margin-top: 30px;
  padding: 20px;
  background-color: white;
  min-height: 200px;
  z-index: 10;
  opacity: 1;
}
</style>