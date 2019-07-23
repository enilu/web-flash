package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.core.ShiroUser;
import cn.enilu.flash.bean.entity.system.User;
import cn.enilu.flash.cache.TokenCache;
import cn.enilu.flash.core.log.LogManager;
import cn.enilu.flash.core.log.LogTaskFactory;
import cn.enilu.flash.service.system.impl.ConstantFactory;
import cn.enilu.flash.utils.Convert;
import cn.enilu.flash.utils.HttpKit;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * AccountService
 *
 * @author enilu
 * @version 2018/9/12 0012
 */
@Service
public class AccountService {
    @Autowired
    private TokenCache tokenCache;
    @Autowired
    private UserService userService;

    public String login(Long idUser) {
        String token = UUID.randomUUID().toString();
        tokenCache.put(token, idUser);
        LogManager.me().executeLog(LogTaskFactory.loginLog(idUser, HttpKit.getIp()));
        User user = userService.get(idUser);
        Long[] roleArray = Convert.toLongArray(true,Convert.toStrArray(",",  user.getRoleid()));
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(user.getAccount());
        shiroUser.setDeptId(user.getDeptid());
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setId(idUser);
        shiroUser.setName(user.getName());
        shiroUser.setRoleList(Lists.newArrayList(roleArray));
        List<String> roleNames = Lists.newArrayList();
        List<String> roleCodes = Lists.newArrayList();
        for (Long roleId : roleArray) {
            roleCodes.add(ConstantFactory.me().getSingleRoleTip(roleId));
            roleNames.add(ConstantFactory.me().getSingleRoleName(roleId));

        }
        shiroUser.setRoleNames(roleNames);
        shiroUser.setRoleCodes(roleCodes);
        tokenCache.setUser(token,shiroUser);
        return token;
    }


    public void logout(String token) {
        tokenCache.remove(token);
    }

}
