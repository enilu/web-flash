package cn.enilu.flash.bean.core;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:26:43
 */
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;          // 主键ID
    private String account;      // 账号
    private String password;
    private String name;         // 姓名
    private Long deptId;      // 部门id
    private List<Long> roleList; // 角色集
    private String deptName;        // 部门名称
    private List<String> roleNames; // 角色名称集
    private List<String> roleCodes;//角色编码
    private Set<String> urls;//资源路径
    private Set<String> permissions;//资源编码

}
