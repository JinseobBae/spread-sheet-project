<template>
  <div>
  <vue-excel-editor ref="grid" v-model="rows" @update="rowUpdate" no-header-edit filter-row disable-panel-filter  :localized-label="labels" :key="componentKey">
    <vue-excel-column field="col1"  width="300px"/>
    <vue-excel-column field="col2"  width="200px"/>
    <vue-excel-column field="col3"  width="150px"/>
    <vue-excel-column field="col4"  width="120px"/>
    <vue-excel-column field="col5"  width="110px"/>
    <vue-excel-column field="col6"  width="100px"/>
    <vue-excel-column field="col7"  width="150px"/>
    <vue-excel-column field="col8"  width="250px"/>
    <vue-excel-column field="col9"  width="250px"/>
    <vue-excel-column field="col10" />
  </vue-excel-editor>
    <context-menu :display="false" ref="menu">
      <context-menu-item @click.native="rowAdd"> ADD</context-menu-item>
      <context-menu-item @click.native="rowRemove">DELETE</context-menu-item>
    </context-menu>
  </div>
</template>

<script>
import { fetchRows , updateRow} from '@/api/api'
import { label } from '@/label/label.kr'
import { setLabel } from "@/label/colLabel";
import SheetEvent from "@/event/sheetEvent";

export default {
  name: 'Sheet',
  data() {
    return {
      rows: [],
      labels : label,
      componentKey : 0
    }
  },
  created() {
    fetchRows(this.$route.params.name)
        .then(rows => { // api.js에서 넘기는 결과값
          this.rows = rows
        })
  },
  mounted() {
    setLabel(this)
    SheetEvent.initSheetEvent(this)
  },
  watch : {
    $route(to, from){ // router 변경 감지
      if( to.path !== from.path){
        fetchRows(this.$route.params.name)
            .then(rows => {
              this.rows = rows
            })
        setLabel(this)
      }
    }
  },
  methods : {
    rowUpdate(rec){
      const row = rec[0]
      updateRow(this.$route.params.name, row.idx, {name : row.name, val : row.newVal} )
    },
    rerender(){
      this.componentKey += 1
      setLabel(this)
    },
    rowAdd(){
      const cellElement = this.$refs.menu.getTarget()
      SheetEvent.newRecord(cellElement, this)
      this.$refs.menu.close()

    },
    rowRemove(){
      const cellElement = this.$refs.menu.getTarget()
      SheetEvent.delRecord(cellElement, this)
      this.$refs.menu.close()
    }
  }
}
</script>

<style>
</style>
