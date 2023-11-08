package cn.common.resp.biz.app.wxpay;

import cn.common.resp.biz.app.order.AppTradeResp;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName n.common.resp.biz.app.wxpay
 * @Description: 微信小程序支付Resp
 * @date 2023-02-24
 */
@Data
public class WxMiniPayRespApp extends AppTradeResp implements Serializable {

    private static final long serialVersionUID = 2848818050670339473L;

    /**
      * 预先支付时间戳
      */
    @JSONField(name = "timeStamp")
    private Long timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 订单详情扩展字符串
     */
    @JSONField(name = "package")
    private String payPackage;

    /**
     * 签名类型 签名类型，默认为RSA，仅支持RSA。
     */
    private String signType = "RSA";

    /**
     * 签名，使用字段appId、timeStamp、nonceStr、package
     */
    private String paySign;



}
