import Vue from "vue";
import VueExcelEditor from "vue-excel-editor";


Vue.use(VueExcelEditor)
const BaseEditor = Vue.options.components["vue-excel-editor"]
const ExcelEditor = BaseEditor.extend({
    methods: {
        newRecord(rec, selectAfterDone, noLastPage, isUndo) {
            console.error("rec", rec);
            if (typeof rec === 'undefined') rec = {}
            this.fields.map(f => {
                if (typeof rec[f.name] === 'undefined') {
                    if (f.keyField)
                        rec[f.name] = '§' + this.tempKey()
                    else
                        rec[f.name] = null
                }
            })
            const id = rec.$id || this.tempKey()
            rec.$id = id

            const rowPos = rec.pos
            this.value.splice(rowPos, 0, rec)
            this.table.splice(rowPos, 0, rec)

            if (selectAfterDone) this.selected[rowPos] = id
            Object.keys(rec).forEach(name => {
                const field = this.fields.find(f => f.name === name)
                if (field) this.updateCell(rec, field, rec[name], isUndo)
            })
            // this.refresh()
            if (!noLastPage) this.lazy(() => {
                // this.lastPage()
                // this.moveToSouthWest()
            })
            return rec
        }
    }
})


export default ExcelEditor