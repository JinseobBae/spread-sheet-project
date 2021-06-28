import Vue from 'vue'
import Router from 'vue-router'
import Sheet from '../components/Sheet'


Vue.use(Router)

export default new Router({
    routes: [
        {
            path : '/',
            name : 'Sheet',
            component : Sheet
        }
    ]
})

