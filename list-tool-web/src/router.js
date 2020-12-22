import Vue from "vue";
import Router from "vue-router";
// import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
    mode: "hash",
    base: process.env.BASE_URL,
    routes: [
        // {
        //     path: "/",
        //     name: "home",
        //     component: () => import("./views/Home.vue")
        // },
        // {
        //     path: "/about",
        //     name: "about",
        //     component: () =>
        //         import("./views/About.vue")
        // },
        // {
        //     path: "/articleList/:typeId",
        //     name: "articleList",
        //     component: () =>
        //         import("./components/article/ArticleList.vue")
        // },
        // {
        //     path: "/articleDetail/:articleId",
        //     name: "articleDetail",
        //     component: () =>
        //         import("./components/article/ArticleDetail.vue")
        // }
        // ,
        // {
        //     path: "/addArticle",
        //     name: "addArticle",
        //     component: () =>
        //         import("./components/article/ArticleEdit.vue")
        // }
    ]
});
