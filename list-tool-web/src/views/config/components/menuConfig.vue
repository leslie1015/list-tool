<template>
    <div id="menuConfig">

        <el-row :gutter="10">
            <el-col :span="6">
                <div style="color: white">0</div>
            </el-col>
            <el-col :span="12">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="菜单ID" prop="queueId">
                        <el-input v-model="form.queueId"  @blur.prevent="getQueueInfo()" placeholder="新增则不填，修改则填入对应的菜单ID"></el-input>

                    </el-form-item>
                    <el-form-item label="菜单名称" prop="resourceName">
                        <el-input v-model="form.resourceName" placeholder="填写菜单名(必填)"></el-input>
                    </el-form-item>
                    <!--            <el-form-item label="所属应用">
                                    <el-select v-model="form.appType" placeholder="请选择所属应用">
                                        <el-option label="默认应用1" value="1"></el-option>
                                    </el-select>
                                </el-form-item>-->
                    <el-form-item label="父菜单" prop="parentQueueId">
                        <el-select v-model="form.parentQueueId" placeholder="请选择父菜单">
                            <el-option label="无" :value="0"></el-option>
                            <template v-for="item in this.parentMenus">
                                <el-option :label="item.resourceName" :value="item.queueId"></el-option>
                            </template>
                        </el-select>
                        <span>
                            <el-popover
                                placement="top-start"
                                title="Tips"
                                width="300"
                                trigger="hover"
                                content="不选父菜单，则默认添加顶级菜单项">
                                 <span slot="reference"><i style="font-size: 20px;margin-left: 10px" class="el-icon-question"></i></span>
                            </el-popover>
                        </span>
                    </el-form-item>
                    <el-form-item label="菜单类型" prop="resourceType">
                        <el-select v-model="form.resourceType" placeholder="请选择菜单类型(必选)">
                            <el-option label="菜单" value="button"></el-option>
                            <el-option label="资源" value="link"></el-option>
                        </el-select>
                        <span>
                            <el-popover
                                    placement="top-start"
                                    title="Tips"
                                    width="200"
                                    trigger="hover"
                                    content="如果是直接对应后台sql的id的菜单（叶子菜单），则选择“资源”，否则选择“菜单”">
                                 <span slot="reference"><i style="font-size: 20px;margin-left: 10px" class="el-icon-question"></i></span>
                            </el-popover>
                        </span>
                    </el-form-item>
                    <el-form-item label="资源ID" prop="resourceId">
                        <el-input v-model="form.resourceId" placeholder="如果菜单类型为“资源”，则填写对应后台sql的id，否则自定义（方便后续根据这个id做权限），或者留空"></el-input>
                    </el-form-item>
                    <el-form-item label="顺序" prop="orderNum">
                        <el-input v-model="form.orderNum" placeholder="相对展示顺序(非必填，默认为0-展示在最上面)"></el-input>
                    </el-form-item>

                    <el-form-item label="是否激活" prop="activeFlag">
                        <el-radio-group v-model="form.activeFlag">
                            <el-radio border :label="1">激活</el-radio>
                            <el-radio border :label="0">禁用</el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">提交</el-button>
                        <el-button @click="resetForm('form')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="6">
                <div style="color: white">0</div>
            </el-col>
        </el-row>


    </div>
</template>

<script>
    export default {
        name: "menuConfig",
        data() {
            return {
                form: {
                    queueId:'',
                    resourceName: '',
                    appType: 1,
                    parentQueueId: null,
                    resourceType: '',
                    resourceId: null,
                    orderNum: null,
                    activeFlag:1
                },
                parentMenus: []

            }
        },
        created () {
            this.initParentMenus();
        },
        methods: {
            getQueueInfo() {
                if (!!!this.form.queueId || !!!this.form.queueId.trim()) {
                    return false;
                }
                const url = this.csbRouter + '/listQueue/getByQueueId/' + this.form.queueId;
                this.httpGet(url).then((response) => {
                    if (!!response.data.value) {
                        this.form = response.data.value
                    } else {
                        this.resetForm("form");
                        alert("未查询到此队列");
                    }
                }).catch((response) => {
                    console.log(response)
                })
            },

            onSubmit() {
                let url = this.csbRouter + '/listQueue/saveOrUpdate';
                const data = this.form;
                this.httpPost(url, data)
                    .then(response => {
                        this.successNotice(response.data.value);
                        this.resetForm("form");
                    })
                    .catch(error => {
                        this.errorNotice(error.data.value);
                    });

            },
            initParentMenus () {
                const url = this.csbRouter + '/listQueue/listParentMenus/' + this.form.appType;
                this.httpGet(url).then((response) => {
                    this.parentMenus = response.data.value
                }).catch((response) => {
                    console.log(response)
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

        }
    }
</script>

<style scoped>

</style>