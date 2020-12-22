<template>
    <Layout>
        <div class="home-wrapper layout">
            <div class="content-wrapper">
                <template v-if="showHome">
                    <div class="side-bar">
                        <Tree-view></Tree-view>
                    </div>
                    <div class="continer">
                        <router-view></router-view>
                    </div>
                </template>
            </div>
        </div>
        <!--<Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>-->
    </Layout>
</template>
<script>

    import listContent from './contents/content.vue'
    import TreeView from "./tree_menu/TreeView.vue";
    import TreeDetail from '../components/tree_menu/TreeDetail.vue';

    export default {

        name: "Home",
        data() {
            return {
                showHome: false,
                menuArr: [], // 遍历菜单menus时的临时变量
                dynamicRouters: [] // 动态路由数组

            }
        },
        created() {
            this.initMenuInfo();
        },
        mounted() {

        },
        methods: {
            refreshList: function () {
                this.$refs.listContent.refreshListPage(this.resourceId);
            },
            // 初始化菜单
            initMenuInfo() {
                // 如果本地已存在，则不去后台加载
                // if (sessionStorage.getItem('menuJson')) {
                //     // 添加动态路由
                //     this.addDynamicRouters(JSON.parse(sessionStorage.getItem('menuJson')));
                //     this.showHome = true;
                // } else {
                //
                // }
                this.httpGet(this.csbRouter + '/listQueue/getAppMenu/1', null, false)
                    .then(
                        (response) => {
                            if (response.data.code == '0') {
                                //本地缓存菜单信息字符串
                                // sessionStorage.removeItem('menuJson');
                                // sessionStorage.setItem('menuJson', JSON.stringify(response.data.value));
                                // 添加动态路由
                                this.addDynamicRouters(response.data.value);
                                // 初始化状态中的菜单信息，否则第一次加载menusModule#firstInit中的status.menus为null，会报错
                                // 只有通过store.commit才有效
                                this.$store.commit("initStateMenus", {menus: response.data.value});
                                // this.$router.push("/");
                                this.showHome = true;
                            } else if (response.data.code == '2') {
                                alert("认证失败，请登录");
                            } else {
                                alert("程序异常");
                            }
                        }
                    ).catch((response) => {
                    console.log(response);
                })
            },
            // 动态添加路由
            addDynamicRouters(menuObj) {
                // 组装需要渲染的动态路由
                this.assemblyRoutersArr(menuObj, this.menuArr);
                // 将404页面的路由加在最后，因为路由会从上往下匹配，上面的匹配不到最终会走到这里，匹配任意，进入404页面
                let errorRouter = {
                    path: '*',
                    redirect: '/error'
                };
                // 添加动态路由
                this.dynamicRouters.push(errorRouter);
                this.$router.addRoutes(this.dynamicRouters);
            },
            // 递归遍历菜单json，将其中type未link的提取出来拼成需要渲染的路由数组
            assemblyRoutersArr(json, arr) {
                if (json == undefined) {
                    return;
                }
                for (var i = 0; i < json.length; i++) {
                    var sonList = json[i].childQueues;
                    // 资源类型未link的，添加到路由中
                    if (json[i].resourceType == 'link') {
                        // 防止重复添加动态路由
                        if (this.containsRouter(json[i].resourceId.toString())) {
                            continue;
                        }

                        let routerObj = {
                            path: json[i].resourceUrl,
                            name: json[i].resourceId.toString(),
                            component: TreeDetail

                        };

                        this.dynamicRouters.push(routerObj);
                    }
                    if (sonList.length == 0) {
                        arr.push(json[i]);
                    } else {
                        this.assemblyRoutersArr(sonList, arr);
                    }
                }
            },

            containsRouter(resourceId) {
                let routers = this.dynamicRouters;
                if (routers.length == 0) {
                    return false;
                }
                for (var i = 0; i < routers.length; i++) {
                    if (routers.name == resourceId) {
                        return true;
                    }
                }
                return false;
            }
        },
        components: {
            TreeView,
            // 此处是动态添加的，显示未引用，其实是动态引用，不能删掉
            listContent,
            TreeDetail
        }

    };
</script>
<style scoped>
    .side-bar {
        width: 180px;
        height: 100%;
        font-size: 14px;

    }

    .continer {
        margin-left: 10px;
        width: calc(100% - 200px);
        height: 100%;
    }

    .home-wrapper {
        display: flex;
        flex-direction: column;
        flex: 1;
        width: 100%;
        height: 100%;
    }

    .content-wrapper {
        display: flex;
        flex-direction: row;
        width: 100%;
        /*height: calc(100% - 60px);*/
        height: 100%;
    }

    .layout {
        border: 1px solid #d7dde4;
        background: #ffffff;
        /*background-color: red;*/
        position: relative;
        border-radius: 4px;
        overflow: hidden;
        height: 100%;
    }

</style>