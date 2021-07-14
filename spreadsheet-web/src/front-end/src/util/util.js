export default class Util {

    static makeAutoEnterPressedElement(element){
        if (element instanceof Array || element instanceof NodeList){
            element.forEach( (it) => {
                Util.makeAutoEnterPressedElement(it);
            });
            return;
        }

        element.addEventListener("input", (event) => {
            let targetElement = event.target;

            //Fire 'Enter' key
            Util.triggerEnter(targetElement);

            //When a search event is fired with the 'Enter' key, a 'Selection' is generated which prevents typing.
            //So here's a trick to prevent this.
            window.getSelection().collapseToEnd();
        });
    }

    static triggerEnter(element){
        let event = document.createEvent('HTMLEvents');
        event.initEvent('keydown', false, true);
        event.which = 13;
        event.keyCode = 13;
        return element.dispatchEvent(event);
    }

    static findLastChildNodeInnerText(element, searchParentsLevel){
        let resultInnerText = null;

        let foundLastElement = Util.findLastChildNode(element, searchParentsLevel);

        if (foundLastElement !== null)
            resultInnerText = foundLastElement.innerText;

        return resultInnerText;
    }

    static findLastChildNode(element, searchParentsLevel){
        let foundLastElement = null;
        let currentSearchLevel = -1;
        let currentElement = element;

        while(++currentSearchLevel < searchParentsLevel){
            if (currentElement.children && currentElement.children.length > 0){
                currentElement = currentElement.children[0];
            }else{
                foundLastElement = currentElement;
                break;
            }
        }

        return foundLastElement;
    }

}