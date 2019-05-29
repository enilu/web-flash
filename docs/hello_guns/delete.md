# delete

## 选中要删除的参数，点击“删除”按钮后js逻辑

```html
<#button name="删除" icon="fa-minus" clickFun="Cfg.delete()" space="true"/>
```

```javascript

/**
 * 删除系统参数
 */
Cfg.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cfg/delete", function (data) {
            Feng.success("删除成功!");
            Cfg.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cfgId",this.seItem.id);
        ajax.start();
    }
};

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

## 后台逻辑

```java

    /**
     * 删除参数
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long cfgId) {
        cfgRepository.delete(cfgId);
        return SUCCESS_TIP;
    }
    
```