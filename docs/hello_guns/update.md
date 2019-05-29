# update

参数修改逻辑为：
- 列表页选中修改参数，点击修改按钮，弹出修改页面
- 更改信息，提交修改
- 关闭修改弹窗，并刷新列表页面


## 列表页选中修改参数，点击修改按钮，弹出修改页面


```html
<#button name="修改" icon="fa-edit" clickFun="Cfg.openCfgDetail()" space="true"/>
```

- 打开参数详情弹窗

```javascript
/**
 * 打开查看系统参数详情
 */
Cfg.openCfgDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '系统参数详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cfg/cfg_update/' + Cfg.seItem.id
        });
        this.layerIndex = index;
    }
};
```
- 后台查询参数调准到详情逻辑

```java
    /**
     * 跳转到修改参数
     */
    @RequestMapping("/cfg_update/{cfgId}")
    public String orgUpdate(@PathVariable Long cfgId, Model model) {
        Cfg cfg = cfgRepository.findOne(cfgId);
        model.addAttribute("item",cfg);
        return PREFIX + "cfg_edit.html";
    }
```

- 参数修改页面

```html
@layout("/common/_container.html"){
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <div class="form-horizontal">
            <input type="hidden" id="id" value="${item.id}">
            <div class="row">
                <div class="col-sm-6 b-r">
                            <#input id="id" name="自增主键" value="${item.id}" underline="true"  readonly="readonly"/>
                            <#input id="cfgName" name="参数名" value="${item.cfgName}" />
                </div>
                <div class="col-sm-6">
                            <#input id="cfgValue" name="参数值" value="${item.cfgValue}" underline="true"/>
                            <#input id="cfgDesc" name="参数描述" value="${item.cfgDesc}" />
                </div>
            </div>
            <div class="row btn-group-m-t">
                <div class="col-sm-10">
                    <#button btnCss="info" name="提交" id="ensure" icon="fa-check" clickFun="CfgInfoDlg.editSubmit()"/>
                    <#button btnCss="danger" name="取消" id="cancel" icon="fa-eraser" clickFun="CfgInfoDlg.close()"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctxPath}/static/modular/cfg/cfg/cfg_info.js"></script>
@}

```

## 更改信息，提交修改

- js逻辑提交修改

```javascript

/**
 * 提交修改
 */
CfgInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cfg/update", function(data){
        Feng.success("修改成功!");
        window.parent.Cfg.table.refresh();
        CfgInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cfgInfoData);
    ajax.start();
}
```

- 后台逻辑提交更改

```java
    /**
     * 修改参数
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Cfg cfg) {
        cfgRepository.save(cfg);
        return SUCCESS_TIP;
    }
```
