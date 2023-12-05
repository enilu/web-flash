package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.entity.system.Dept;
import cn.enilu.flash.bean.enumeration.ApplicationExceptionEnum;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Ret;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.node.DeptNode;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.system.DeptService;
import cn.enilu.flash.service.system.LogObjectHolder;
import cn.enilu.flash.service.system.RoleService;
import cn.enilu.flash.service.system.UserService;
import cn.enilu.flash.utils.BeanUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * DeptContoller
 *
 * @author enilu
 * @version 2018/9/15 0015
 */
@RestController
@RequestMapping("/dept")
public class DeptContoller extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    @RequiresPermissions(value = {Permission.DEPT})
    public Object list() {
        List<DeptNode> list = deptService.queryAllNode();
        return Rets.success(list);
    }

    @PostMapping
    @BussinessLog(value = "编辑部门", key = "simplename")
    @RequiresPermissions(value = {Permission.DEPT_EDIT})
    public Object save(@RequestBody @Valid Dept dept) {
        if (BeanUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        if (dept.getId() != null) {
            Dept old = deptService.get(dept.getId());
            LogObjectHolder.me().set(old);
            old.setPid(dept.getPid());
            old.setSimplename(dept.getSimplename());
            old.setFullname(dept.getFullname());
            old.setNum(dept.getNum());
            deptService.deptSetPids(old);
            deptService.update(old);
        } else {
            deptService.deptSetPids(dept);
            deptService.insert(dept);
        }
        return Rets.success();
    }

    @DeleteMapping
    @BussinessLog(value = "删除部门", key = "id")
    @RequiresPermissions(value = {Permission.DEPT_DEL})
    public Object remove(@RequestParam Long id) {
        if (id == null) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        if (id < 5) {
            return Rets.failure("禁止删除初始部门");
        }
        if (deptService.count(SearchFilter.build("pid", id)) > 0
                || userService.count(SearchFilter.build("deptid", id)) > 0
                || roleService.count(SearchFilter.build("deptid", id)) > 0) {
            return Rets.failure("该部门下有关联的用户或角色，无法删除");
        }
        deptService.deleteDept(id);
        return Rets.success();
    }


    @DeleteMapping("batchRemove")
    @BussinessLog(value = "批量删除部门", key = "id")
    @RequiresPermissions(value = {Permission.DEPT_DEL})
    public Ret batchRemove(@RequestParam(value = "id[]") Long[] id) {
        for (Long deptId : id) {
            if (deptId == null) {
                continue;
            }
            if (deptService.count(SearchFilter.build("pid", deptId)) > 0
                    || userService.count(SearchFilter.build("deptid", deptId)) > 0
                    || roleService.count(SearchFilter.build("deptid", deptId)) > 0) {
                return Rets.failure("该部门下有关联的用户或角色，无法删除");
            }
            deptService.delete(deptId);
        }
        return Rets.success();
    }
}
