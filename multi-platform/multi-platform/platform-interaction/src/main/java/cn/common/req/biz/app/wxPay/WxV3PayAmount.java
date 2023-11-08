package cn.common.req.biz.app.wxPay;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by Singer email:singer-coder@qq.com
 * @packageName cn.common.resp.biz.app.wxpay
 * @Description: 微信V2支付金额信息
 * @date 2023-02-24
 */
@Data
public class WxV3PayAmount implements Serializable {

    private static final long serialVersionUID = 3263403190487771973L;

    /**
      * 总金额
      */
    @JSONField(name = "total")
    private Integer totalAmount;

    /**
     * 支付总金额
     */
    @JSONField(name = "currency")
    private String currency = "CNY";


}
