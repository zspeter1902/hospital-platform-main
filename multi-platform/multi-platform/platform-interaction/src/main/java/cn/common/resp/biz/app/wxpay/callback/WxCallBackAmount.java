package cn.common.resp.biz.app.wxpay.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Singer create by singer - Singer email:singer-coder@qq.com
 * @projectName multi-platform
 * @Description: 微信支付订单数据
 * @date 2023-02-24
 */
@Data
public class WxCallBackAmount implements Serializable {

    private static final long serialVersionUID = 8462850251622531865L;

    /**
     * 总金额
     */
    @JSONField(name = "total")
    private Integer totalAmount;

    /**
     * 用户支付金额
     */
    @JSONField(name = "payer_total")
    private Integer payer_total;

    /**
     * 货币类型
     */
    @JSONField(name = "currency")
    private String currency = "CNY";

    /**
     * 用户支付币种
     */
    @JSONField(name = "payer_currency")
    private String payerCurrency = "CNY";

}
