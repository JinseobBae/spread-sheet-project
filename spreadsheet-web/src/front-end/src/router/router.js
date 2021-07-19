import Vue from 'vue'
import Router from 'vue-router'
import Sheet from '../components/Sheet'
import Home from '../components/Home'


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
            path : '/sheet/:name',
            name : 'Sheet',
            component : Sheet
        }
    ]
})

