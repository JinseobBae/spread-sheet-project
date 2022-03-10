<template>
  <div class="modal">
    <div class="overlay"/>
    <div class="spinner-container">
      <div class="spinner" />
    </div>
  </div>

</template>

<script>
export default {
  name: "LoadingSpinner"
}
</script>

<style scoped>
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
  opacity: 0.5;
  background-color: black;
}

.spinner-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 240px;
}
.spinner {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 5px solid #e0e0e0;
  border-bottom: 5px solid #fe9616;
  animation: spin 1s linear infinite;
  position: relative;
}
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>