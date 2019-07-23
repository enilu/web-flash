package cn.enilu.flash.service.system.impl;

import cn.enilu.flash.bean.constant.cache.CacheKey;
import cn.enilu.flash.bean.constant.state.ManagerStatus;
import cn.enilu.flash.bean.constant.state.MenuStatus;
import cn.enilu.flash.bean.entity.system.*;
import cn.enilu.flash.bean.vo.DictVo;
import cn.enilu.flash.bean.vo.SpringContextHolder;
import cn.enilu.flash.cache.ConfigCache;
import cn.enilu.flash.cache.DictCache;
import cn.enilu.flash.dao.system.*;
import cn.enilu.flash.service.system.IConstantFactory;
import cn.enilu.flash.service.system.LogObjectHolder;
import cn.enilu.flash.utils.Convert;
import cn.enilu.flash.utils.StringUtils;
import cn.enilu.flash.utils.cache.TimeCacheMap;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
@CacheConfig
public class ConstantFactory implements IConstantFactory {
    public static TimeCacheMap<String, String> cache = new TimeCacheMap<String, String>(3600, 2);
    private RoleRepository roleRepository = SpringContextHolder.getBean(RoleRepository.class);
    private DeptRepository deptRepository = SpringContextHolder.getBean(DeptRepository.class);
    private DictCache dictCache = SpringContextHolder.getBean(DictCache.class);
    private DictRepository dictRepository = SpringContextHolder.getBean(DictRepository.class);
    private UserRepository userRepository = SpringContextHolder.getBean(UserRepository.class);
    private MenuRepository menuRepository = SpringContextHolder.getBean(MenuRepository.class);
    private NoticeRepository sysNoticeRepository = SpringContextHolder.getBean(NoticeRepository.class);
    private ConfigCache configCache = SpringContextHolder.getBean(ConfigCache.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void set(String key, String val) {
        cache.put(key, val);

    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(Long userId) {
        String val = get(CacheKey.SYS_USER_NAME + userId);
        if (StringUtils.isNotEmpty(val)) {
            return val;
        }

        User user = getUser(userId);
        if (user != null) {
            val = user.getName();
            set(CacheKey.SYS_USER_NAME + userId, val);
            return val;
        }


        return "--";
    }

    private User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        return null;
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(Long userId) {
        User user = getUser(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    public String getRoleName(String roleIds) {
        String val = get(CacheKey.ROLES_NAME + roleIds);
        if (StringUtils.isNotEmpty(val)) {
            return val;
        }
        Integer[] roles = Convert.toIntArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (Integer role : roles) {
            Role roleObj = getRole(Long.valueOf(role));
            if (StringUtils.isNotNullOrEmpty(roleObj) && StringUtils.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        val = StringUtils.removeSuffix(sb.toString(), ",");
        set(CacheKey.ROLES_NAME + roleIds, val);
        return val;
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    public String getSingleRoleName(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = getRole(roleId);
        if (StringUtils.isNotNullOrEmpty(roleObj) && StringUtils.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    public String getSingleRoleTip(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = getRole(roleId);
        if (StringUtils.isNotNullOrEmpty(roleObj) && StringUtils.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }

    /**
     * 获取部门名称
     */
    @Override
    public String getDeptName(Long deptId) {
        if (deptId == null) {
            return null;
        }
        String val = get(CacheKey.DEPT_NAME + deptId);
        if (StringUtils.isNotEmpty(val)) {
            return val;
        }
        Dept dept = getDept(deptId);
        if (StringUtils.isNotNullOrEmpty(dept) && StringUtils.isNotEmpty(dept.getFullname())) {
            val = dept.getFullname();
            set(CacheKey.DEPT_NAME + deptId, val);
            return val;
        }
        return "";
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNames(String menuIds) {
        Integer[] menuArray = Convert.toIntArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (int menuId : menuArray) {
            Menu menuObj = getMenu(Long.valueOf(menuId));
            if (StringUtils.isNotNullOrEmpty(menuObj) && StringUtils.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StringUtils.removeSuffix(sb.toString(), ",");
    }

    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(Long menuId) {

        Menu menu = getMenu(menuId);
        if (menu == null) {
            return "";
        } else {
            return menu.getName();
        }
    }

    /**
     * 获取菜单名称通过编号
     */
    @Override
    public String getMenuNameByCode(String code) {

        Menu menu = menuRepository.findByCode(code);
        if (menu == null) {
            return "";
        } else {
            return menu.getName();
        }
    }

    @Override
    public List<DictVo> findByDictName(String dictName) {

        List<DictVo> list = new ArrayList<DictVo>();

        List<Dict> dicts = dictCache.getDictsByPname(dictName);
        if (dicts != null) {
            for (int i = 0; i < dicts.size(); i++) {
                Dict dict = dicts.get(i);
                DictVo dictVo = new DictVo(dict.getNum(), dict.getName());
                list.add(dictVo);
            }
        }
        return list;
    }

    /**
     * 获取字典名称
     */
    @Override
    public String getDictName(Long dictId) {

        String val = get(CacheKey.DICT_NAME + dictId);
        if (StringUtils.isNotEmpty(val)) {
            return val;
        }
        val = dictCache.getDict(dictId);
        set(CacheKey.DICT_NAME + dictId, val);
        return val;

    }

    /**
     * 获取通知标题
     */
    @Override
    public String getNoticeTitle(Long id) {

        Notice notice = getNotice(id);
        if (notice == null) {
            return "";
        } else {
            return notice.getTitle();
        }

    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName(String name, String val) {
        String result = get(CacheKey.DICT_NAME + name + val);
        if (StringUtils.isNotEmpty(result)) {
            return result;
        }
        List<Dict> dicts = dictCache.getDictsByPname(name);
        for (Dict item : dicts) {
            if (item.getNum() != null && item.getNum().equals(val)) {
                result = item.getName();
                set(CacheKey.DICT_NAME + name + val, result);
                return result;
            }
        }
        return "";

    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", String.valueOf(sex));
    }

    @Override
    public String getCardTypeName(String cardType) {
        return getDictsByName("银行卡类型", cardType);
    }

    @Override
    public String getIdCardTypeName(String cardType) {
        return getDictsByName("证件类型", cardType);
    }

    @Override
    public String getRelationName(String relation) {
        return getDictsByName("联系人关系", relation);
    }

    /**
     * 获取用户登录状态
     */
    @Override
    public String getStatusName(Integer status) {
        return ManagerStatus.valueOf(status);
    }

    /**
     * 获取菜单状态
     */
    @Override
    public String getMenuStatusName(Integer status) {
        return MenuStatus.valueOf(status);
    }

    /**
     * 查询字典
     */
    @Override
    public List<Dict> findInDict(Long id) {
        return dictRepository.findByPid(id);

    }

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    /**
     * 获取子部门id
     */
    @Override
    public List<Long> getSubDeptId(Long deptid) {

        List<Dept> depts = this.deptRepository.findByPidsLike("%[" + deptid + "]%");

        ArrayList<Long> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getId());
            }
        }

        return deptids;
    }

    /**
     * 获取所有父部门id
     */
    @Override
    public List<Integer> getParentDeptIds(Long deptid) {
        Dept dept = getDept(deptid);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StringUtils.removeSuffix(StringUtils.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }


    @Override
    public List<Dict> getDicts(String pname) {
        return dictCache.getDictsByPname(pname);
    }

    @Override
    public String getCfg(String cfgName) {
        String val = get(CacheKey.CFG + cfgName);
        if (StringUtils.isNotEmpty(val)) {
            return val;
        }
        val = (String) configCache.get(cfgName);
        set(CacheKey.CFG + cfgName, val);
        return val;
    }
    @Override
    public Role getRole(Long id) {
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    @Override
    public Dept getDept(Long id) {
        Optional<Dept> optional = deptRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    @Override
    public Menu getMenu(Long id) {
        Optional<Menu> optiona = menuRepository.findById(id);
        if (optiona.isPresent()) {
            return optiona.get();
        }
        return null;
    }
    @Override
    public Notice getNotice(Long id) {
        Optional<Notice> optional = sysNoticeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

}
