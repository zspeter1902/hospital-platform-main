package cn.common.internal;


import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * 系统用户交互参数封装
 * @title: Place.java
 * @author create by Singer - Singer email:singer-coder@qq.com
 * @date 2019/4/24 11:13
 */
@Data
public class AuthUserLoginReq implements Serializable {

	private static final long serialVersionUID = 4445428827414406099L;

	/**
	  * 用户名
	  */
	@NotEmpty(message = "用户名->不可为空")
	private String userName;

	/**
	 * 验证码
	 */
	@NotEmpty(message = "验证码->不可为空")
	private  String verifyCode;
	/**
	 * 密码
	 */
	@NotEmpty(message = "用户名->密码")
	private String password;

}
