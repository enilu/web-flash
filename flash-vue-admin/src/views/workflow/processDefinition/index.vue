<template>
  <div class="app-container">
    <div class="block">
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="onlineDrawingProcess" v-permission="['/workflow/process/definition/edit']">{{ $t('button.add') }}</el-button>
        </el-col>
      </el-row>
    </div>


    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange">
      <el-table-column label="名称">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="标识">
        <template slot-scope="scope">
          {{scope.row.key}}
        </template>
      </el-table-column>
      <!--<el-table-column label="版本号">-->
        <!--<template slot-scope="scope">-->
          <!--{{scope.row.version}}-->
        <!--</template>-->
      <!--</el-table-column>-->
      <el-table-column label="部署id">
        <template slot-scope="scope">
          {{scope.row.deploymentId}}
        </template>
      </el-table-column>
      <el-table-column label="流程资源">
        <template slot-scope="scope">
          {{scope.row.resourceName}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit" @click.native="onlineModificationProcess(scope.row)">{{ $t('button.view') }}</el-button>
          <el-button type="text" size="mini" icon="el-icon-delete" @click.native="removeItem(scope.row)" v-permission="['/workflow/process/definition/delete']">{{ $t('button.delete') }}</el-button>
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

    <!--bpmnjs在线流程设计器-->
    <el-dialog
      :visible.sync="processDefinitionForm.visible"
      :title="processDefinitionForm.title"
      @close="closeDrawingWindow"

      :fullscreen="true"
      append-to-body
    >
      <div style="position:relative;height: 100%;">
        <iframe
          id="iframe"
          :src="processDefinitionForm.modelerUrl"
          frameborder="0"
          width="100%"
          height="600px"
          scrolling="auto"
        ></iframe>
      </div>
    </el-dialog>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称">
              <el-input v-model="form.name" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="标识">
              <el-input v-model="form.key" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="版本号">
              <el-input v-model="form.version" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部署id">
              <el-input v-model="form.deploymentId" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="流程资源">
              <el-input v-model="form.resourceName" minlength=1></el-input>
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

<script src="./processDefinition.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

