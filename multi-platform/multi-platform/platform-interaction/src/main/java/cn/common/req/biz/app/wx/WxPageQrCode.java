package cn.common.req.biz.app.wx;

import lombok.Data;
import pro.skywalking.validation.NotEmpty;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common.req.biz.app.wx
 * @Description: 微信页面二维码生成请求参数封装
 * @date 2021-02-26
 */
@Data
public class WxPageQrCode implements Serializable {

    private static final long serialVersionUID = 3308153316718504442L;

    /**
      * 微信页面二维码所带的参数
      */
    @NotEmpty(message = "微信页面二维码所带的参数->不可为空")
    private String scene;

    /**
     * 微信页面路径
     */
    @NotEmpty(message = "微信页面路径->不可为空")
    private String page;

    /**
     * 物品ID
     */
    @NotEmpty(message = "物品ID->不可为空")
    private String itemId;

}
