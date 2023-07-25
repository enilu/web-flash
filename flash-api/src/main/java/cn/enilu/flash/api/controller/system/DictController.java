package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.system.Dict;
import cn.enilu.flash.bean.enumeration.ApplicationExceptionEnum;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.cache.DictCache;
import cn.enilu.flash.service.system.DictService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.warpper.DictWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DictController
 *
 * @author enilu
 * @version 2018/11/17 0017
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;
    @Autowired
    private DictCache dictCache;

    /**
     * 获取所有字典列表
     */
    @GetMapping(value = "/list")
    @RequiresPermissions(value = {Permission.DICT})
    public Object list(String name) {

        if (StringUtil.isNotEmpty(name)) {
            List<Dict> list = dictService.findByNameLike(name);
            return Rets.success(new DictWrapper(BeanUtil.objectsToMaps(list)).warp());
        }
        List<Dict> list = dictService.findByPid(0L);
        return Rets.success(new DictWrapper(BeanUtil.objectsToMaps(list)).warp());
    }

    @PostMapping
    @BussinessLog(value = "添加字典", key = "dictName")
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object add(String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        dictService.addDict(dictName, dictValues);
        return Rets.success();
    }

    @PutMapping
    @BussinessLog(value = "修改字典", key = "dictName")
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object update(Long id, String dictName, String dictValues) {
        if (BeanUtil.isOneEmpty(dictName, dictValues)) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        dictService.editDict(id, dictName, dictValues);
        return Rets.success();
    }


    @DeleteMapping
    @BussinessLog(value = "删除字典", key = "id")
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object delete(@RequestParam Long id) {
        dictService.delteDict(id);
        return Rets.success();
    }

    @GetMapping(value = "/getDicts")
    public Object getDicts(@RequestParam String dictName) {
        List<Dict> dicts = dictCache.getDictsByPname(dictName);
        return Rets.success(dicts);
    }
}
