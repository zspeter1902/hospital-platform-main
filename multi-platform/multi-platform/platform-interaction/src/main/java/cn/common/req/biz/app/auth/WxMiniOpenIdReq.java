package cn.common.req.biz.app.auth;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信用户openId绑定Req
 * @date 2023-02-21
 */
@Data
public class WxMiniOpenIdReq implements Serializable {

    private static final long serialVersionUID = -1646235082737859584L;

    /**
     * 微信小程序登陆的code
     */
    @NotEmpty(message = "微信小程序登陆凭据")
    private String code;

}
