<template>
    <el-row :gutter="20" class="head-top">
        <el-col :span="6" class="head-container-left">
            <!--<div @click="backPage">back</div>-->
            <a href="/index.html">首页</a>
        </el-col>
        <el-col :span="12" class="head-container-center">
            <el-input clearable maxlength="10" @keyup.enter.native="searchArticle" placeholder="快来搜索吧！" v-model="searchText" class="input-with-select">
                <el-button slot="append" icon="el-icon-search" @click="searchArticle"></el-button>
            </el-input>
        </el-col>
        <el-col :span="6" class="head-container-right">
            <a href="/write.html?action=add">
                <i style="font-weight: bold; font-size: 1.5rem;color: white" class="el-icon-edit"></i>
            </a>
            <a href="/person.html">
                <i style="font-weight: bold; font-size: 1.5rem;color: white" class="el-icon-user-solid"></i>
            </a>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "HeadTop",
        data() {
            return {
                searchText:decodeURI(this.getQueryString('searchText')),
                loginUserName: ''
            };
        },
        created() {
            this.initLoginUserInfo()
        },
        methods: {
            searchArticle() {
                if (!this.searchText) {
                    alert("请输入搜索内容")
                    return false;
                }
                location.href = '/articleList.html?searchText=' + encodeURI(encodeURI(this.searchText));
            },
            initLoginUserInfo() {
                this.loginUserName = this.getLoginUser();
            },
            backPage() {
                window.history.go(-1);
            }
        }
    }
</script>

<style lang="stylus">
    .head-top {
        display flex
        flex-direction row
        justify-content space-around
        align-items: center;     /* 垂直居中 */
        background-color: #545c64
        text-color: #fff
        height: 100%
    }

    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

    .head-container-left {
        display: flex;
        justify-content: flex-start;
        align-items: center;     /* 垂直居中 */
        margin-left: 1rem;
        color:white;
    }
    .head-container-left a {
        color: white;
        text-decoration:none;
        font-weight:bold;
    }
    .head-container-center {
        display: flex;
        justify-content: center; /* 水平居中 */
        align-items: center;     /* 垂直居中 */
    }
    .head-container-right {
        display: flex;
        justify-content: flex-end;
        align-items: center;     /* 垂直居中 */
        margin-right: 1rem;
    }
    .head-container-right a{
        margin-right:1rem;
    }

</style>