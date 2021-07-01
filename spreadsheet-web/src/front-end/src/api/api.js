import axios from 'axios';

function fetchRows(sheetName){
    return axios.get('/sheet/data', {
        params : {
            sheetName: sheetName
        }
    }).then( response => {
        return response.data  // 성공 시 data를 return -> promise
    }).catch(error => {
        console.log(error)
    })
}

function fetchLables(sheetName){
    return axios.get('/sheet/label', {
        params : {
            sheetName: sheetName
        }
    }).then( response => {
        return response.data  // 성공 시 data를 return -> promise
    }).catch(error => {
        console.log(error)
    })
}

export { fetchRows , fetchLables }