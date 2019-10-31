<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="24">
        <el-col :span="4">
          <el-date-picker type="date" size="mini" placeholder="起始日期" v-model="listQuery.beginTime" value-format="yyyy-MM-dd"
                          style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-date-picker type="date" size="mini" placeholder="结束日期" v-model="listQuery.endTime"  value-format="yyyy-MM-dd"
                          style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-input v-model="listQuery.logName" size="mini" placeholder="日志名称"></el-input>
        </el-col>
        <el-col :span="4">
          <el-select v-model="listQuery.logType" size="mini" placeholder="日志类型">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="8">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="clear">{{ $t('button.clear') }}</el-button>
        </el-col>
      </el-row>
      <br>
    </div>

    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>
     <el-table-column type="expand">
       <template slot-scope="props">
         <el-form label-position="left" inline class="flash-table-expand">
           <el-form-item label="日志类型">
             <span>{{ props.row.logtype }}</span>
           </el-form-item>
           <el-form-item label="日志名称">
             <span>{{ props.row.logname }}</span>
           </el-form-item>
           <el-form-item label="用户">
             <span>{{ props.row.userName }}</span>
           </el-form-item>
           <el-form-item label="类名">
             <span>{{ props.row.classname }}</span>
           </el-form-item>
           <el-form-item label="方法名">
             <span>{{ props.row.method }}</span>
           </el-form-item>
           <el-form-item label="时间">
             <span>{{ props.row.createtime }}</span>
           </el-form-item>
           <el-form-item label="内容">
             <span>{{ props.row.regularMessage }}</span>
           </el-form-item>
         </el-form>
       </template>
     </el-table-column>

     <el-table-column
       label="用户"
       prop="userName">
     </el-table-column>
     <el-table-column
       label="日志类型"
       prop="logtype">
     </el-table-column>
     <el-table-column
       label="日志名称"
       prop="logname">
     </el-table-column>
     <el-table-column
       label="时间"
       prop="createtime">
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

<script src="./log.js"></script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
