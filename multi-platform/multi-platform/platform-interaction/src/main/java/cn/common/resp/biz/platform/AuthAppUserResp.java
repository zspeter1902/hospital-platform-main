package cn.common.resp.biz.platform;

import pro.skywalking.req.platform.base.BaseResp;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.*;

/**
 * APP认证用户返回数据封装类
 * @title: AuthAppUserResp.java
 * @author Singer
 * @date 2022/9/27 11:13
 */
@Data
public class AuthAppUserResp extends BaseResp implements Serializable {

    private static final long serialVersionUID = 8863516816547394032L;

    /**
     * 业务主键ID->"authAppUserId"
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

    //-------------其他用户数据 start-----------------
    /**
     * 身份证号
     */
    private String identityCard;

    //-------------其他用户数据 end-----------------

}
