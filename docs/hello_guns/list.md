# list


## 列表页面

列表页面针对：添加、删除、修改 三个操作按钮做了权限控制，具体逻辑在下文再详细描述

```html
@layout("/common/_container.html"){
<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>系统参数管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">                   
                        <div class="hidden-xs" id="CfgTableToolbar" role="group">
                            @if(shiro.hasPermission("/cfg/add")){
                                <#button name="添加" icon="fa-plus" clickFun="Cfg.openAddCfg()"/>
                            @}
                            @if(shiro.hasPermission("/cfg/update")){
                                <#button name="修改" icon="fa-edit" clickFun="Cfg.openCfgDetail()" space="true"/>
                            @}
                            @if(shiro.hasPermission("/cfg/delete")){
                                <#button name="删除" icon="fa-minus" clickFun="Cfg.delete()" space="true"/>
                            @}
                        </div>
                        <#table id="CfgTable"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctxPath}/static/modular/cfg/cfg/cfg.js"></script>
@}

```

## 查询列表数据的js代码:

```javascript
/**
 * 系统参数管理初始化
 */
var Cfg = {
    id: "CfgTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Cfg.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自增主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '参数名', field: 'cfgName', visible: true, align: 'center', valign: 'middle'},
            {title: '参数值', field: 'cfgValue', visible: true, align: 'center', valign: 'middle'},
            {title: '参数描述', field: 'cfgDesc', visible: true, align: 'center', valign: 'middle'}
    ];
};
 

$(function () {
    var defaultColunms = Cfg.initColumn();
    var table = new BSTable(Cfg.id, "/cfg/list", defaultColunms);
    //使用客户端分页（假分页）
    table.setPaginationType("client");
    Cfg.table = table.init();
});

/**
 * 检查是否选中
 */
Cfg.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Cfg.seItem = selected[0];
        return true;
    }
};

```

## 后台代码

```java

   /**
     * 跳转到参数首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cfg.html";
    }
    
    /**
     * 获取参数列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        return cfgRepository.findAll();
    }
```