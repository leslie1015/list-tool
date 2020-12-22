<template>
    <!--<h3>-->
    <div>
        <list-content :resourceId="resourceId"
                      :resourceType="resourceType"
                      ref="listContent">
        </list-content>
    </div>

    <!--</h3>-->
</template>
<script>

    import listContent from '../contents/content.vue'

    export default {
        name: "TreeViewDetail",
        data() {
            return {
                isLoadList: false,
                currentRoute: this.$route.path,
                resourceId: '',
                resourceType: ''            };
        },
        created() {

        },
        mounted() {
            this.initList();
        },
        methods: {
            // 初始化列表
            initList() {
                if (this.$route.path != '' && this.$route.path != null && this.$route.path != undefined) {
                    this.resourceId = this.$route.name;
                    this.resourceType = 'link';


                }
            }

        },
        watch: {
            //监听路由，只要路由有变化(路径，参数等变化)都有执行下面的函数
            $route: {
                handler: function(val, oldVal) {
                    this.resourceId = val.name;
                    this.resourceType = 'link';
                    this.currentRoute = val.name;
                },
                deep: true
            }
        },
        components : {
            listContent
        }
    };
</script>
<style scoped>
    h3 {
        margin-top: 10px;
        font-weight: normal;
    }
</style>