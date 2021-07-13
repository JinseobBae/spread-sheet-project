import Vue from "vue";
import VueExcelEditor from "vue-excel-editor";


Vue.use(VueExcelEditor)
const BaseEditor = Vue.options.components["vue-excel-editor"]
const ExcelEditor = BaseEditor.extend({
    methods: {
        newRecord(rec) {
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

            return rec
        },
        async updateCell (row, field, newVal, isUndo) {
            switch(row.constructor.name) {
                case 'String': // $id
                    row = this.value.find(r => r.$id === row) // id
                    break
                case 'Number':
                    row = this.table[row] // tablePos
                    break
                case 'Object': // record object
                    break
                default:
                    throw new Error('Invalid row argument type')
            }
            switch(field.constructor.name) {
                case 'String': // field name
                    field = this.fields.find(f => f.name === field)
                    break
                case 'Number': // field pos
                    field = this.fields[field]
                    break
                case 'Object': // field object
                    break
                default:
                    throw new Error('Invalid field argument type')
            }
            if (!field) throw new Error('Invalid field argument')
            if (!row) return // No row is found

            const oldVal = row[field.name]
            const oldKeys = this.getKeys(row)

            if (field.change) {
                let result = await field.change(newVal, oldVal, row, field)
                if (result === false) return
            }

            row[field.name] =  newVal
            console.error(row,field)
            setTimeout(() => {
                const transaction = {
                    $id: row.$id,
                    keys: this.getKeys(row),
                    oldKeys: oldKeys,
                    name: field.name,
                    field: field,
                    oldVal: typeof oldVal !== 'undefined' ? oldVal : '',
                    newVal: newVal,
                    err: '',
                    idx: row.rowSeq || parseInt(row.pos) + 1
                }

                if (field.validate !== null) transaction.err = field.validate(newVal, oldVal, row, field)
                if (field.mandatory && newVal === '')
                    transaction.err += (transaction.err ? '\n' : '') + field.mandatory
                this.setFieldError(transaction.err, row, field)

                if (this.validate !== null) {
                    transaction.rowerr = this.validate(newVal, oldVal, row, field)
                    this.setRowError(transaction.rowerr, row)
                }

                if (field.summary)
                    this.calSummary(field.name)

                this.lazy(transaction, (buf) => {
                    this.$emit('update', buf)
                    if (!isUndo) this.redo.push(buf)
                }, 50)
            })
        }
    }
})


export default ExcelEditor