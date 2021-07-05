<template>
  <vue-excel-editor ref="grid" v-model="rows"  no-header-edit filter-row disable-panel-filter  :localized-label="labels" >
    <vue-excel-column field="col1"   label="클래스명" width="400px"  />
    <vue-excel-column field="col2"   label="CLASS_ID" width="100px" text-align="center"/>
    <vue-excel-column field="col3"  label="프로퍼티명" width="150px"/>
    <vue-excel-column field="col4" label="PROPERTY_ID" width="120px" text-align="center"/>
    <vue-excel-column field="col5"    label="컬럼명" width="110px"/>
    <vue-excel-column field="col6"  label="데이터타입" width="100px"/>
    <vue-excel-column field="col7"  label="테이블명" width="150px"/>
    <vue-excel-column field="col8"  label="설명" width="250px"/>
    <vue-excel-column field="col9"  label="기타사항" width="250px"/>
    <vue-excel-column field="col10"  label="ROW_SEQ" invisible />
  </vue-excel-editor>
</template>

<script>
import { fetchRows } from '@/api/api'
import { label } from '@/label/label.kr'
import { setLabel } from "@/label/colLabel";

export default {
  name: 'Sheet',
  data() {
    return {
      rows: [],
      labels : label
    }
  },
  created() {
    fetchRows(this.$route.params.name)
        .then(rows => { // api.js에서 넘기는 결과값
          this.rows = rows
        })
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
  mounted() {
    setLabel(this)
  }
}
</script>

<style>
</style>
