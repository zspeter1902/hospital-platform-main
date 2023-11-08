package cn.common.req.biz.app.auth;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * 系统用户登陆业务交互参数封装
 * @title: Place.java
 * @author create by singer - Singer email:singer-coder@qq.com
 * @date 2019/4/24 11:13
 */
@Data
public class AuthAppUserLoginReq implements Serializable {

	private static final long serialVersionUID = 4445428827414406099L;

	/**
	 * 唯一账号
	 */
	@NotEmpty(message = "唯一账号->不可为空")
	private String uniqueNo;

	/**
	 * 密码
	 */
	@NotEmpty(message = "密码->不可为空")
	private String password;

	/**
	 * 前端微信的jsCode
	 */
	private String code;

}
