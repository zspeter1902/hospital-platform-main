<template>
    <div>
        <el-table
            :data="dataList"
            border
            @selection-change="handleCheckChange"
        >
            <el-table-column
                    label="序号"
                    type="index"
                    width="64" v-if="needCheck">
            </el-table-column>
            <el-table-column
                v-for="item in columns"
                :key="item.id"
                :label="item.label"
                :prop="item.prop"
                :align="align"
                :class-name="item.className"
                :label-class-name="item.labelClassName"
                :min-width="item.width"
                :fixed="item.fix || null"
            >
                <template slot-scope="scope">
                    <slot name="cell" :row="scope.row" :column="scope.column" :index="scope.$index"></slot>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 15px;" v-if="needPagination"
                       @size-change="handleSizeChange"
                       @current-change="handlePageNoChange"
                       :current-page="pageNo"
                       :page-sizes="pageSizes"
                       :page-size="pageSize"
                       :layout="layout"
                       :total="total"
        >
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: '',
        components: {
        },
        props: {
            dataList: {
                type: Array,
                default() {
                    return [];
                }
            },
            needPagination: {
                type: Boolean,
                default: true
            },
            needCheck: {
                type: Boolean,
                default: true
            },
            columns: {
                type: Array,
                required: true
            },
            pageNo: {
                type: Number,
                default: 1
            },
            pageSize: {
                type: Number,
                default: 20
            },
            pageSizes: {
                type: Array,
                default() {
                    return [10, 20, 50]
                }
            },
            total: {
                type: Number,
                required: true
            },
            layout: {
                type: String,
//        default: 'total, sizes, prev, pager, next, jumper'
                default: 'total, prev, pager, next, jumper'
            },
            align: {
                type: String,
                default() {
                    return 'center'
                }
            }
        },
        data() {
            return {
                name: 'pageTable'
            }
        },
        methods: {
            handleSizeChange(val) {
                this.$emit('pageSizeChange', val)
            },
            handlePageNoChange(val) {
                this.$emit('pageNoChange', val)
            },
            handleCheckChange(val) {
                this.$emit('pageCheckChange', val)
            }
        }
    }
</script>

<style scoped>

</style>
