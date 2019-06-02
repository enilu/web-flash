package cn.enilu.flash.core.factory;

import cn.enilu.flash.bean.dto.UserDto;
import cn.enilu.flash.bean.entity.system.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static User createUser(UserDto userDto, User user){
        if(userDto == null){
            return null;
        }else{
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
    public static User updateUser(UserDto userDto,User user){
        if(userDto == null){
            return null;
        }else{
            user.setName(userDto.getName());
            user.setDeptid(userDto.getDeptid());
            user.setSex(userDto.getSex());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setBirthday(userDto.getBirthday());
            if(userDto.getStatus()!=null){
                user.setStatus(userDto.getStatus());
            }
            return user;
        }
    }
}
