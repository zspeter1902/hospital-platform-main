package cn.common.req.biz.app.auth;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信用户手机号绑定Req
 * @date 2023-02-21
 */
@Data
public class WxMiniPhoneReq implements Serializable {

    private static final long serialVersionUID = -1646235082737859584L;

    /**
     * getPhoneNumber返回的code
     * 与wx.login返回的code 作用是不一样的，不能混用。
     */
    @NotEmpty(message = "微信小程序拿到手机号的Code")
    private String getPhoneCode;


}
