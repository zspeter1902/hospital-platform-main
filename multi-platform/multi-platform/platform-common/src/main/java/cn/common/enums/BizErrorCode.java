package cn.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.ant.fun.common.enums
 * @Description: 系统错误码枚举
 * @date 2021-01-21
 */
@Getter
@AllArgsConstructor
public enum BizErrorCode {


    /**
     * 设置为空的属性为空
     */
    SET_EMPTY_FIELD_ERROR("0000026","设置为空的属性为出现异常"),


    /**
     * 员工信息已经存在
     */
    STAFF_EXISTED_ERROR("0000029","员工信息已经存在"),


    /**
     * 微信支付出现异常
     */
    WX_PAY_ERROR("0000624","微信支付出现异常"),

    /**
     * 微信支付签名解密出现异常
     */
    WX_PAY_SIGN_ERROR("0000623","微信支付签名解密出现异常"),


    ;

    /**
     * 错误码code
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
