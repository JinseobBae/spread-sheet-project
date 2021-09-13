<template>
  <div>
    <spreadsheet ref="spreadsheet"
                 :sheetsbar="false"
                 :toolbar="toolbarSetting"
                 :rows="1000"
                 v-on:render="onRender"
                 v-on:select="onSelect"
                 v-on:change="onChange"
                 v-on:changeformat="onChangeFormat"
                 v-on:excelexport="onExcelExport"
                 v-on:excelimport="onExcelImport"
                 v-on:pdfexport="onPdfExport"
                 v-on:insertsheet="onInsertSheet"
                 v-on:removesheet="onRemoveSheet"
                 v-on:renamesheet="onRenameSheet"
                 v-on:selectsheet="onSelectSheet"
                 v-on:unhidecolumn="onUnhideColumn"
                 v-on:unhiderow="onUnhideRow"
                 v-on:hidecolumn="onHideColumn"
                 v-on:hiderow="onHideRow"
                 v-on:deletecolumn="onDeleteColumn"
                 v-on:deleterow="onDeleteRow"
                 v-on:insertcolumn="onInsertColumn"
                 v-on:insertrow="onInsertRow">
      <spreadsheet-sheet :name="'Data sheet'"
                         :rows="rows"
                         :columns="columns"
                         :data-source="datas"

      >
      </spreadsheet-sheet>
    </spreadsheet>
  </div>
</template>

<script>

// window.JSZip = JSZip;

import {findRowKendo, changeSheetData} from '@/api/api'

export default {
  name: 'App',
  mounted: function () {
    var spreadsheet = this.$refs.spreadsheet.kendoWidget();
    spreadsheet.element.css('height', '800px');
    spreadsheet.element.css('width', '100%');
    spreadsheet.resize();
  },
  data(){
    return {
      toolbarSetting : {data : false},
      rows : [{
        height:50,
        cells :[{textAlign:"center", verticalAlign:"center"}]
      }],
      columns : [],
      datas: {
        transport: {
          read: (options) => {
            this.dataInit(options)
          },
          update: (options) => {
            console.log(options)
            alert("submit!!")
          }
        },
        autoSync : true

      }
    }
  },

  watch : {
    $route(to, from){ // router 변경 감지
      if( to.path !== from.path){
        this.$router.go(); // 화면 refresh
      }
    }
  },

  methods: {
    dataInit(options){
      findRowKendo(this.$route.params.name).then( r => {
        options.success(r)
      })
    },
    myTest(){
      alert("myTEst")
    },
    onRender () {
      // console.log("Spreadsheet is rendered");
    },
    onSelect (arg) {
      console.log("New range selected. New value: " + arg.range.value());
    },
    onChange (arg) { // cell update
      // console.log(JSON.parse(JSON.stringify(arg)));
      // console.log(JSON.stringify(arg.range._sheet));
      changeSheetData(arg.range._sheet.toJSON())
    },
    onChangeFormat (arg) {
      console.log("Format of the range with value " + arg.range.value() + " changed to " + arg.range.format());
    },
    onExcelExport () {
      console.log("Spreadsheet is exported to Excel");
    },
    onExcelImport (arg) {
      console.log(arg.file.name + " file is about to be imported in the Spreadsheet");
    },
    onPdfExport () {
      console.log("Spreadsheet is exported to PDF");
    },
    onInsertSheet () {
      console.log("Inserted new Sheet");
    },
    onRemoveSheet (arg) {
      console.log(arg.sheet.name() + " sheet removed");
    },
    onRenameSheet (arg) {
      console.log(arg.sheet.name() + " renamed to " + arg.newSheetName);
    },
    onSelectSheet (arg) {
      console.log(arg.sheet.name() + " sheet is selected");
    },
    onUnhideColumn (arg) {
      console.log("The column at index " + arg.index + " on sheet " + arg.sheet.name() + " is unhidden");
    },
    onUnhideRow (arg) {
      console.log("The row at index " + arg.index + " on sheet " + arg.sheet.name() + " is unhidden");
    },
    onHideColumn (arg) {
      console.log("The column at index " + arg.index + " on sheet " + arg.sheet.name() + " is hidden");
    },
    onHideRow (arg) {
      console.log("The row at index " + arg.index + " on sheet " + arg.sheet.name() + " is hidden");
    },
    onDeleteColumn (arg) {
      console.log("The column at index " + arg.index + " on sheet " + arg.sheet.name() + " is deleted");
    },
    onDeleteRow (arg) {
      console.log("The row at index " + arg.index + " on sheet " + arg.sheet.name() + " is deleted");
    },
    onInsertColumn (arg) {
      console.log("New column at index " + arg.index + " on sheet " + arg.sheet.name() + " is inserted");
    },
    onInsertRow (arg) {
      console.log("New row at index " + arg.index + " on sheet " + arg.sheet.name() + " is inserted");
    }
  },
  created(){

  }
}
</script>