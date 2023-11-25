package cn.enilu.flash.api.controller.system;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.Const;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.constant.state.ManagerStatus;
import cn.enilu.flash.bean.core.BussinessLog;
import cn.enilu.flash.bean.dto.UserDto;
import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.bean.enumeration.ApplicationExceptionEnum;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.exception.ApplicationException;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.core.factory.UserFactory;
import cn.enilu.flash.service.system.DeptService;
import cn.enilu.flash.service.system.UserService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.MD5;
import cn.enilu.flash.utils.RandomUtil;
import cn.enilu.flash.utils.factory.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserController
 *
 * @author enilu
 * @version 2018/9/15 0015
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    @GetMapping(value = "/list")
    @RequiresPermissions(value = {Permission.USER})
    public Object list(@RequestParam(required = false) String account,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Long deptid,
                       @RequestParam(required = false) String phone,
                       @RequestParam(required = false) Integer status,
                       @RequestParam(required = false) Integer sex,
                        @RequestParam(required = false) String startTime,
                       @RequestParam(required = false) String endTime
    ) {
        Page page = new PageFactory().defaultPage();
        page.addFilter("name", SearchFilter.Operator.LIKE, name);
        page.addFilter("account", SearchFilter.Operator.LIKE, account);
        page.addFilter("deptid", deptid);
        page.addFilter("phone", phone);
        page.addFilter("status", status);
        page.addFilter("status", SearchFilter.Operator.GT, 0);
        page.addFilter("sex", sex);
        page.addFilter("createTime", SearchFilter.Operator.GTE, DateUtil.parseTime(startTime));
        page.addFilter("createTime", SearchFilter.Operator.LTE, DateUtil.parseTime(endTime));
        page = userService.queryPage(page);
        return Rets.success(page);
    }

    @PostMapping
    @BussinessLog(value = "编辑账号", key = "name")
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object save(@RequestBody @Valid UserDto user, BindingResult result) {
        User repeatUser = userService.findByAccountForLogin(user.getAccount());
        if (user.getId() == null) {

            // 判断账号是否重复
            if (repeatUser != null) {
              return Rets.failure("账号重复");
            }
            // 完善账号信息
            String password = RandomUtil.getRandomPassword();
            user.setSalt(RandomUtil.getRandomString(5));
            user.setPassword(MD5.md5(password, user.getSalt()));
            user.setStatus(ManagerStatus.OK.getCode());
            userService.insert(UserFactory.createUser(user, new User()));
            return Rets.success(password);
        } else {
            if(repeatUser!=null&&repeatUser.getId().intValue()!=user.getId().intValue()){
                return Rets.failure("账号重复");
            }

            User oldUser = userService.get(user.getId());
            userService.update(UserFactory.updateUser(user, oldUser));
            return Rets.success();
        }

    }

    @BussinessLog(value = "删除账号", key = "userId")
    @DeleteMapping
    @RequiresPermissions(value = {Permission.USER_DEL})
    public Object remove(@RequestParam Long userId) {
        if (userId == null) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        if (userId.intValue() <= 3) {
            return Rets.failure("不能删除初始用户");
        }
        User user = userService.get(userId);
        user.setStatus(ManagerStatus.DELETED.getCode());
        userService.update(user);
        return Rets.success();
    }

    @BussinessLog(value = "设置账号角色", key = "userId")
    @PostMapping(value = "/setRole")
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object setRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
        if (BeanUtil.isOneEmpty(userId, roleIds)) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.intValue() == Const.ADMIN_ID.intValue()) {
            return Rets.failure("不能修改超级管理员得角色");
        }
        User user = userService.get(userId);
        user.setRoleid(roleIds);
        userService.update(user);
        return Rets.success();
    }

    @BussinessLog(value = "冻结/解冻账号", key = "userId")
    @GetMapping(value = "changeStatus")
    @RequiresPermissions(value = {Permission.USER_EDIT})
    public Object changeStatus(@RequestParam Long userId) {
        if (userId == null) {
            throw new ApplicationException(ApplicationExceptionEnum.REQUEST_NULL);
        }
        if (userId.intValue() <= 3) {
            return Rets.failure("不能冻结初始用户");
        }
        User user = userService.get(userId);
        user.setStatus(user.getStatus().intValue() == ManagerStatus.OK.getCode() ? ManagerStatus.FREEZED.getCode() : ManagerStatus.OK.getCode());
        userService.update(user);
        return Rets.success();
    }
    @BussinessLog(value = "重置密码", key = "userId")
    @PostMapping(value="resetPassword")
    public Object resetPassword(Long userId){
        User user = userService.get(userId);
        String password = RandomUtil.getRandomPassword();
        user.setPassword(MD5.md5(password, user.getSalt()));
        userService.update(user);
        return Rets.success(password);
    }
    @GetMapping(value = "getAllDepts")
    public Object getAllDepts(){
        return Rets.success(deptService.queryAll());
    }
}
