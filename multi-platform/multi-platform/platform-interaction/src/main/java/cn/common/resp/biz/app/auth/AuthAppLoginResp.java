package cn.common.resp.biz.app.auth;

import lombok.Data;
import pro.skywalking.req.platform.base.BaseResp;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: APP登陆返回类封装
 * @date 2023-02-21
 */
@Data
public class AuthAppLoginResp extends BaseResp implements Serializable {

    private static final long serialVersionUID = 1580802885439128161L;


    /**
     * 业务主键ID
     */
    private String authAppUserId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户编号
     */
    private String userNumber;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 绑定三方平台的OpenID
     */
    private String openId;

    /**
     * 明文密码
     */
    private String decryptionPassword;

    /**
     * 密码(MD5)
     */
    private String password;

    /**
     * 用户状态 跟随枚举
     */
    private Integer authStatus;

    /**
     * 返回的Token
     */
    private String token;

}
