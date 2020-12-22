<template>
    <div id="list-table">
        <Table :height="tableHeight" stripe @on-sort-change="sort"  :border="true" :columns="columns" :data="tableData" :loading="isLoading"></Table>
    </div>
</template>

<script>

    export default {
        name: "list-table",
        props : {
            columns: Array,
            tableData: Array,
            isLoading : Boolean,
            tableHeight: Number
        },
        data () {
            return {

            }
        },
        mounted() {
            console.log(process.env.BASE_URL + 'admin');
        },
        methods: {
            sort:function (sortInfo) {
                this.$emit("freshTableDataByCondition", null, 0, 1, sortInfo.key, sortInfo.order);
            }
        }
    }
</script>

<style scoped>

    #list-table {
        height: 100%;
        overflow: auto;
    }

</style>