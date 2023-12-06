package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.system.Dict;
import cn.enilu.flash.bean.enumeration.ApplicationExceptionEnum;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.cache.DictCache;
import cn.enilu.flash.service.system.DictService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.Lists;
import cn.enilu.flash.utils.Maps;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.warpper.DictWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public Object add(@RequestBody @Valid Dict dict) {
        if (BeanUtil.isOneEmpty(dict.getName(), dict.getDetail())) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        dictService.addDict(dict.getName(), dict.getDetail());
        return Rets.success();
    }

    @PutMapping
    @BussinessLog(value = "修改字典", key = "dictName")
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object update(@RequestBody @Valid Dict dict) {
        if (BeanUtil.isOneEmpty(dict.getName(), dict.getDetail())) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        dictService.editDict(dict.getId(), dict.getName(), dict.getDetail());
        return Rets.success();
    }


    @DeleteMapping
    @BussinessLog(value = "删除字典", key = "id")
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Object delete(@RequestParam Long id) {
        dictService.delteDict(id);
        return Rets.success();
    }

    @DeleteMapping("batchRemove")
    @BussinessLog(value = "批量删除字典", key = "id")
    @RequiresPermissions(value = {Permission.DICT_EDIT})
    public Ret batchRemove(@RequestParam(value = "id[]") Long[] id) {
        for (Long dictId : id) {
            if (dictId == null) {
                continue;
            }
            dictService.delete(dictId);
        }
        return Rets.success();
    }
    @GetMapping(value = "/getDicts")
    public Object getDicts(@RequestParam String dictName) {
        List<Dict> dicts = dictCache.getDictsByPname(dictName);
        return Rets.success(dicts);
    }
    @GetMapping(value = "/getDicts/V2")
    public Object getDictsV2(@RequestParam String dictName) {
        List<Dict> dicts = dictCache.getDictsByPname(dictName);
        List<Map> results = Lists.newArrayList();
        for(Dict dict:dicts){
            results.add(Maps.newHashMap("label",dict.getName(),"value",Integer.valueOf(dict.getNum())));
        }
        return Rets.success(results);
    }
}
