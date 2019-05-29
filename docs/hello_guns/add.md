# add


## 添加参数

添加参数功能为点击“添加”按钮后调用对应的js代码逻辑弹出添加页面，在添加页面输入相关信息提交保存，保存成功后关闭弹窗，并刷新参数列表数据.

### 添加按钮注册点击函数:

```html
 <#button name="添加" icon="fa-plus" clickFun="Cfg.openAddCfg()"/>
```
### 添加 按钮点击逻辑：

```javascript

/**
 * 点击添加系统参数
 */
Cfg.openAddCfg = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统参数',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cfg/cfg_add'
    });
    this.layerIndex = index;
};
```
### 添加页面代码

```html
@layout("/common/_container.html"){
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="form-horizontal">
            <input type="hidden" id="id" value="">
            <div class="row">
                <div class="col-sm-6 b-r">
                            <#input id="cfgName" name="参数名"/>
                            <#input id="cfgValue" name="参数值" underline="true"/>
                </div>
                <div class="col-sm-6">
                            <#input id="cfgDesc" name="参数描述" underline="true"/>
                </div>
            </div>
            <div class="row btn-group-m-t">
                <div class="col-sm-10">
                    <#button btnCss="info" name="提交" id="ensure" icon="fa-check" clickFun="CfgInfoDlg.addSubmit()"/>
                    <#button btnCss="danger" name="取消" id="cancel" icon="fa-eraser" clickFun="CfgInfoDlg.close()"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctxPath}/static/modular/cfg/cfg/cfg_info.js"></script>
@}

```

### 点击“提交”按钮提交参数保存逻辑：

```javascript
/**
 * 初始化系统参数详情对话框
 */
var CfgInfoDlg = {
    cfgInfoData : {}
};

/**
 * 清除数据
 */
CfgInfoDlg.clearData = function() {
    this.cfgInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CfgInfoDlg.set = function(key, val) {
    this.cfgInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CfgInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CfgInfoDlg.close = function() {
    parent.layer.close(window.parent.Cfg.layerIndex);
}

/**
 * 收集数据
 */
CfgInfoDlg.collectData = function() {
    this
    .set('id')
    .set('cfgName')
    .set('cfgValue')
    .set('cfgDesc');
}

/**
 * 提交添加
 */
CfgInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cfg/add", function(data){
        Feng.success("添加成功!");
        //刷新参数列表数据
        window.parent.Cfg.table.refresh();
        //关闭当前弹窗
        CfgInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cfgInfoData);
    ajax.start();
}


```


## 后台保存逻辑

```java
    /**
     * 跳转到添加参数
     */
    @RequestMapping("/cfg_add")
    public String orgAdd() {
        return PREFIX + "cfg_add.html";
    }
    /**
     * 新增参数
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Cfg cfg) {
        cfgRepository.save(cfg);
        return SUCCESS_TIP;
    }
```