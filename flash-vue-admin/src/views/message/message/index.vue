<template>
    <div class="app-container">
        <div class="block">
            <el-row>
                <el-col :span="24">
                    <el-button type="danger" icon="el-icon-delete" @click.native="clear">{{ $t('button.clear') }}</el-button>
                </el-col>
            </el-row>
        </div>


        <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
                  @current-change="handleCurrentChange">
            <el-table-column label="模板编码">
                <template slot-scope="scope">
                    {{scope.row.tplCode}}
                </template>
            </el-table-column>
            <el-table-column label="消息内容">
                <template slot-scope="scope">
                    {{scope.row.content}}
                </template>
            </el-table-column>
            <el-table-column label="接收者">
                <template slot-scope="scope">
                    {{scope.row.receiver}}
                </template>
            </el-table-column>
            <el-table-column label="消息类型">
                <template slot-scope="scope">
                    {{scope.row.type==0?"短信":"邮件"}}
                </template>
            </el-table-column>
            <el-table-column label="状态">
              <template slot-scope="scope">
                {{scope.row.state==1?"成功":"失败"}}
              </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[10, 20, 50, 100,500]"
                :page-size="listQuery.limit"
                :total="total"
                @size-change="changeSize"
                @current-change="fetchPage"
                @prev-click="fetchPrev"
                @next-click="fetchNext">
        </el-pagination>

    </div>
</template>

<script src="./t_message.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
    @import "src/styles/common.scss";
</style>

