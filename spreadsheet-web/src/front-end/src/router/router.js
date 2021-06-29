import Vue from 'vue'
import Router from 'vue-router'
import Sheet from '../components/Sheet'
import Home from '../components/HelloWorld'


Vue.use(Router)

export default new Router({
    routes: [
        {
            path : '/',
            name : 'Home',
            component : Home
        },
        {
            path : '/sheet/:name',
            name : 'Sheet',
            component : Sheet
        }
    ]
})

