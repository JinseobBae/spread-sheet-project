import {fetchLables} from "@/api/api";

function setLabel(vue){
    fetchLables(vue.$route.params.name)
        .then(colLabel => {
            const label = JSON.parse(JSON.stringify(colLabel))
            vue.$refs.grid.fields.forEach((field) => {
                checkLabel(field, 'col1', label.col1)
                checkLabel(field, 'col2', label.col2)
                checkLabel(field, 'col3', label.col3)
                checkLabel(field, 'col4', label.col4)
                checkLabel(field, 'col5', label.col5)
                checkLabel(field, 'col6', label.col6)
                checkLabel(field, 'col7', label.col7)
                checkLabel(field, 'col8', label.col8)
                checkLabel(field, 'col9', label.col9)
                checkLabel(field, 'col10', label.col10)
            })
            vue.$forceUpdate()
        })
}

function checkLabel(field, col, label){
    if(field.name === col){
        if(label == null){
            field.invisible = true
        }else{
            field.label = label
            field.invisible = false
        }
    }
}

export { setLabel }