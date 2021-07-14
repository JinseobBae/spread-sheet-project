import Util from "@/util/util";
import { addRow, delRow } from "@/api/api";

export default class SheetEvent {

    static initSheetEvent(context){
        //Event - When document is fully loaded
        window.addEventListener("load", function(){

            //Elements for search - auto enter press
            let searchColumnElements = document.querySelectorAll(".cell.column-filter.column-filter")
            Util.makeAutoEnterPressedElement(searchColumnElements);

            //Right click event
            document.body.addEventListener("contextmenu", function(event){
                openContext(event, context);
            });

        });
    }
    static newRecord(element, sheetContext){
        //pos 찾아
        let pos = findPos(element, 2);
        //끼워 넣기
        if (pos !== null && pos !== undefined && pos !== "" && pos !== -1){
            sheetContext.$refs.grid.newRecord({pos : pos})
            addRow(sheetContext.$route.params.name, parseInt(pos) + 1)
        }
    }

    static delRecord(element, sheetContext){
        let pos = findPos(element, 2);
        if (pos !== null && pos !== undefined && pos !== "" && pos !== -1){
            sheetContext.$refs.grid.deleteRecord(parseInt(pos) - 1 )
            delRow(sheetContext.$route.params.name, parseInt(pos))
        }
    }
}

const openContext = (event, sheetContext) =>{
    event.preventDefault()
    //pos 찾아
    let pos = findPos(event.target, 2);
    //끼워 넣기
    if (pos !== null && pos !== undefined && pos !== "" && pos !== -1){
        sheetContext.$refs.menu.open(event)
    }

}

const findPos = (node, targetLevel) => {
    let level = -1;
    let pos = -1;
    while(++level < targetLevel){

        //자식이 있으면, 다음 자식으로 이동 => 반복
        //자식이 없으면, 부모가 pos를 갖고 있는지 확인 => 갖고 있으면 이벤트적용
        if(node.children === null || node.children === undefined || node.children.length === 0){
            let pPos = node.parentNode.getAttribute("pos")
            if (pPos !== null && pPos !== undefined && pPos !== ""){
                pos = node.innerText
            }
            break;
        }else{
            node = node.children[0]
        }
    }
    return pos
}


