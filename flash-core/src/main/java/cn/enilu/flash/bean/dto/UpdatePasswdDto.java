package cn.enilu.flash.bean.dto;

import lombok.Data;

/**
 * @description: desc
 * @author: zhangtao
 * @create: 2024/2/11 10:35
 **/
@Data
public class UpdatePasswdDto {
    private String oldPassword;
    private String password;
    private String rePassword;
}
