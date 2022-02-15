import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import KendoSheet from "@/components/KendoSheet";
import Register from "@/components/Register";


Vue.use(Router)

export default new Router({
    base : process.env.BASE_URL,
    routes: [
        {
            path : '/',
            name : 'Home',
            component : Home
        },
        {
            path : '/sheet/:category/:name',
            name : 'Sheet',
            component : KendoSheet,
        },
        {
            path : '/kendo',
            name :'Kendo',
            component : KendoSheet
        },
        {
            path : '/sheet/register',
            name : 'Register',
            component : Register
        }
    ]
})

