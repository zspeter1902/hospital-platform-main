package cn.common.req.biz.app.auth;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * 系统用户注册
 * @title: Place.java
 * @author create by singer - Singer email:singer-coder@qq.com
 * @date 2019/4/24 11:13
 */
@Data
public class AuthAppUserReqReq implements Serializable {

	private static final long serialVersionUID = -461019252345946879L;

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
	 * 前端微信(或者第三方平台的)的openId
	 */
	private String openId;

	/**
	 * 前端微信(或者第三方平台的)的jsCode
	 */
	private String code;

}
