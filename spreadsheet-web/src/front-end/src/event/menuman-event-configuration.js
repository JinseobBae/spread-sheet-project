import '@sj-js/menuman/src/css/menuman.css'
import MenuMan from '@sj-js/menuman'
import Util from '@/util/util'

export default class MenumanEventConfiguration {

    constructor() {

        // You can customize theme on any css file - (ref: menuman.css)
        this.menuman = new MenuMan().setTheme('test-1');

        this.sheetContext = null;

    }

    setup(sheetContext){

        this.sheetContext = sheetContext

        this.setupMenu()

        this.setupMenuBoard()

    }

    setupMenu(){
        let that = this;

        this.menuman.addMenu(

            new MenuMan.Menu('new_record').setTitle('➕ 행에 삽입')
                .setBoardLoadHandler(function(event){
                    let index = Util.findLastChildNodeInnerText(event.target, 2);
                    event.menu.element.innerHTML = ('➕ ' +index+ '행 아래에 삽입');
                })
                .setRunHandler(function(element){
                    //1. find record number
                    let index = Util.findLastChildNodeInnerText(element, 2);
                    index = parseInt(index);
                    //2. new record to found number
                    that.sheetContext.$refs.grid.newRecord({pos:index}, false)
                }),

            new MenuMan.Menu('del_record').setTitle('➖ 행 삭제')
                .setBoardLoadValidateHandler(function(){
                    return document.querySelectorAll(".select").length == 0
                })
                .setBoardLoadHandler(function(event){
                    let index = Util.findLastChildNodeInnerText(event.target, 2);
                    event.menu.element.innerHTML = ('➖ ' +index+ '행을 삭제');
                })
                .setRunHandler(function(event){
                    //1. find record number
                    let index = Util.findLastChildNodeInnerText(event.target, 2);
                    index = parseInt(index) -1;
                    //2. delete record by found number
                    that.sheetContext.$refs.grid.deleteRecord(index)
                }),

            new MenuMan.Menu('del_records').setTitle('➖ 선택된 행 삭제')
                .setBoardLoadValidateHandler(function(){
                    let selectedNodes = document.querySelectorAll(".select");
                    return selectedNodes.length > 0
                })
                .setBoardLoadHandler(function(event){
                    let selectedNodes = document.querySelectorAll(".select");
                    event.menu.element.innerHTML = ('➖ ' +selectedNodes.length+ '개의 선택된 행을 삭제');
                })
                .setRunHandler(function(){
                    //1. delete selected record
                    that.sheetContext.$refs.grid.deleteSelectedRecords()
                }),

            new MenuMan.Menu('search_selection').setTitle('🔍 \'선택된 영역의 문자\'로 검색')
                .setBoardLoadValidateHandler(function(){
                    return that.menuman.getSelectionText() != "";
                })
                .setBoardLoadHandler(function(event){
                    let nowSelectionText = that.menuman.getSelectionText();
                    event.menu.element.innerHTML = ('🔍 \'' +nowSelectionText+ '\' (으)로 검색');
                })
                .setRunHandler(function(){
                    //TODO: It can be applied to something.
                    alert(that.menuman.getSelectionText());
                })
        );
    }

    setupMenuBoard(){
        this.menuman.addMenuBoard(

            new MenuMan.MenuBoard('default_ctx').setMenus('search_selection'),

            new MenuMan.MenuBoard('row_ctx').setMenus('new_record', 'del_record', 'del_records').setPriority(1).setElementMatchCondition({"id":"rid-*"}).setElementMatchSearchParentsLevel(2)

        );
    }

}