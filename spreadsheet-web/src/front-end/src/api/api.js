import axios from 'axios';

const fetchRows = (sheetName) => {
    return axios.get('sheet/data', {
        params : {
            sheetName: sheetName
        }
    }).then( response => {
        return response.data  // 성공 시 data를 return -> promise
    }).catch(error => {
        alert(error.response.data.message)
    })
}

const fetchLables = (sheetName) =>{
    return axios.get('sheet/label', {
        params : {
            sheetName: sheetName
        }
    }).then( response => {
        return response.data  // 성공 시 data를 return -> promise
    }).catch(error => {
        alert(error.response.data.message)
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
            },
            {
                type: "link",
                text: "Kendo-TEST",
                path: "/kendo"
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
                    subMenu.path = '/sheet/'+ category + '/' + sheet.sheetName
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
            alert(error.response.data.message)
        })

    return nav
}

const addRow = (sheetName, idx) => {
    axios.post('sheet/row',
        {
            sheetName : sheetName,
            rowSeq : idx
             },
        {
            headers: {
                'Content-Type' : 'application/json'
            }
        }
    ).catch( error => {
        alert(error.response.data.message)
    })
}

const delRow = (sheetName, idx) => {
    axios.delete('sheet/row', {
            data: {
                sheetName : sheetName,
                rowSeq : idx
            }
        }
    ).catch( error => {
        alert(error.response.data.message)
    })
}

const updateRow = (sheetName, idx, row) => {
    axios.put('sheet/row', {
        sheetName : sheetName,
        rowSeq : idx,
        value : row.val,
        updatedCol : row.name
    }).catch( error => {
        alert(error.response.data.message)
    })
}

const findSearchRow = (searchValue) => {
    return axios.get('sheet/search', {
        params : {
            searchValue : searchValue
        }
    }).then( response => {
        return response.data  // 성공 시 data를 return -> promise
    }).catch(error => {
        alert(error.response.data.message)
    })
}

const findRowKendo = (sheetName) => {
    return axios.get('sheet/search-kendo', {
        params : {
            sheetName : sheetName
        }
    }).then( response => {
        return response.data
    }).catch(error => {
        alert(error.response.data.message)
    })
}

const changeSheetData = (allData, sheetName) => {
    console.log(allData);
    window.mytest = allData;
    axios.post('sheet/kendo-change',
        {
            rows : allData.rows,
            columns : allData.columns,
            frozenColumns : allData.frozenColumns,
            frozenRows : allData.frozenRows,
            sheetName : sheetName
        },
        {
            headers: {
                'Content-Type' : 'application/json'
            }
        }
    )
}

export { fetchRows , fetchLables , fetchAllSheets, addRow , delRow , updateRow , findSearchRow , findRowKendo, changeSheetData}