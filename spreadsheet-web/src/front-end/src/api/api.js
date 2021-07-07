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

function fetchAllSheets(){

    const nav = {
        elementId: "main-navbar",
        isUsingVueRouter: true,
        menuOptionsLeft: []
    }

    axios.get('/sheet/sheet')
        .then( response => {
            const sheets = response.data
            const subMenus = [];
            sheets.forEach((sheet) => {
                const subMenu = {
                    isLinkAction: false,
                    type: "link"
                }
                subMenu.text = sheet.sheetName
                subMenu.path = '/sheet/' + sheet.sheetName
                subMenus.push(subMenu)
            })
            nav.menuOptionsLeft.push(
                {
                    type: "link",
                    text: "테스트입니다.",
                    arrowColor: "#659CC8",
                    subMenuOptions: subMenus
                }
            )
        })
        .catch(error => {
            console.log(error)
        })

    return nav
}

export { fetchRows , fetchLables , fetchAllSheets }