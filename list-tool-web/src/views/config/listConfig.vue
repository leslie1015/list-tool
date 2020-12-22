<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div id="listConfig">
        <div>
            <el-button @click="drawerConfig.drawer = true" size="small" type="info" style="margin-right: 36px; float: right">
                配置说明
            </el-button>
            <el-drawer
                    title="菜单"
                    :visible.sync="drawerConfig.drawer"
                    :direction="drawerConfig.direction"
                    :with-header="false"
                    :custom-class="drawerConfig.drawerClass"
                    :size="drawerConfig.size"
                    :show-close="false"
                    :modal="true"
                    ref="drawer"
                    style="width: 100%;">
                <div class="config-advice">
                    <span class="config-advice-item">
                        <h3>步骤一</h3>
                        后台代码“/resource/sqlmap/”目录下，新建对应业务文件夹（分类更清晰），在文件夹内新建的或者已有的xml文件中，
                        写好对应的业务SQL脚本，注意sql的id，id范围最好根据业务提前规划好，如用户相关的列表，id从300000开始往后累加，避免后续管理混乱。如下图：
                        <el-image :src="require('../../img/config_advice/sql.png')"  style="width: 100%; height: auto"></el-image>
                    </span>
                    <span class="config-advice-item">
                        <h3>步骤二</h3>
                        进入/listConfig.html页面-菜单配置，菜单ID不填，填写列表需要显示的菜单名称，选择对应父菜单，
                        菜单类型选择资源，资源ID为对应的sql的id，如上图Demo为300001,填写后提交
                    </span>
                    <span class="config-advice-item">
                        <h3>步骤三</h3>
                        点击“列表配置”按钮，左侧为已配置好菜单的所有资源性列表，输入300001，点击查询，找到刚才配置的菜单信息，点击配置，右侧为300001对应sql的列信息。
                        <ul>
                            <li>字段名：即后台sql查询的列名，不可编辑</li>
                            <li>显示名为：在列表中的列名，可以配置列是否隐藏显示（display:none）</li>
                            <li>是否主键：用于行操作按钮点击时获取关键数据</li>
                            <li>参数key：对应后台数据字典表的key，用于将库中存储的1、2、3...等状态类的数据替换为对应的字典值，配置好字典表后，此处只需要配置字典的key即可</li>
                            <li>查询方式：配置列的查询方式，支持七种查询</li>
                            <li>是否排序：选择是，列head上出现排序按钮</li>
                            <li>是否时间字段：前端判断字段是否需要格式化</li>
                            <li>时间格式：如果选择是，可以配置列中时间展示格式</li>
                        </ul>
                        可以拖动行来排序，列表字段展示的时候排序以此为据，字段名为“ACTION”的为默认行，默认在最后，如果列表仅查看，不需要操作按钮，可以选择隐藏此列。
                        此行配置后，还需要进行操作按钮配置，功能暂未提供，目前可以直接配置后台表来实现。
                    </span>
                    <span class="config-advice-item">
                        <h3>步骤四</h3>
                        在开发环境操完上述步骤后，点击“列表配置”，选择左边对应的资源，点击右上角提交按钮旁的“导出配置”按钮，导出所有该列表配置的sql脚本，到测试环境执行，测试完毕后，到生产环境执行
                    </span>
                </div>
            </el-drawer>
        </div>
        <div class="config-wrapper">
            <el-tabs v-model="activeTab" type="card" @tab-click="handleClick">
                <el-tab-pane label="菜单配置" name="menuConfig">
                    <keep-alive>
                        <MenuConfig v-if="activeTab=='menuConfig'"></MenuConfig>
                    </keep-alive>
                </el-tab-pane>
                <el-tab-pane label="列表配置" name="listConfig">
                    <keep-alive>
                        <DetailConfig v-if="activeTab=='listConfig'"></DetailConfig>
                    </keep-alive>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
    import DetailConfig from './components/detailConfig'
    import MenuConfig from './components/menuConfig'

    export default {
        name: "listConfig",
        data () {
            return {
                drawerConfig: {
                    trigger: "click",
                    direction : "ltr",
                    drawer: false,
                    drawerClass: "menu-drawer",
                    size: "40%",
                },
                activeTab:'menuConfig',
                activeName: "listConfig"
            };
        },
        created () {

        },
        methods: {
            getArticleInfo () {
                const url = this.silkRouter + '/article/getArticleInfo?articleId=' + this.getQueryString('articleId')
                this.httpGet(url).then((response) => {
                    this.articleInfo = response.data.value
                }).catch((response) => {
                    console.log(response)
                })
            },
            handleClick(tab, event) {
                // this.$router.push({path: this.activeName})
            }

        },
        components: {
            DetailConfig, MenuConfig
        }
    }
</script>

<style lang="stylus">

    body {
        height : 100%
    }

    #listConfig {
        display : flex;
        flex-direction: column;
        height : 100%
    }

    .el-tab-pane {
        height: 100%
    }

    .el-tabs__content {
        height: 100%
    }

    .config-wrapper {
        height: 100%
    }
    .config-advice {
        display :flex;
        flex-direction :column;
        padding:10px;
        overflow-y:auto;
        max-height :850px;
    }

    .config-advice span{
        color: #656363;
    }


</style>
