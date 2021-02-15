<template>
    <div class="app-container">
        <div class="block">
          <el-row  :gutter="20">

            <el-col :span="4">
                <el-select    size="mini" v-model="listQuery.idMessageSender" filterable placeholder="请选择发送器">
                  <el-option
                    v-for="item in sendList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  >
                  </el-option>
                </el-select>
            </el-col>
            <el-col :span="4">
              <el-input v-model="listQuery.title" size="mini" placeholder="请输入标题"></el-input>
            </el-col>
            <el-col :span="6">
              <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
              <el-button type="primary" size="mini" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
            </el-col>
          </el-row>
          <br>
            <el-row>
                <el-col :span="24">
                    <el-button type="success" size="mini" icon="el-icon-plus" @click.native="add" v-permission="['/template/edit']">{{ $t('button.add') }}</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="edit" v-permission="['/template/edit']">{{ $t('button.edit') }}</el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="remove" v-permission="['/template/remove']">{{ $t('button.delete') }}</el-button>
                </el-col>
            </el-row>
        </div>


        <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
                  @current-change="handleCurrentChange">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="flash-table-expand">
                <el-form-item label="编号">
                  <span>{{ props.row.code }}</span>
                </el-form-item>
                <el-form-item label="标题">
                  <span>{{ props.row.title }}</span>
                </el-form-item>
                <el-form-item label="发送器">
                  <span>{{ props.row.messageSender.name }}</span>
                </el-form-item>
                <el-form-item label="内容">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
                <el-form-item label="消息类型">
                  <span>{{ props.row.type==0?'短信':'邮件' }}</span>
                </el-form-item>
                <el-form-item label="远程模板编号">
                  <span>{{ props.row.remoteTplCode }}</span>
                </el-form-item>


              </el-form>
            </template>
          </el-table-column>


          <el-table-column label="编号">
                <template slot-scope="scope">
                    {{scope.row.code}}
                </template>
            </el-table-column>
            <el-table-column label="标题">
                <template slot-scope="scope">
                    {{scope.row.title}}
                </template>
            </el-table-column>

            <el-table-column label="消息类型">
                <template slot-scope="scope">
                    {{scope.row.type==0?'短信':'邮件'}}
                </template>
            </el-table-column>
            <el-table-column label="发送器">
                <template slot-scope="scope">
                    {{scope.row.messageSender.name}}
                </template>
            </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">

              <el-button type="text" size="mini" icon="el-icon-edit" @click.native="editItem(scope.row)" v-permission="['/template/edit']">{{ $t('button.edit') }}</el-button>
              <el-button type="text" size="mini" icon="el-icon-delete" @click.native="removeItem(scope.row)" v-permission="['/template/remove']">{{ $t('button.delete') }}</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[10, 20, 50, 100,500]"
                :page-size="listQuery.limit"
                :total="total"
                :current-page.sync="listQuery.page"
                @size-change="changeSize"
                @current-change="fetchPage"
                @prev-click="fetchPrev"
                @next-click="fetchNext">
        </el-pagination>

        <el-dialog
                :title="formTitle"
                :visible.sync="formVisible"
                width="70%">
            <el-form ref="form" :model="form" :rules="rules" label-width="120px">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="编号"  >
                            <el-input v-model="form.code" minlength=1></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="标题"  >
                            <el-input v-model="form.title" minlength=1></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="消息类型">
                        <el-radio-group v-model="form.type">
                          <el-radio :label="0">短信</el-radio>
                          <el-radio :label="1">邮件</el-radio>
                        </el-radio-group>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="发送器"  >
                          <el-select  v-model="form.idMessageSender"   placeholder="请选择">
                            <el-option
                              v-for="item in sendList"
                              :key="item.id"
                              :label="item.name"
                              :value="item.id"

                            >
                            </el-option>
                          </el-select>

                        </el-form-item>
                    </el-col>
                  <el-col :span="12" v-if="form.type==0">
                    <el-form-item label="远程模板编号"  >
                      <el-input v-model="form.remoteTplCode" placeholder="请输入配置在短信服务商的短信模板编号"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="内容"  >
                      <el-input  type="textarea" :rows="4" v-model="form.content" minlength=1></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-form-item>
                    <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
                    <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
                </el-form-item>

            </el-form>
        </el-dialog>
    </div>
</template>

<script src="./t_message_template.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
    @import "src/styles/common.scss";
</style>

