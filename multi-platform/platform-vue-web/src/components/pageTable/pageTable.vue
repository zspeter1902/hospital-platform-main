<template>
    <div>
        <el-table
            :data="dataList"
            size="mini"
            @selection-change="handleCheckChange"
            ref="multipleTable"
        >
            <el-table-column
                    type="selection"
                    width="55" v-if="needCheck">
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

        <!--<div style="margin-top: 20px">-->
            <!--<el-button @click="toggleSelection([dataList[1], dataList[2]])">切换第二、第三行的选中状态</el-button>-->
            <!--<el-button @click="toggleSelection()">取消选择</el-button>-->
        <!--</div>-->
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
                name: 'pageTable',
                multipleSelection: []
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
                this.multipleSelection = val;
                this.$emit('pageCheckChange', val)
            },
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },

        }
    }
</script>

<style scoped>

</style>
