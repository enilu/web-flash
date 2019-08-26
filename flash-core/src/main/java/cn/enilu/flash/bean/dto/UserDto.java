package cn.enilu.flash.bean.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 用户传输bean
 * 
 * @author stylefeng
 * @Date 2017/5/5 22:40
 */
@Data
public class UserDto{

	private Long id;

	@NotBlank(message = "账号不能为空")
	private String account;
	@NotBlank(message = "密码不能为空")
	private String password;
	private String salt;
	@NotBlank(message = "姓名不能为空")
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Integer sex;
	private String email;
	private String phone;
	private String roleid;
	private Long deptid;
	private Long eduorgid;
	private Integer status;
	private Date createtime;
	private Integer version;
	private String avatar;


}
