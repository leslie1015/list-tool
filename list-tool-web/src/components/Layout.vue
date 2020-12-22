<template>
    <el-container class="layout-container">
        <el-header style="height: 3rem">
            <slot name="header"></slot>
        </el-header>
        <el-container>
            <el-aside class="container-aside" width="20%">
                <slot name="ad-left"></slot>
            </el-aside>
            <el-main>
                <slot name="main"></slot>
            </el-main>
            <el-aside class="container-aside" width="20%">
                <slot name="ad-right"></slot>
            </el-aside>
        </el-container>
        <el-footer>

        </el-footer>
    </el-container>
</template>

<script>
    export default {
        name: "Layout"
    }
</script>

<style scoped>

    @media (max-width: 1000px) {
        .container-aside {
            display: none !important;
        }
    }

    .container-aside {
        background-color: #efefef;
    }

    .el-header {
        padding: 0 0 !important;
    }
</style>