package cn.common.req.biz.app.wx;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.zmi.common.interaction.req.app
 * @Description: 微信的基础请求信息
 * @date 2023-02-25
 */
@Data
public class WxBaseReq implements Serializable {

    private static final long serialVersionUID = -6176505282936345088L;

    /**
     * 前端拿到的Code
     */
    private String code;

    /**
     * 微信的授权类型
     */
    private String grantType;
}
