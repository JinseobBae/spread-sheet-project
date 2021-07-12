import axios from 'axios';

const fetchRows = (sheetName) => {
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

const fetchLables = (sheetName) =>{
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

const fetchAllSheets = () =>{

    const nav = {
        elementId: "main-navbar",
        isUsingVueRouter: true,
        menuOptionsLeft: [
            {
                type: "link",
                text: "Home",
                path: "/"
            },
            {
                type: "spacer",
                text: "",
                path: ""
            }
        ]

    }

    axios.get('/sheet/sheet')
        .then( response => {
            const sheets = response.data

            for(const category in sheets){
                const subMenus = [];
                sheets[category].forEach((sheet) => {
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
                        text: category,
                        arrowColor: "#659CC8",
                        subMenuOptions: subMenus
                    }
                )
            }
        })
        .catch(error => {
            console.log(error)
        })

    return nav
}

export { fetchRows , fetchLables , fetchAllSheets }