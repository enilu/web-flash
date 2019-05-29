<template>
  <div class="app-container">
    <div class="block">

<el-table :data="data" style="width: 100%" :row-style="treeTableShowTr" size="small"
                  :highlight-current-row="true">
            <el-table-column label="菜单名称" header-align="center" prop="name"
                             width="200" :show-overflow-tooltip="true" align="left">
                <template slot-scope="scope">
                    <span v-for="(space, levelIndex) in treeTablePath(scope.row.path)" class="ms-tree-space"></span>
                    <span v-if="treeTableIconShow(scope.row)" @click="treeTableToggle(scope.$index)"
                          style="cursor: pointer">
                        <span v-if="!scope.row.expanded"
                              class="el-tree-node__expand-icon el-icon-caret-right" title="展开"></span>

                        <span v-if="scope.row.expanded"
                              class="el-tree-node__expand-icon el-icon-caret-right expanded" title="关闭"></span>
                    {{ scope.row.name }}
                    </span>
                    <span v-if="!treeTableIconShow(scope.row)">
                         <span class="ms-tree-space"></span>
                        {{ scope.row.name }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column label="code" header-align="center" prop="code"
                             :show-overflow-tooltip="true" align="center">
            </el-table-column>

            <el-table-column label="url" header-align="center" prop="url"
                             :show-overflow-tooltip="true" align="center">
            </el-table-column>

            <el-table-column label="是否是菜单" header-align="center" prop="isMenuName"
                             :show-overflow-tooltip="true" align="center">
            </el-table-column>

            <el-table-column label="操作" header-align="center" prop="website"
                             :show-overflow-tooltip="true" align="center" width="120">
                <template slot-scope="scope">
                    <el-dropdown @command="dropdownCommand">
                        <el-button size="mini">
                            <span class="el-icon-arrow-down"></span>
                        </el-button>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item :command="{type:'edit',id:scope.row.id}">
                                修改
                            </el-dropdown-item>
                            <el-dropdown-item :command="{type:'delete',id:scope.row.id}"
                                              v-if="scope.row.path!='0001'">
                                删除
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </template>
            </el-table-column>
        </el-table>
      </div>
        </div>
        </template>
<script>

import { getList , save , delMenu } from '@/api/system/menu'

  export default {
    data() {
            return {
                addDialogVisible: false,
                editDialogVisible: false,
                data: [{"id":"vvv","parentId":"","path":"0002","name":"vvv","location":1,"hasChildren":false,"expanded":false}, {"id":"1ef4478f20c54df391f104f2e3ac4be9","parentId":"","path":"0001","name":"系统管理","aliasName":"System","address":"银河-太阳系-地球","telephone":"","email":"wizzer@qq.com","website":"https://wizzer.cn","location":2,"hasChildren":true,"opBy":"","opAt":1532061870,"delFlag":false,"expanded":false,"children":["1ef4478f20c54df391f104f2e3ac4be9"]}, {"id":"xxx","parentId":"1ef4478f20c54df391f104f2e3ac4be9","path":"00010001","name":"测试","location":2,"hasChildren":false,"opBy":"","opAt":1532061870,"delFlag":false,"expanded":false}],
                pageSize: 10,
                pageNumber: 1,
                pageOrderName: "",
                pageOrderBy: "",
                totalCount: 0,
            }
        },

        created: function () {
            this.fetchData()
        },
        methods: {
          fetchData() {
            this.listLoading = true
            getList(this.listQuery).then(response => {
              this.data = response.data.items
              this.listLoading = false
            })
          },
            // 显示层级
            treeTablePath: function (path) {
                return path.length / 4 - 1;
            },
            // 显示行
            treeTableShowTr: function (row, index) {
                var parentIndex = this.data.findIndex(function (value) {
                    return value.id === row.row.pid;
                });
                var show = (row.row.pid ? (this.data[parentIndex].expanded && this.data[parentIndex]._show) : true);
                row.row._show = show;
                return show ? '' : 'display:none;'
            },
            // 展开下级
            treeTableToggle: function (trIndex) {
                console.log(trIndex)
                var record = this.data[trIndex];
                record.expanded = !record.expanded;
            },
            // 点击展开和关闭的时候，图标的切换
            treeTableIconShow: function (record) {
                if (record.children && record.children.length > 0) {
                    return true;
                }
                return false;
            },
            dropdownCommand: function (command) {//监听下拉框事件
                var self = this;
                if ("edit" == command.type) {

                }
                if ("delete" == command.type) {

                }
            }
        }
    };
</script>
<style>
.ms-tree-space {
    position: relative;
    top: 1px;
    display: inline-block;
    font-family: 'Glyphicons Halflings';
    font-style: normal;
    font-weight: 400;
    line-height: 1;
    width: 18px;
    height: 14px;
}

.ms-tree-space::before {
    content: ""
}

.el-table__expand-icon--expanded {
    -ms-transform: rotate(90deg);
    transform: rotate(90deg);
}
</style>
