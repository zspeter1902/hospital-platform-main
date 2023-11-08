package cn.common.resp.biz.platform;

import pro.skywalking.validation.NotEmpty;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.*;

/**
 * APP认证用户数据导出请求封装类
 * @title: AuthAppUserExportResp.java
 * @author Singer
 * @date
*/
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class AuthAppUserExportResp implements Serializable {

    private static final long serialVersionUID = 4015963833562398719L;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名->不可为空")
    @ExcelProperty(value = "用户名")
    private String userName;

    /**
     * 用户编号
     */
    @NotEmpty(message = "用户编号->不可为空")
    @ExcelProperty(value = "用户编号")
    private String userNumber;

    /**
     * 手机号码
     */
    @NotEmpty(message = "手机号码->不可为空")
    @ExcelProperty(value = "手机号码")
    private String phoneNumber;

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称->不可为空")
    @ExcelProperty(value = "昵称")
    private String nickName;

    /**
     * 头像
     */
    @NotEmpty(message = "头像->不可为空")
    @ExcelProperty(value = "头像")
    private String avatarUrl;

    /**
     * 真实姓名
     */
    @NotEmpty(message = "真实姓名->不可为空")
    @ExcelProperty(value = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @NotEmpty(message = "性别->不可为空")
    @ExcelProperty(value = "性别")
    private String gender;

    /**
     * 绑定三方平台的OpenID
     */
    @NotEmpty(message = "绑定三方平台的OpenID->不可为空")
    @ExcelProperty(value = "绑定三方平台的OpenID")
    private String openId;

    /**
     * 明文密码
     */
    @NotEmpty(message = "明文密码->不可为空")
    @ExcelProperty(value = "明文密码")
    private String decryptionPassword;

    /**
     * 密码(MD5)
     */
    @NotEmpty(message = "密码(MD5)->不可为空")
    @ExcelProperty(value = "密码(MD5)")
    private String password;

    /**
     * 用户状态 跟随枚举
     */
    @NotEmpty(message = "用户状态 跟随枚举->不可为空")
    @ExcelProperty(value = "用户状态 跟随枚举")
    private Integer authStatus;

}
