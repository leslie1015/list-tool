import Vue from 'vue'
import Vuex from 'vuex'
import menusModule from './modules/menusModule'
Vue.use(Vuex);
const store = new Vuex.Store({
    modules: {
        menusModule
    }
})
export default store;