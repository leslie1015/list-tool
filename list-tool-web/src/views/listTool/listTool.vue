<template>
    <div id="listTool">
        <div class="header">
            <div class="head-left" >
                <span class="home-icon-wrapper">
                    <router-link to="/">
                        <Icon class="home-icon"  type="ios-home" size="35" color="#ffffff"/>
                    </router-link>
                </span>
            </div>
            <div class="head-center">
                Item
            </div>
            <div class="head-right">
                <a href="/listConfig.html" target="_blank">列表配置</a>
            </div>
            <span>
                <!--<Icon id="refreshIcon" size="30" color="red" type="ios-refresh-circle" style="float: right;margin-right: 10px;" v-on:click="refreshList" title="刷新页面"></Icon>-->
            </span>
        </div>
        <Home></Home>
    </div>
</template>
<script>
    import Home from "./components/Home.vue";

    export default {
        name: 'listTool',
        components: {
            Home
        }
    }
</script>

<style lang="scss">
    * {
        padding: 0;
        margin: 0;
    }
    #listTool {

        min-height: 100vh;
        display: flex;
        flex-direction: column;

        font-family: "Avenir", Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        color: #2c3e50;
        /*height:100%;*/
    }
    html,
    body,
    #listTool,
    .home {
        height: 100%;
    }
    html,
    body {
        overflow: auto;
    }

    .header {
        display : flex;
        flex-direction: row;
        height:60px;
        /*background-color: #515a6e;*/
        background-color:#404655;

        /*background-color:#7a8294;*/
        /*background-color:#2d8cf0;*/
        color : #ffffff;
        font-size: 15px;

    }

    .head-left {
        display : flex;
        /*background-color: #6EABF9;*/
        width: 180px;
        height: 100%;
        padding-left: 20px;
        align-items: center;     /* 垂直居中 */
    }

    .head-center {
        display : flex;
        width: calc(100% - 430px);;
        height: 100%;
        padding-left: 20px;
        align-items: center;     /* 垂直居中 */
    }

    .head-right {
        display : flex;
        /*background-color: #f95c8c;*/
        width: 250px;
        height: 100%;
        padding-left: 20px;
        align-items: center;     /* 垂直居中 */
    }

    .home-icon:hover {
        /*background-color: #c1c1c1;*/

        box-shadow: 3px 3px 3px #888888;

    }
</style>
