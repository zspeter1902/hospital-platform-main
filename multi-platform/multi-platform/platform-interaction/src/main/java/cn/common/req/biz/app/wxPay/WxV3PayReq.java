package cn.common.req.biz.app.wxPay;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.zmi.interaction.inner.wx.wxpay
 * @Description: 微信统一下单请求Req
 * @date 2023-02-24
 */
@Data
public class WxV3PayReq implements Serializable {

    private static final long serialVersionUID = -7104881066156806532L;


    /**
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 直连商户申请的公众号或移动应用appid。
     */
    @JSONField(name = "appid")
    private String appId;

    /**
     * 直连商户的商户号，由微信支付生成并下发。
     */
    @JSONField(name = "mchid")
    private String mchId;

    /**
     * 商品描述
     */
    private String description;

    /**
     *
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;


    /**
      * 支付者信息
      */
    @JSONField(name = "payer")
    private WxV3Payer wxV3Payer;


    /**
     * 订单金额信息
     */
    @JSONField(name = "amount")
    private WxV3PayAmount wxV3PayAmount;

}
