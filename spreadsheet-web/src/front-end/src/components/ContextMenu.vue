<template>
 <div class="context-menu" ref="context" tabindex="0" v-show="show" @blur="close" :style="style">
     <slot></slot>
 </div>
</template>

<script>
import Vue from 'vue';

export default {
  name: "ContextMenu",
  props : {
    display: Boolean
  },
  data(){
    return{
      left: 0,
      top: 0,
      show: false,
      target: ''
    }
  },
  computed: {
    style(){
        return{
          top: this.top + 'px',
          left : this.left + 'px'
        }
    }
  },
  methods: {
    // closes context menu
    close(){
      this.show = false;
      this.left = 0;
      this.top = 0;
    },
    open(evt){
      // updates position of context menu
      this.left = evt.pageX || evt.clientX;
      this.top = evt.pageY || evt.clientY;
      // make element focused
      // @ts-ignore
      Vue.nextTick(() => this.$el.focus());
      this.show = true;
      this.target = evt.target
    },
    getTarget(){
      return this.target
    }

  },
}
</script>

<style scoped>
.context-menu {
  position: fixed;
  background: white;
  z-index: 999;
  outline: none;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  cursor: pointer;
}

</style>