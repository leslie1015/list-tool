<template>
    <div id="colInfo">
        <div style="margin-bottom: 5px">
            <el-row :gutter="1" style="background-color: #4c5050">
                <el-col :span="4" style="font-weight: bold;color: white"> 资源ID： {{this.currResourceId == null ? "请选择资源" : this.currResourceId}} </el-col>
                <el-col :span="8" style="justify-content: flex-start;font-weight: bold;color: white"> 菜单名：{{this.resourceName}} </el-col>
                <el-col :span="12" style="justify-content: flex-end;padding-right: 30px">
                    <el-button type="primary" @click="submitColFields()">提  交</el-button>
                    <el-button type="primary" @click="downloadSql()">导出配置</el-button>
                </el-col>
            </el-row>
        </div>
        <div>
            <el-row :gutter="1" class="field-table-header">
                <el-col :span="1"> 顺序 </el-col>
                <el-col :span="3"> 字段名 </el-col>
                <el-col :span="3"> 显示名 </el-col>
                <el-col :span="2"> 是否隐藏列 </el-col>
                <el-col :span="2"> 是否主键 </el-col>
                <el-col :span="3"> 参数key </el-col>
                <el-col :span="3"> 查询方式 </el-col>
                <el-col :span="2"> 是否排序 </el-col>
                <el-col :span="2"> 是否时间字段 </el-col>
                <el-col :span="3"> 时间格式 </el-col>
            </el-row>

            <draggable
                    :list="colSubmitForm"
                    :disabled="!enabled"
                    class="list-group"
                    ghost-class="ghost"
                    :move="checkMove"
                    @start="dragging = true"
                    @end="dragging = false"
            >
                <div v-for="(item,key) in colSubmitForm" :key="key" class="dragging-item">
                    <el-row :gutter="1">
                        <el-col :span="1" style="font-weight: bold">
                            {{key}}
                        </el-col>
                        <el-col :span="3">
                            <el-input readonly v-model="colSubmitForm[key].fieldName"/>
                        </el-col>
                        <el-col :span="3">
                            <el-input v-model="colSubmitForm[key].showName" />
                        </el-col>
                        <el-col :span="2">
                            <el-select v-model="colSubmitForm[key].hideFlag" size="middle">
                                <el-option label="否" :value="0"></el-option>
                                <el-option label="是" :value="1"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="2">
<!--                            <el-select v-model="form.resourceType" >-->
                            <el-select v-model="colSubmitForm[key].primarykeyFlag" size="middle">
                                <el-option label="否" :value="0"></el-option>
                                <el-option label="是" :value="1"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="3">
                            <el-input v-model="colSubmitForm[key].paramGroupKey"/>
                        </el-col>
                        <el-col :span="3">
                            <el-select v-model="colSubmitForm[key].searchType" size="middle">
                                <el-option label="不搜索" :value="0"></el-option>
                                <el-option label="模糊搜索" :value="1"></el-option>
                                <el-option label="精确搜索" :value="2"></el-option>
                                <el-option label="下拉搜索" :value="3"></el-option>
                                <el-option label="时间区间" :value="4"></el-option>
                                <el-option label="数字区间" :value="5"></el-option>
                                <el-option label="下拉多选" :value="6"></el-option>

                            </el-select>
                        </el-col>
                        <el-col :span="2">
                            <el-select v-model="colSubmitForm[key].sortType" size="middle">
                                <el-option label="否" :value="0"></el-option>
                                <el-option label="是" :value="1"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="2">
                            <el-select  v-model="colSubmitForm[key].isDate" size="middle">
                                <el-option label="否" :value="0"></el-option>
                                <el-option label="是" :value="1"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="3">
                            <el-select v-model="colSubmitForm[key].dateFormatType" size="middle">
                                <el-option label="yyyy-mm-dd" :value="1"></el-option>
                                <el-option label="yyyy-mm-dd hh:mi:ss" :value="2"></el-option>
                            </el-select>
                        </el-col>
                    </el-row>
                </div>
            </draggable >

            <!--{{colNameList}}-->
        </div>
    </div>
</template>

<script>
    import draggable  from 'vuedraggable'

    let id = 1;

    export default {
        name: "colInfo",
        props: {
            resourceId: {
                default: null,
                type: Number
            },
            resourceName: {
                default: "",
                type: String
            }
        },
        data() {
            return {
                enabled: true,
                dragging: false,

                currResourceId: null,
                colNameList:[],
                colFieldList: [],
                colSubmitForm:[]
            }
        },
        computed: {
            draggingInfo() {
                return this.dragging ? "under drag" : "";
            }
        },
        methods: {
            downloadSql() {
                if (!!!this.currResourceId) {
                    this.warningNotice("请选择菜单");
                    return false;
                }
                this.warningNotice("功能开发中...");
            },
            checkMove: function(e) {
                window.console.log("Future index: " + e.draggedContext.futureIndex);
            },
            submitColFields() {
                const url = this.csbRouter + "/listResource/batchInsert";

                const data = {
                    resourceId:this.currResourceId,
                    resourceFieldList: this.colSubmitForm
                }

                if (!!!data.resourceId || !!!data.resourceFieldList || data.resourceFieldList.length <= 0) {
                    this.warningNotice("请选择要配置的菜单");
                    return false;
                }

                // 提交前根据页面实际排序结果将indexNo重新赋值提交到后台
                for (let i = 0; i < data.resourceFieldList.length; i++) {
                    data.resourceFieldList[i].indexNo = i;
                }
                this.httpPost(url, data)
                    .then(response => {
                        this.successNotice("操作成功");
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            initColumns() {
                if (!!!this.currResourceId) {
                    return false;
                }
                this.colNameList = [];
                this.httpGet(this.csbRouter + '/columns/getColumns/' + this.currResourceId).then((response) => {
                    this.colNameList = response.data.value;
                    this.initFields(this.currResourceId);

                }).catch((response) => {
                    console.log(response);
                })
            },

            // 获取已配置字段信息
            initFields(resourceId) {
                this.colFieldList = [];
                this.httpGet(this.csbRouter + '/listResource/listField/' + resourceId).then((response) => {
                    this.colFieldList = response.data.value;
                    // this.colSubmitForm = this.colFieldList;
                    this.initColSubmitForm(this.colNameList);
                }).catch((response) => {
                    console.log(response);
                })
            },
            //getJson(getItem(colFieldList, 'fieldName', item), 'showName')

            // 将数据库已有的值带入到表单中，直接赋值页面会报找不到字段属性，所以这里手动赋值
            // 遍历sql中所有查询出的字段
            initColSubmitForm(colNameList) {
                this.colSubmitForm = [];
                for (var i = 0 ;i < colNameList.length; i++) {
                    const obj = new Object();
                    obj.fieldName = colNameList[i];
                    obj.orderNo = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'indexNo');
                    obj.showName = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'showName');
                    obj.hideFlag = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'hideFlag');
                    obj.primarykeyFlag = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'primarykeyFlag');
                    obj.paramGroupKey = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'paramGroupKey');
                    obj.searchType = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'searchType');
                    obj.sortType = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'sortType');
                    obj.isDate = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'isDate');
                    obj.dateFormatType = this.getJson(this.getItem(this.colFieldList, 'fieldName', obj.fieldName), 'dateFormatType');
                    obj.indexNo = null;
                    this.colSubmitForm.push(obj);
                }

                const actionObj = new Object();
                actionObj.fieldName = "ACTION";
                actionObj.orderNo = colNameList.length;
                actionObj.showName = "操作";
                const hideFlag = this.getJson(this.getItem(this.colFieldList, 'fieldName', actionObj.fieldName), 'hideFlag');
                actionObj.hideFlag = !!hideFlag ? hideFlag : 0;
                actionObj.primarykeyFlag = 0;
                actionObj.paramGroupKey = null;
                actionObj.searchType = 0;
                actionObj.sortType = 0;
                actionObj.isDate = 0;
                actionObj.dateFormatType = null;
                actionObj.indexNo = null;
                this.colSubmitForm.push(actionObj);

                this.colSubmitForm.sort(this.up);
            },

            // json排序
            up(x,y){
                return x.orderNo-y.orderNo
            },

            // 查询json数组
            getItem(arr,name,val) {
                for (var i = 0; i < arr.length; i++)
                    if (arr[i][name]==val)
                        return arr[i];
            },
            // 根据key取对象的value
            getJson(jsonObj,key) {
                if (!!!jsonObj) {
                    return "";
                }
                return jsonObj['' + key + ''];
            }


    },

        watch: {
            resourceId: function (newVal, oldVal) {
                this.currResourceId = newVal;
                this.initColumns();
            }
        },
        components: {
            draggable
        }
    }
</script>

<style scoped>

    #colInfo {
        min-height: 800px;
        /*background-color: whitesmoke;*/
        border-left: 1px solid #DCDFE6;
    }

    #colInfo .el-row {

        height: 45px;
    }

    #colInfo .el-row .el-col {
        height:100%;
        color: #606266;
        font-size: 14px;
        display: flex;
        justify-content: center;
        align-items:center;
    }

    #colInfo .field-table-header {
        background-color: whitesmoke;
        border-bottom: 1px solid #313030;
    }

    #colInfo .field-table-header div {
        font-weight: bold;
        border-left: 1px solid rgb(228, 229, 230);
    }

    .dragging-item {
        cursor: move;
        margin-bottom: 2px;
        border-bottom: 1px solid #DCDFE6;
        border-top: 1px solid #DCDFE6;
        background-color: whitesmoke;
        border-radius: 2px;

        box-shadow:0px 10px 8px -12px #000; border-radius:10px;
    }

</style>