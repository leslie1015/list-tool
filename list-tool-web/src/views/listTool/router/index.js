import Vue from "vue";
import Router from "vue-router";

import welcome from "../components/welcome.vue";
import error from "../components/error.vue";

Vue.use(Router)
export default new Router({
    linkActiveClass: 'selected',
    mode: 'hash',
    base: process.env.BASE_URL,
    // 默认初始路由
    routes: [{
        path: '/',
        name: 'welcome',
        component: welcome
    },
        {
            path: '/error',
            name: 'error',
            component: error
        }
    ]

})