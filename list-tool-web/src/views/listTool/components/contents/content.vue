<template>
    <div class="container" style="height:100%">
        <div class="search-wrapper">
            <search-info :fieldInfo="fieldInfo"
                         @freshTableDataByCondition="freshTableDataByCondition"
                         ref="searchInfo"></search-info>
        </div>
        <div class="list-wrapper" ref="list_wrapper">
            <list-table :tableHeight="tableHeight" :tableData="tableData" :columns="columns" :isLoading="isLoading"
                        @freshTableDataByCondition="freshTableDataByCondition"></list-table>
        </div>
        <div class="page-wrapper">
            <Page style="float: right"
                  :total="pageTotal"
                  :page-size="pageSize"
                  :current="this.currentPageNo"
                  show-total
                  show-sizer
                  show-elevator
                  @on-change="changeCurrentPage"
                  @on-page-size-change="changePageSize"></Page>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import listTable from './listtable.vue'
    import searchInfo from './search-info.vue'
    import axios from 'axios'
    import VueAxios from 'vue-axios'

    import {formatDate} from "../filter/dateFilter";
    Vue.use(VueAxios, axios)

    export default {
        name: "list-content",
        data() {
            return {
                listData: [],
                tableData: [],
                columns: [],
                fieldInfo: [],
                pageTotal: 0,
                pageSize: 10,
                currentPageNo: 1,
                searchParam: [],
                sortField: '',
                sortType: '',
                isLoading: false,
                tableHeight : 0
            }
        },
        components: {
            listTable,
            searchInfo
        },
        props: {
            resourceType: String,
            resourceId: String
        },
        mounted () {
            this.initTableHeight();
        },
        watch: {
            resourceId: function (newVal, oldVal) {

                this.refreshListPage(newVal);
            }
        },
        methods: {
            // 初始化表格高度
            initTableHeight() {
                var height= this.$refs.list_wrapper.offsetHeight;
                this.tableHeight = height-50;
            },
            refreshListPage(resourceId) {
                // 影响页面查询结果的数据全部置为初始化状态
                this.pageSize = 10;
                this.currentPageNo = 1;
                this.searchParam = [];
                this.sortField = '';
                this.sortType = '';
                this.$refs.searchInfo.clearSearchInfo();
                this.getResourceList(resourceId);
            },
            getResourceList(resourceId) {

                // 如果资源类型为列表链接
                if (this.resourceType == 'link') {

                    var primaryKey = '';
                    var srcPath = this.csbRouter;
                    var routerPath = this.csbRouter;
                    var sortField = this.sortField;

                    this.isLoading = true;
                    // axios不能直接用在render模板函数中，可能是this指向不明，转换一下再引用，解决。。
                    var renderAxiosGet = this.httpGet;
                    var renderAxiosPost = this.httpPost;
                    // 先加载表头信息，加载完再加载数据，如果都是异步不相关，可能先加载完列表数据，没有表头数据导致渲染失败
                    this.httpGet(this.csbRouter + '/listResource/listField/' + resourceId, null, false).then((response) => {
                        this.fieldInfo = response.data.value;
                        var arr = new Array();
                        // 第一列显示序号
                        arr[0] = {
                            key: 'index',
                            title: '序号',
                            align: 'center',
                            fixed: 'left',
                            width:70,
                            // fixed: 'left',
                            render: (h, params) => {
                                return h('div', (this.pageSize * (this.currentPageNo - 1)) + params.index + 1)
                            }
                        };
                        this.fieldInfo.forEach(function (item, index) {

                            //字段类型不是按钮，找出主键
                            if (item.primarykeyFlag === 1) {
                                primaryKey = item.fieldName;
                            }
                            // 字段类型为操作按钮
                            if (item.fieldType === 2) {
                                // 根据按钮数量确定操作列的具体宽度
                                let width = 0;
                                let marinCount = '0px';// 如果只有一个按钮，就不需要margin控制间距
                                if (item.actions.length === 1) {
                                    width = 80;
                                } else {
                                    width = item.actions.length * 56;
                                    marinCount = '3px';
                                }

                                arr[index+1] = {
                                    key: item.fieldName,
                                    title: item.showName,
                                    align: 'center',
                                    fixed:'right',// 右侧固定
                                    width: width,
                                    // 操作按钮模板
                                    render: (h, params) => {
                                        return h('div',{
                                                style: {
                                                    display:'flex',
                                                    flexDirection: 'row',
                                                    justifyContent:'center'
                                                }
                                            },
                                            item.actions.map(function (action) {
                                                return h(action.buttonTag, {
                                                    props: {
                                                        type: action.buttonStyle,
                                                        size: 'small',
                                                    },
                                                    style: {

                                                        marginRight:marinCount
                                                    },
                                                    on: {
                                                        click: () => {
                                                            //get请求
                                                            if (action.requestType === 1) {
                                                                if (primaryKey === '' || primaryKey === undefined) {
                                                                    alert("主键未定义");
                                                                }
                                                                // window.open(routerPath + action.clickFunction + params.row[primaryKey]);
                                                                // window.open(action.clickFunction + params.row[primaryKey]);
                                                                let target = action.clickFunction;
                                                                if (target.indexOf('[pk]') > -1) {
                                                                    target = target.replace('[pk]',params.row[primaryKey]);
                                                                }
                                                                window.open(target)

                                                            }
                                                            else if (action.requestType === 2) {
                                                                if (primaryKey === '' || primaryKey === undefined) {
                                                                    alert("主键未定义");
                                                                }

                                                                renderAxiosGet(srcPath + action.targetFunction + params.row[primaryKey]).then((response) => {
                                                                    alert("操作成功");
                                                                }).catch((response) => {
                                                                    alert("操作失败");
                                                                })
                                                            }//post请求
                                                            else if (action.requestType === 3) {
                                                                //TODO 构造请求参数
                                                                // var param = new URLSearchParams();
                                                                // param.append('resourceId', this.resourceId);
                                                                // param.append('paramsJson', JSON.stringify(searchParam));
                                                                // param.append('pageSize', pageSize);
                                                                // param.append('currentPageNo', currentPageNo);
                                                                renderAxiosPost(srcPath + action.targetFunction, params.row).then((response) => {
                                                                    alert("操作失败");
                                                                }).catch((response) => {
                                                                    alert("操作失败");
                                                                })
                                                            }

                                                        }
                                                    }
                                                }, action.showName)
                                            })
                                        )
                                    }
                                };
                            } else {
                                arr[index+1] = {
                                    key: item.fieldName,
                                    title: item.showName,
                                    minWidth : 100,
                                    align: 'center'
                                };

                                // 是否隐藏列
                                //由于iview表格的限制，如果直接将控制的hide-column列用display none隐藏，表格显示会出现右侧空缺，
                                //此处隐藏将其宽度设为1（设为0无效），达到视觉上隐藏的效果，表格内部数据再用display none隐藏掉
                                if (item.hideFlag === 1) {
                                    arr[index+1].className = 'hide-column';
                                    arr[index+1].minWidth = 1;
                                    arr[index+1].width = 1;
                                }

                                // 字段需要排序
                                if (item.sortType === 1) {
                                    arr[index+1].sortable = 'custom';
                                }
                                // 排序类型
                                if (sortField === item.fieldName) {
                                    arr[index+1].sortType = this.sortType;
                                }
                                // 如果是时间字段，根据配置格式化处理
                                if (item.isDate === 1) {
                                    var formatType = item.dateFormatType === '1' ? 'yyyy-MM-dd' : 'yyyy-MM-dd hh:mm:ss'
                                    // TODO vue时间格式化问题
                                    arr[index+1].render = function (h, params) {
                                        return h('div', formatDate(params.row[item.fieldName], formatType));
                                    }
                                }
                            }

                        });
                        this.columns = arr;
                        this.httpGet(this.csbRouter + '/listTool/list/' + resourceId, null, false).then((response) => {
                            this.tableData = this.jsonKeyToUpper(response.data.value.resultList);
                            this.pageTotal = response.data.value.total;
                            this.isLoading = false;
                        }).catch((response) => {
                            console.log('获取列表数据失败');
                        })

                    }).catch((response) => {
                        alert("获取表头数据失败");
                    })
                }
            },
            // 将json的key转为大写，与后端数据库的配置对应，避免大小写转换问题
            jsonKeyToUpper(jsonObj) {
                var newArr = new Array();
                for(var i = 0;i < jsonObj.length; i++) {
                    var oldObj = jsonObj[i];
                    var newObj = new Object();
                    Object.keys(oldObj).forEach(function(key){
                        newObj[key.toUpperCase()] = oldObj[key];
                    });
                    newArr[i] = newObj;
                }
                return newArr;
            },

            // 根据条件刷新列表数据(翻页、切换每页条数、查询)
            freshTableDataByCondition(searchParam, pageSize, currentPageNo, sortField, sortType) {
                this.isLoading = true;

                if (sortType === 'normal') {
                    sortField = '';
                    sortType = '';
                }

                if (searchParam === null) {
                    searchParam = this.searchParam;
                }

                if (pageSize === 0 || pageSize === undefined) {
                    pageSize = this.pageSize;
                }
                if (currentPageNo === 0 || currentPageNo === undefined) {
                    currentPageNo = this.currentPageNo;
                }

                // this.searchParam = searchParam;
                // //构造post请求参数
                // var param = new URLSearchParams();
                // param.append('resourceId', this.resourceId);
                // param.append('paramsJson', JSON.stringify(searchParam));
                // param.append('pageSize', pageSize);
                // param.append('currentPageNo', currentPageNo);
                //
                // param.append('sortField', sortField);
                // param.append('sortType', sortType);


                const data= {
                    resourceId: this.resourceId,
                    paramsJson: JSON.stringify(searchParam),
                    pageSize: pageSize,
                    currentPageNo: currentPageNo,
                    sortField: sortField,
                    sortType: sortType
                }

                // 根据操作更新页面分页相关显示
                this.currentPageNo = currentPageNo;

                this.sortField = sortField;
                this.sortType = sortType;
                this.pageSize = pageSize;
                this.httpPost(this.csbRouter + '/listTool/search', data, false).then((response) => {
                    this.tableData = this.jsonKeyToUpper(response.data.value.resultList);
                    this.pageTotal = response.data.value.total;
                    // 数据加载成功后取消加载动画
                    this.isLoading = false;
                }).catch((response) => {
                    alert("加载列表数据失败");
                })
            },
            // 翻页时，pageSize不变，查询参数不变，排序字段与排序方式不变
            changeCurrentPage: function (event) {
                this.freshTableDataByCondition(this.searchParam, this.pageSize, event, this.sortField, this.sortType);
            },
            // 修改每页显示数量时，默认跳到第一页，查询参数不变，排序字段与排序方式不变
            changePageSize: function (event) {
                this.freshTableDataByCondition(this.searchParam, event, 1, this.sortField, this.sortType);
            }
        }
    }
</script>

<style>
    .container {
        min-height: 95vh;
        display: flex;
        flex-direction: column;
        /*background-color: indianred;*/

    }

    .search-wrapper {
        flex:0.05;
        display: flex;

        justify-content: center;
        border-bottom: 1px solid #eee;
    }

    .list-wrapper {
        flex:0.95;
    }

    .page-wrapper {
        display: flex;
        height: 50px;
        margin-left: auto; /*靠右*/
        border-top: 1px solid #eee;
        margin-bottom: 20px;
    }


    .hide-column div{
        display:none !important;
    }

</style>