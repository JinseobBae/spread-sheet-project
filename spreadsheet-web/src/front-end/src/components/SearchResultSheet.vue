<template>
  <div>
    <spreadsheet ref="spreadsheet_search"
                 :sheetsbar="false"
                 :toolbar="false"
                 :rows="5000"
                 >
      <spreadsheet-sheet :name="'Search sheet'"/>
    </spreadsheet>
  </div>
</template>

<script>
export default {
  name: "SearchResultSheet",

  mounted() {
    const spreadsheet = this.$refs.spreadsheet_search.kendoWidget();
    spreadsheet.element.css('height', '50vh');
    spreadsheet.element.css('width', '100%');
    spreadsheet.resize();

    const sheet = spreadsheet.activeSheet();
    sheet.range("A2").select()
  },

  methods: {
    myTest(){
      const spreadsheet = this.$refs.spreadsheet_search.kendoWidget();
      spreadsheet.element.css('height', '600px');
      spreadsheet.element.css('width', '100%');
      spreadsheet.resize();

      const sheet = spreadsheet.activeSheet();
      sheet.range("A4").select()
    }
  }
}
</script>

<style scoped>

</style>