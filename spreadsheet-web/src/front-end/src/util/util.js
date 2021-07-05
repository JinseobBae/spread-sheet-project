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

}