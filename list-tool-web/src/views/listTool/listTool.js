import Vue from "vue";
import listTool from "./listTool.vue";

import router from "./router/index";
import store from "./store/index";
import iView from "iview";
import "iview/dist/styles/iview.css"; // 使用 CSS

Vue.config.productionTip = false;

Vue.use(iView);

import base from "../../js/base.js";
Vue.use(base);

new Vue({
  router,
  store,
    components: {
        listTool
    },
  render: h => h(listTool)
}).$mount("#listTool");
