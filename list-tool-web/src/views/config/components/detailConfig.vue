<template>
    <div id="detailConfig">
        <el-row :gutter="10" style="height: 100%">
            <el-col :span="6">
                <div style="border-bottom: 1px solid #DCDFE6">
                    <el-row :gutter="10" style="height: 100%">
                        <el-col :span="18">
                            <el-form ref="form" :model="searchForm" label-width="80px">
                                <el-form-item label="菜单ID" prop="resourceId">
                                    <el-input clearable v-model="searchForm.resourceId" placeholder="精确"></el-input>
                                </el-form-item>
                                <el-form-item label="菜单名" prop="resourceName">
                                    <el-input clearable v-model="searchForm.resourceName" placeholder="模糊"></el-input>
                                </el-form-item>
                            </el-form>
                        </el-col>
                        <el-col :span="6" style="margin-top: 71px">
                            <el-button type="primary" size="small" @click="listResourceMenus(1)">查询</el-button>
                        </el-col>
                    </el-row>
                </div>

                <el-table
                        :data="tableInfo.menuInfo"
                        stripe
                        style="width: 100%"
                        @row-click="changeResource"
                >
                    <el-table-column
                            prop="resourceId"
                            label="资源ID" width="100px">
                    </el-table-column>
                    <el-table-column
                            prop="resourceName"
                            label="菜单名" >
                    </el-table-column>
                    <el-table-column label="操作" width="200px" align="center">
                        <el-button type="info" plain size="small">配置</el-button>
                    </el-table-column>
                </el-table>
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :total="tableInfo.total"
                        :page-size="tableInfo.pageSize"
                        @current-change="listResourceMenus"
                >
                </el-pagination>
            </el-col>
            <el-col :span="18">
                <ColInfo :resource-id="selectResourceId" :resource-name="this.selectResourceName"></ColInfo>
            </el-col>
        </el-row>

    </div>
</template>

<script>

    import ColInfo from './colInfo'

    export default {
        name: "detailConfig",
        data() {
            return {
                tableInfo: {
                    menuInfo: [],
                    total:null,
                    pageSize: 5
                },
                searchForm: {
                    resourceId:null,
                    resourceName:"",
                    appType: 1,
                    resourceType: "link"
                },
                selectResourceId: null,
                selectResourceName: ""
            }
        },
        created() {
            this.listResourceMenus(1);
        },
        methods: {
            listResourceMenus(pageNo) {
                let url = this.csbRouter + '/listQueue/listByPage';
                const data = {
                    pageNum: pageNo,
                    pageSize: this.tableInfo.pageSize,
                    filters: this.searchForm
                }
                this.httpPost(url, data)
                    .then(response => {
                        this.tableInfo.total = response.data.value.total;
                        this.tableInfo.menuInfo = response.data.value.list
                    })
                    .catch(error => {
                        this.errorNotice(error.data.value);
                    });
            },
            changeResource(row) {
                this.selectResourceId = row.resourceId;
                this.selectResourceName = row.resourceName;
            }
        },
        components: {
            ColInfo
        }
    }
</script>

<style scoped>
#detailConfig {
 height: 100%;
}
</style>