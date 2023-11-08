<template>
    <div class="alerts">
        <el-alert v-for="(item, index) in list"
                  :title="item.title"
                  :type="item.type"
                  :description="item.description || null"
                  :show-icon="item.showIcon"
                  :key="index"
        >
        </el-alert>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                name: 'Alerts'
            }
        },
        props: {
            list: {
                type: Array,
                default() {
                    return []
                }
            }
        }
    }
</script>

<style scoped lang="scss">
    .alerts .el-alert {
        margin-bottom: 15px;
    }
</style>